package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.NotificationDto;
import com.resident.residentialmanagement.entity.Notification;
import org.springframework.data.domain.Page;

public interface INotificationService {
    Page<Notification> getAll(int pageNumber, int pageSize);

    Notification getById(int id);

    int create(NotificationDto notificationDto);

    int update(int id, NotificationDto notificationDto);

    Boolean delete(int id);
}
