package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.dto.BuildingDto;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.BuildingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    GeneralResponse<?> getAllBuildings(@RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", buildingService.getAll(pageNumber, pageSize));
        }catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    GeneralResponse<?> getBuildingById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", buildingService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createBuilding(@RequestBody BuildingDto buildingDto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", buildingService.create(buildingDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    GeneralResponse<?> updateBuilding(@PathVariable int id, @RequestBody BuildingDto buildingDto) {
        try {
            return GeneralResponse.ok("success", "Successfully updated", buildingService.update(id, buildingDto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteBuildingById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", buildingService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
