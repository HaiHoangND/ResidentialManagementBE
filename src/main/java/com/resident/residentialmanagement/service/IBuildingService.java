package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.BuildingDto;
import com.resident.residentialmanagement.entity.Building;
import org.springframework.data.domain.Page;

public interface IBuildingService {
    Page<Building> getAll(int pageNumber, int pageSize);

    Building getById(int id);

    int create(BuildingDto buildingDto);

    int update(int id, BuildingDto buildingDto);

    Boolean delete(int id);
}
