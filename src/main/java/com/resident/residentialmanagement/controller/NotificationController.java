package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.NotificationDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    GeneralResponse<?> getAllNotifications(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", notificationService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getNotificationById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", notificationService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createNotification(@RequestBody NotificationDto notificationDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", notificationService.create(notificationDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateNotification(@PathVariable int id, @RequestBody NotificationDto notificationDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", notificationService.update(id, notificationDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteNotificationById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", notificationService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
