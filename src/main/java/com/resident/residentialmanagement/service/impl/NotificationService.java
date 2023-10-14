package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.NotificationDto;
import com.resident.residentialmanagement.entity.Notification;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.NotificationMapper;
import com.resident.residentialmanagement.repository.NotificationRepository;
import com.resident.residentialmanagement.repository.UserRepository;
import com.resident.residentialmanagement.service.INotificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;

    private final Validator validator;
    private final NotificationMapper mapper;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, Validator validator, NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Notification> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return notificationRepository.findAll(pageRequest);
    }

    @Override
    public Notification getById(int id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Notification not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(NotificationDto notificationDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(notificationDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Notification notification = mapper.createEntity(notificationDto);
        notificationRepository.save(notification);
        return notification.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, NotificationDto notificationDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(notificationDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Notification not found"));
        mapper.updateEntity(notification, notificationDto);
        notificationRepository.save(notification);
        return notification.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        notificationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Notification not found"));
        notificationRepository.deleteById(id);
        return true;
    }
}
