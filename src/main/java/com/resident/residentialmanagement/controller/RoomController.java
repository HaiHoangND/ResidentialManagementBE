package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.RoomDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    GeneralResponse<?> getAllRooms(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", roomService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getRoomById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", roomService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createRoom(@RequestBody RoomDto roomDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", roomService.create(roomDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping("/addUser")
    GeneralResponse<?> addUser(@RequestParam int userId, @RequestParam int roomId) {
        try {
            return GeneralResponse.ok("success", "Successfully created", roomService.addUser(userId,roomId));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateRoom(@PathVariable int id, @RequestBody RoomDto roomDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", roomService.update(id, roomDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteRoomById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", roomService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
