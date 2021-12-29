package com.customersim.management.repository;

import com.customersim.management.entity.SIM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SIMRepository extends JpaRepository <SIM, Long >{



}
