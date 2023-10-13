package com.resident.residentialmanagement.dto;

import com.resident.residentialmanagement.entity.User;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {
    private int id;
    private int number;
    private String image;
    private int buildingId;
    private List<User> users;
}
