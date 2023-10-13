package com.resident.residentialmanagement.mapper;

public interface GenericMapper<D, E> {

    D toDto(E entity);

    E createEntity(D dto);

    void updateEntity( E entity, D dto);

}
