package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.ComplaintDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.ComplaintService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/complaint")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    GeneralResponse<?> getAllComplaints(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", complaintService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getComplaintById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", complaintService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createComplaint(@RequestBody ComplaintDto complaintDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", complaintService.create(complaintDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateComplaint(@PathVariable int id, @RequestBody ComplaintDto complaintDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", complaintService.update(id, complaintDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteComplaintById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", complaintService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}