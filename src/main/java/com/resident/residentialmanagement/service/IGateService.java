package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.GateDto;
import com.resident.residentialmanagement.entity.Gate;
import org.springframework.data.domain.Page;

public interface IGateService {
    Page<Gate> getAll(int pageNumber, int pageSize);

    Gate getById(int id);

    int create(GateDto gateDto);

    int update(int id, GateDto gateDto);

    Boolean delete(int id);
}
