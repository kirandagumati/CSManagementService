package com.customersim.management.controller;

import com.customersim.management.entity.SIM;
import com.customersim.management.mapper.SIMMapper;
import com.customersim.management.service.SIMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SIMController {

    @Autowired
    private SIMService simservice;

    @PostMapping("/createsim")
    public String  createSim (SIMMapper simMapper) {
        return simservice.saveSimDetails(simMapper);

    }

    @GetMapping("/fetchallsims")
    public List<SIMMapper> getAllSims(){
        return simservice.getAllSimDetails();

    }
}
