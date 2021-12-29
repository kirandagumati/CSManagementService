package com.customersim.management.mapper;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CustomerAndSIMMapper {
    private  Long customerId;
    private String customerFirstName;
    private String customerLastName;
   private String simNumber;
   private String dateOfBirth;

}
