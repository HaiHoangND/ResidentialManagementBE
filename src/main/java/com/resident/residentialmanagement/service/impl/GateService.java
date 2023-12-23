package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.GateDto;
import com.resident.residentialmanagement.entity.Building;
import com.resident.residentialmanagement.entity.Gate;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.GateMapper;
import com.resident.residentialmanagement.repository.GateRepository;
import com.resident.residentialmanagement.service.IGateService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GateService implements IGateService {
    private final GateRepository gateRepository;
    private final Validator validator;
    private final GateMapper mapper;

    public GateService(GateRepository gateRepository, Validator validator, GateMapper mapper) {
        this.gateRepository = gateRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Gate> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return gateRepository.findAll(pageRequest);
    }

    @Override
    public List<Gate> getAllNoPage() {
        return gateRepository.findAllNoPage();
    }

    @Override
    public Gate getById(int id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Gate not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(GateDto gateDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(gateDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Gate gate = mapper.createEntity(gateDto);
        gateRepository.save(gate);
        return gate.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, GateDto gateDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(gateDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Gate not found"));
        mapper.updateEntity(gate, gateDto);
        gateRepository.save(gate);
        return gate.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        gateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Gate not found"));
        gateRepository.deleteById(id);
        return true;
    }
}
