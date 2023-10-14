package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.UserNotificationDto;
import com.resident.residentialmanagement.entity.UserNotification;
import org.springframework.data.domain.Page;

public interface IUserNotificationService {
    Page<UserNotification> getAll(int pageNumber, int pageSize);

    UserNotification getById(int id);

    int create(UserNotificationDto userNotificationDto);

    int update(int id, UserNotificationDto userNotificationDto);

    Boolean delete(int id);
}
