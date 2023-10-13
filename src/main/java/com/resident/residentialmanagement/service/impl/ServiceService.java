package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.ServiceDto;
import com.resident.residentialmanagement.entity.Service;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.ServiceMapper;
import com.resident.residentialmanagement.repository.ServiceRepository;
import com.resident.residentialmanagement.service.IService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements IService {
    private final ServiceRepository serviceRepository;
    private final Validator validator;
    private final ServiceMapper mapper;

    public ServiceService(ServiceRepository serviceRepository, Validator validator, ServiceMapper mapper) {
        this.serviceRepository = serviceRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Service> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return serviceRepository.findAll(pageRequest);
    }

    @Override
    public Service getById(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Service not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(ServiceDto serviceDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(serviceDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Service service = mapper.createEntity(serviceDto);
        serviceRepository.save(service);
        return service.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, ServiceDto serviceDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(serviceDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Service not found"));
        mapper.updateEntity(service, serviceDto);
        serviceRepository.save(service);
        return service.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        serviceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Service not found"));
        serviceRepository.deleteById(id);
        return true;
    }
}
