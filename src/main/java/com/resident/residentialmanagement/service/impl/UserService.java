package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.UserDto;
import com.resident.residentialmanagement.entity.User;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.UserMapper;
import com.resident.residentialmanagement.repository.UserRepository;
import com.resident.residentialmanagement.service.IUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final Validator validator;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, Validator validator, UserMapper mapper) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<User> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "User not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(UserDto userDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(userDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        User user = mapper.createEntity(userDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, UserDto userDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(userDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "User not found"));
        mapper.updateEntity(user, userDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "User not found"));
        userRepository.deleteById(id);
        return true;
    }
}
