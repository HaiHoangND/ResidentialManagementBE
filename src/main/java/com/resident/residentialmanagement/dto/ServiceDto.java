package com.resident.residentialmanagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDto {
    private int id;
    private String name;
    private double price;
    private String image;
    private String providerName;
    private String description;
    private String phoneContact;
}
