package com.customersim.management.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@Table(name ="Customer")
public class Customer implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="customer_firstName")
    private String customerFirstName;
    @Column(name="customer_lastName")
    private String customerLastName;
    @Column(name="date_Of_Brith")
    private String dateOfBrith;

    @Column(name="customer_email")
    private String email;

    @OneToMany(targetEntity = SIM.class,cascade =CascadeType.ALL )
    @JoinColumn(name="customer_id",referencedColumnName = "Id")
    private List<SIM> sims;

}
