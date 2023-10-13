package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.NotificationDto;
import com.resident.residentialmanagement.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends GenericMapper<NotificationDto, Notification>{
    @Override
    NotificationDto toDto(Notification entity);

    @Override
    Notification createEntity(NotificationDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Notification entity, NotificationDto dto);
}
