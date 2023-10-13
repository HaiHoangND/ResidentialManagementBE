package com.resident.residentialmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String password;
    private boolean status;
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_notification",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notifications;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;

    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints;
}
