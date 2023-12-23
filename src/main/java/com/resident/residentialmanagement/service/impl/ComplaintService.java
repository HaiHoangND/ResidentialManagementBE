package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.ComplaintDto;
import com.resident.residentialmanagement.entity.Complaint;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.ComplaintMapper;
import com.resident.residentialmanagement.repository.ComplaintRepository;
import com.resident.residentialmanagement.service.IComplaintService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {
    private final ComplaintRepository complaintRepository;
    private final Validator validator;
    private final ComplaintMapper mapper;

    public ComplaintService(ComplaintRepository complaintRepository, Validator validator, ComplaintMapper mapper) {
        this.complaintRepository = complaintRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Complaint> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return complaintRepository.findAll(pageRequest);
    }


    @Override
    public Complaint getById(int id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Complaint not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(ComplaintDto complaintDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(complaintDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Complaint complaint = mapper.createEntity(complaintDto);
        complaintRepository.save(complaint);
        return complaint.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, ComplaintDto complaintDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(complaintDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Complaint not found"));
        ComplaintDto complaintDto1 = mapper.toDto(complaint);
        Complaint complaint1 = mapper.createEntity(complaintDto1);
        mapper.updateEntity(complaint1, complaintDto);
        complaintRepository.save(complaint1);
        return complaint1.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        complaintRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Complaint not found"));
        complaintRepository.deleteById(id);
        return true;
    }
}
