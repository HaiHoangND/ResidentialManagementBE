package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.EntryExitDto;
import com.resident.residentialmanagement.entity.EntryExit;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.EntryExitMapper;
import com.resident.residentialmanagement.repository.EntryExitRepository;
import com.resident.residentialmanagement.service.IEntryExitService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryExitService implements IEntryExitService {
    private final EntryExitRepository entryExitRepository;
    private final Validator validator;
    private final EntryExitMapper mapper;

    public EntryExitService(EntryExitRepository entryExitRepository, Validator validator, EntryExitMapper mapper) {
        this.entryExitRepository = entryExitRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<EntryExit> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return entryExitRepository.findAll(pageRequest);
    }

    @Override
    public EntryExit getById(int id) {
        return entryExitRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "EntryExit not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(EntryExitDto entryExitDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(entryExitDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        EntryExit entryExit = mapper.createEntity(entryExitDto);
        entryExitRepository.save(entryExit);
        return entryExit.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, EntryExitDto entryExitDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(entryExitDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        EntryExit entryExit = entryExitRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "EntryExit not found"));
        mapper.updateEntity(entryExit, entryExitDto);
        entryExitRepository.save(entryExit);
        return entryExit.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        entryExitRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "EntryExit not found"));
        entryExitRepository.deleteById(id);
        return true;
    }
}
