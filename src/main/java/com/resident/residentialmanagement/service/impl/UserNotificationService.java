package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.UserNotificationDto;
import com.resident.residentialmanagement.entity.UserNotification;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.UserNotificationMapper;
import com.resident.residentialmanagement.repository.UserNotificationRepository;
import com.resident.residentialmanagement.service.IUserNotificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNotificationService implements IUserNotificationService {
    private final UserNotificationRepository userNotificationRepository;
    private final Validator validator;
    private final UserNotificationMapper mapper;

    public UserNotificationService(UserNotificationRepository userNotificationRepository, Validator validator, UserNotificationMapper mapper) {
        this.userNotificationRepository = userNotificationRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<UserNotification> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return userNotificationRepository.findAll(pageRequest);
    }

    @Override
    public UserNotification getById(int id) {
        return userNotificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "UserNotification not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(UserNotificationDto userNotificationDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(userNotificationDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        UserNotification userNotification = mapper.createEntity(userNotificationDto);
        userNotificationRepository.save(userNotification);
        return userNotification.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, UserNotificationDto userNotificationDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(userNotificationDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        UserNotification userNotification = userNotificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "UserNotification not found"));
        UserNotificationDto userNotificationDto1 = mapper.toDto(userNotification);
        UserNotification userNotification1 = mapper.createEntity(userNotificationDto1);
        mapper.updateEntity(userNotification1, userNotificationDto);
        userNotificationRepository.save(userNotification1);
        return userNotification1.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        userNotificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "UserNotification not found"));
        userNotificationRepository.deleteById(id);
        return true;
    }
}
