package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.ServiceDto;
import com.resident.residentialmanagement.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends GenericMapper<ServiceDto, Service>{
    @Override
    ServiceDto toDto(Service entity);

    @Override
    Service createEntity(ServiceDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Service entity, ServiceDto dto);
}
