package com.resident.residentialmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntryExitDto {
    private int id;
    private String userName;
    private boolean visitorRequest;
    private LocalDateTime date;
    private int gateId;
}
