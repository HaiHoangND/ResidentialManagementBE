package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.BuildingDto;
import com.resident.residentialmanagement.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BuildingMapper extends GenericMapper<BuildingDto, Building>{
    @Override
    BuildingDto toDto(Building entity);

    @Override
    Building createEntity(BuildingDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Building entity, BuildingDto dto);
}
