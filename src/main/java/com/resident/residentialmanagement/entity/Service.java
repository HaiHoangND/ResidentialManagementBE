package com.resident.residentialmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "service")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private String image;

    @Column(name = "provider_name")
    private String providerName;

    private String description;

    @Column(name = "phone_contact")
    private String phoneContact;
}
