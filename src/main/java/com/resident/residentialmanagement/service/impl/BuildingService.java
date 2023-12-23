package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.BuildingDto;
import com.resident.residentialmanagement.entity.Building;
import com.resident.residentialmanagement.entity.Room;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.BuildingMapper;
import com.resident.residentialmanagement.repository.BuildingRepository;
import com.resident.residentialmanagement.repository.RoomRepository;
import com.resident.residentialmanagement.service.IBuildingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    private final BuildingRepository buildingRepository;

    private final RoomRepository roomRepository;
    private final Validator validator;
    private final BuildingMapper mapper;

    public BuildingService(BuildingRepository buildingRepository, RoomRepository roomRepository, Validator validator, BuildingMapper mapper) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Building> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return buildingRepository.findAll(pageRequest);
    }

    @Override
    public List<Building> getAllNoPage() {
        return buildingRepository.findAll();
    }

    @Override
    public Page<Room> getAllRooms(int pageNumber, int pageSize, int buildingId) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<Room> roomPage = roomRepository.findByBuildingId(pageRequest, buildingId);
        return roomPage;
    }

    @Override
    public Building getById(int id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Building not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(BuildingDto buildingDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(buildingDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Building building = mapper.createEntity(buildingDto);
        buildingRepository.save(building);
        return building.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, BuildingDto buildingDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(buildingDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Building not found"));
        mapper.updateEntity(building, buildingDto);
        buildingRepository.save(building);
        return building.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        buildingRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Building not found"));
        buildingRepository.deleteById(id);
        return true;
    }
}
