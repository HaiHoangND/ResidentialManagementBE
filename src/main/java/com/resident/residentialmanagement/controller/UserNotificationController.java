package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.UserNotificationDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.UserNotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/userNotification")
public class UserNotificationController {
    private final UserNotificationService userNotificationService;

    public UserNotificationController(UserNotificationService userNotificationService) {
        this.userNotificationService = userNotificationService;
    }

    @GetMapping
    public GeneralResponse<?> getAllUserNotifications(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", userNotificationService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public GeneralResponse<?> getUserNotificationById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", userNotificationService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    public GeneralResponse<?> createUserNotification(@RequestBody UserNotificationDto userNotificationDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", userNotificationService.create(userNotificationDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public GeneralResponse<?> updateUserNotification(@PathVariable int id, @RequestBody UserNotificationDto userNotificationDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", userNotificationService.update(id, userNotificationDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public GeneralResponse<?> deleteUserNotificationById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", userNotificationService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
