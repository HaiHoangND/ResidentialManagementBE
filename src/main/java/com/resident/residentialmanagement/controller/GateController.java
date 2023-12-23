package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.GateDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.GateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/gate")
public class GateController {
    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping
    GeneralResponse<?> getAllGates(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", gateService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("/all")
    GeneralResponse<?> getAllGatesNoPage() {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", gateService.getAllNoPage());
        }catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getGateById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", gateService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createGate(@RequestBody GateDto gateDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", gateService.create(gateDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateGate(@PathVariable int id, @RequestBody GateDto gateDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", gateService.update(id, gateDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteGateById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", gateService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
