package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.ServiceDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.ServiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceController {
    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    GeneralResponse<?> getAllServices(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", service.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getServiceById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", service.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createService(@RequestBody ServiceDto serviceDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", service.create(serviceDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateService(@PathVariable int id, @RequestBody ServiceDto serviceDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", service.update(id, serviceDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteServiceById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", service.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
