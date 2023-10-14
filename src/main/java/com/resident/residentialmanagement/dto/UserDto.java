package com.resident.residentialmanagement.dto;

import com.resident.residentialmanagement.entity.Complaint;
import com.resident.residentialmanagement.entity.Notification;
import com.resident.residentialmanagement.entity.Role;
import com.resident.residentialmanagement.entity.Room;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String phone;
    private boolean status;
    private String gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<Notification> notifications;
    private int gateId;
    private List<Room> rooms;
    private List<Complaint> complaints;
}
