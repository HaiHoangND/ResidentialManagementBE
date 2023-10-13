package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.RoomDto;
import com.resident.residentialmanagement.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper extends GenericMapper<RoomDto, Room>{
    @Override
    @Mapping(source = "building.id", target = "buildingId")
    RoomDto toDto(Room entity);

    @Override
    @Mapping(source = "buildingId", target = "building.id")
    Room createEntity(RoomDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "buildingId", target = "building.id")
    void updateEntity(@MappingTarget Room entity, RoomDto dto);
}
