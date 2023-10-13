package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.UserDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    GeneralResponse<?> getAllUsers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", userService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getUserById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", userService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createUser(@RequestBody UserDto userDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", userService.create(userDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", userService.update(id, userDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteUserById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", userService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
