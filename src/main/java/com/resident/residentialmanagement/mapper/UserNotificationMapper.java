package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.UserNotificationDto;
import com.resident.residentialmanagement.entity.UserNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserNotificationMapper extends GenericMapper<UserNotificationDto, UserNotification>{
    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "notification.id", target = "notificationId")
    UserNotificationDto toDto(UserNotification entity);

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "notificationId", target = "notification.id")
    UserNotification createEntity(UserNotificationDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "notificationId", target = "notification.id")
    void updateEntity(@MappingTarget UserNotification entity, UserNotificationDto dto);
}
