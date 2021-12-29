package com.customersim.management.service;

import com.customersim.management.entity.SIM;
import com.customersim.management.mapper.SIMMapper;
import com.customersim.management.repository.SIMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SIMService {
    @Autowired
    private SIMRepository simRepository;

    public String saveSimDetails(SIMMapper simMapper){

        SIM sim= new SIM();
        sim.setId(simMapper.getSimId());
        sim.setSimName(simMapper.getSimName());
        sim.setSimNumber(simMapper.getSimNumber());
        sim.setSimNetwork(simMapper.getSimNetwork());
        simRepository.save(sim);

        return "200 ok";


    }

    public List<SIMMapper> getAllSimDetails() {
        List<SIMMapper>  simMappers = new ArrayList<>();
        List<SIM> all = simRepository.findAll();
        for (SIM s : all) {
            SIMMapper  simMapper = new SIMMapper();
            simMapper.setSimId(s.getId());
            simMapper.setSimName(s.getSimName());
            simMapper.setSimNumber((s.getSimNumber()));
            simMapper.setSimNetwork(s.getSimNetwork());

            simMappers.add(simMapper);
        }
        return simMappers;
    }



}
