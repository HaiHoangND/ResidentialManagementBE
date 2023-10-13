package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.UserDto;
import com.resident.residentialmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User>{
    @Override
    @Mapping(source = "gate.id", target = "gateId")
    UserDto toDto(User entity);

    @Override
    @Mapping(source = "gateId", target = "gate.id")
    User createEntity(UserDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "gateId", target = "gate.id")
    void updateEntity(@MappingTarget User entity, UserDto dto);
}
