package com.customersim.management.service;

import com.customersim.management.mapper.EmailNotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    EmailNotificationService mailService;
    @Autowired
    CustomerService customerService;

    @Scheduled(cron = "0 0 0 * * *")
    public void sevenDayBeforeResponse() {
        EmailNotificationMapper eMail = new EmailNotificationMapper();
        eMail.setMailFrom("emailId");
        eMail.setMailTo(customerService.sendMailtoCustomerBefore7Days());
        eMail.setMailTo(eMail.getMailTo());
        eMail.setMailSubject("Subject");
        eMail.setMailContent("Massage!!!\n\nThanks\nCustomerSIMManagement");
        mailService.sendEmail(eMail);
    }


}
