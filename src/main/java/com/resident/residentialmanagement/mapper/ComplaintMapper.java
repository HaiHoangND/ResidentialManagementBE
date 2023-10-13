package com.resident.residentialmanagement.mapper;

import com.resident.residentialmanagement.dto.ComplaintDto;
import com.resident.residentialmanagement.entity.Complaint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ComplaintMapper extends GenericMapper<ComplaintDto, Complaint>{
    @Override
    @Mapping(source = "user.id", target = "userId")
    ComplaintDto toDto(Complaint entity);

    @Override
    @Mapping(source = "userId", target = "user.id")
    Complaint createEntity(ComplaintDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    void updateEntity(@MappingTarget Complaint entity, ComplaintDto dto);
}
