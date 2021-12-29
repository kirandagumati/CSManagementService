package com.customersim.management.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Setter
@Getter
@Table(name ="SIM")
public class SIM implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="sim_name")
    private String simName;
    @Column(name="sim_number")
    private String simNumber;
    @Column(name="sim_network")
    private String simNetwork;


}
