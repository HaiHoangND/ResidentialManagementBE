package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.UserDto;
import com.resident.residentialmanagement.entity.Role;
import com.resident.residentialmanagement.entity.User;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<User> getAll(int pageNumber, int pageSize, Role role);

    User getById(int id);

    int create(UserDto userDto);

    int update(int id, UserDto userDto);

    Boolean delete(int id);
}
