package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.UserDto;
import com.resident.residentialmanagement.entity.Role;
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
import java.util.Optional;

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
    public Page<User> getAll(int pageNumber, int pageSize, Role role) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findAll(pageRequest, role);
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
        Optional<User> user1 = userRepository.findByPhone(userDto.getPhone());
        if (user1.isPresent() && user1.orElseThrow().getId() != id) {
            throw new BusinessException("404", "error", "Số điện thoại đã tồn tại");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "User not found"));
        UserDto userDto1 = mapper.toDto(user);
        User user2 = mapper.createEntity(userDto1);
        user2.setPassword(user.getPassword());
        mapper.updateEntity(user2, userDto);
        userRepository.save(user2);
        return user2.getId();
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
