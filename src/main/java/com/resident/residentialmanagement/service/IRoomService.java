package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.dto.RoomDto;
import com.resident.residentialmanagement.entity.Room;
import org.springframework.data.domain.Page;

public interface IRoomService {
    Page<Room> getAll(int pageNumber, int pageSize);

    Room getById(int id);

    int create(RoomDto roomDto);

    int update(int id, RoomDto roomDto);

    Boolean delete(int id);
}
