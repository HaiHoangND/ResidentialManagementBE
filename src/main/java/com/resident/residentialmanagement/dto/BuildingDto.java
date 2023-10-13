package com.resident.residentialmanagement.dto;

import com.resident.residentialmanagement.entity.Room;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDto {
    private int id;
    private String name;
    private String position;
    private List<Room> rooms;
}
