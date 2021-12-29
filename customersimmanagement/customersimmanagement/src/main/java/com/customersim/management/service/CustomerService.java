package com.customersim.management.service;

import com.customersim.management.entity.Customer;
import com.customersim.management.entity.SIM;
import com.customersim.management.mapper.CustomerAndSIMMapper;
import com.customersim.management.mapper.CustomerMapper;
import com.customersim.management.repository.CustomerRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String saveCustomer(CustomerMapper customerMapper) {

        Customer customer = new Customer();
        customer.setId(customerMapper.getCustomerId());
        customer.setCustomerFirstName(customerMapper.getCustomerFirstName());
        customer.setCustomerLastName(customerMapper.getCustomerLastName());
        customer.setEmail(customerMapper.getEmailId());
        customer.setDateOfBrith(customerMapper.getDateOfBrith());
        customerRepository.save(customer);
        return "200 ok";
    }

    public ByteArrayInputStream dailyExport() {
        List<Customer> collect = customerRepository.getCustomersListOneDayBeforeBirthday();

        List<CustomerAndSIMMapper> customerAndSIMMappersS = new ArrayList<>();
        for (Customer c : collect) {
            List<String> simNumbers = new ArrayList<>();
            for (SIM sim : c.getSims()) {
                simNumbers.add(sim.getSimNumber());
            }
            String simNum = simNumbers.stream().map(String::valueOf).collect(Collectors.joining(","));
            CustomerAndSIMMapper customerSimMapper = new CustomerAndSIMMapper();
            customerSimMapper.setCustomerId(c.getId());
            customerSimMapper.setCustomerFirstName(c.getCustomerFirstName());
            customerSimMapper.setCustomerLastName(c.getCustomerLastName());
            customerSimMapper.setSimNumber(simNum);
            customerSimMapper.setDateOfBirth(c.getDateOfBrith());
            customerAndSIMMappersS.add(customerSimMapper);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daily Export");

            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("Customer_Id");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Customer_FullName");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Customer_Email");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("SimNumber");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Date_Of_Birth");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows
            for (int i = 0; i < customerAndSIMMappersS.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(customerAndSIMMappersS.get(i).getCustomerId());
                dataRow.createCell(1).setCellValue(customerAndSIMMappersS.get(i).getCustomerFirstName());
                dataRow.createCell(2).setCellValue(customerAndSIMMappersS.get(i).getCustomerLastName());
                dataRow.createCell(4).setCellValue(customerAndSIMMappersS.get(i).getSimNumber());
                dataRow.createCell(5).setCellValue(customerAndSIMMappersS.get(i).getDateOfBirth().toString());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            return null;
        }

    }

    public String[] sendMailtoCustomerBefore7Days() {

      return customerRepository.getCustomer().stream().map(e->e.getEmail()).collect(Collectors.toList()).toArray(String[]::new);


    }
}
