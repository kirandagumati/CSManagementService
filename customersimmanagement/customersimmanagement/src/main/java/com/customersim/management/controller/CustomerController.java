package com.customersim.management.controller;

import com.customersim.management.entity.Customer;
import com.customersim.management.mapper.CustomerMapper;
import com.customersim.management.service.CustomerService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/createcustomer")
    public String createCustomer(@RequestBody CustomerMapper customerMapper ) {

        return customerService.saveCustomer(customerMapper);
    }
    @GetMapping("/getDailyReport")
    public void fetchDailyReport(HttpServletResponse response) throws IOException {
        ByteArrayInputStream byteArrayInputStream = customerService.dailyExport();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=CustomerBirthdayList.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}
