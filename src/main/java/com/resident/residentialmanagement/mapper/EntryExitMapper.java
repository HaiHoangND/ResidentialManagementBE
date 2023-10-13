package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.EntryExitDto;
import com.resident.residentialmanagement.entity.EntryExit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntryExitMapper extends GenericMapper<EntryExitDto, EntryExit>{
    @Override
    @Mapping(source = "gate.id", target = "gateId")
    EntryExitDto toDto(EntryExit entity);

    @Override
    @Mapping(source = "gateId", target = "gate.id")
    EntryExit createEntity(EntryExitDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(source = "gateId", target = "gate.id")
    void updateEntity(@MappingTarget EntryExit entity, EntryExitDto dto);
}
