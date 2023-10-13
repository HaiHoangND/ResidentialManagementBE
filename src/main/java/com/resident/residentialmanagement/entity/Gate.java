package com.resident.residentialmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "gate")
public class Gate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GateCategory category;

    @OneToMany(mappedBy = "gate")
    private List<EntryExit> entryExits;

    @OneToMany(mappedBy = "gate")
    private List<User> users;
}
