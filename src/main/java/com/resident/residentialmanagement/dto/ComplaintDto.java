package com.resident.residentialmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplaintDto {
    private int id;
    private String title;
    private String image;
    private LocalDateTime date;
    private String description;
    private boolean processedStatus;
    private int userId;
}
