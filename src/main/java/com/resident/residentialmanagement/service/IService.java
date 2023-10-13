package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.ServiceDto;
import com.resident.residentialmanagement.entity.Service;
import org.springframework.data.domain.Page;

public interface IService {
    Page<Service> getAll(int pageNumber, int pageSize);

    Service getById(int id);

    int create(ServiceDto serviceDto);

    int update(int id, ServiceDto serviceDto);

    Boolean delete(int id);
}
