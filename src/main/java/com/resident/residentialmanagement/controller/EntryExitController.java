package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.EntryExitDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.EntryExitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/entryExit")
public class EntryExitController {
    private final EntryExitService entryExitService;

    public EntryExitController(EntryExitService entryExitService) {
        this.entryExitService = entryExitService;
    }

    @GetMapping
    GeneralResponse<?> getAllEntryExits(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", entryExitService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getEntryExitById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", entryExitService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createEntryExit(@RequestBody EntryExitDto entryExitDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", entryExitService.create(entryExitDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateEntryExit(@PathVariable int id, @RequestBody EntryExitDto entryExitDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", entryExitService.update(id, entryExitDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteEntryExitById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", entryExitService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
