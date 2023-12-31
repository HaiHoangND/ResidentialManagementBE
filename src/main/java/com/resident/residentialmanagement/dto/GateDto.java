package com.resident.residentialmanagement.dto;

import com.resident.residentialmanagement.entity.EntryExit;
import com.resident.residentialmanagement.entity.GateCategory;
import com.resident.residentialmanagement.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GateDto {
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private GateCategory category;
    private List<EntryExit> entryExits;
    private List<User> users;
}
