package com.resident.residentialmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;
    private String image;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @JsonIgnore
    @ManyToMany(mappedBy = "rooms")
    private List<User> users;

}
