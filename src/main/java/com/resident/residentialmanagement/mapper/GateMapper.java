package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.GateDto;
import com.resident.residentialmanagement.entity.Gate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GateMapper extends GenericMapper<GateDto, Gate>{
    @Override
    GateDto toDto(Gate entity);

    @Override
    Gate createEntity(GateDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Gate entity, GateDto dto);
}
