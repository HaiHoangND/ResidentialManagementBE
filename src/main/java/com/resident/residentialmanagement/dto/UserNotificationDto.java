package com.resident.residentialmanagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNotificationDto {
    private int id;
    private int userId;
    private int notificationId;
    private boolean readStatus;
}
