package com.customersim.management.mapper;

import lombok.Data;

import java.util.List;

@Data
public class EmailNotificationMapper {

    private String mailFrom;

    private List<String> mailList;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private String[] mailTo;
}
