package com.customersim.management.mapper;

import com.customersim.management.entity.SIM;
import lombok.Data;
import java.util.List;

@Data
public class CustomerMapper {

   private  Long customerId;

    private String customerFirstName;
   
    private String customerLastName;
   
    private String dateOfBrith;

   private String simNumber;

   private String emailId;
}
