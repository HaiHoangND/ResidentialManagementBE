package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.dto.RoomDto;
import com.resident.residentialmanagement.entity.Room;
import com.resident.residentialmanagement.entity.User;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.mapper.RoomMapper;
import com.resident.residentialmanagement.repository.RoomRepository;
import com.resident.residentialmanagement.repository.UserRepository;
import com.resident.residentialmanagement.service.IRoomService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;

    private final UserRepository userRepository;
    private final Validator validator;
    private final RoomMapper mapper;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository, Validator validator, RoomMapper mapper) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Page<Room> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return roomRepository.findAll(pageRequest);
    }

    @Override
    public int addUser(int userId, int roomId){
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("404", "error", "User not found")); // Lấy thông tin User từ cơ sở dữ liệu
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new BusinessException("404", "error", "Room not found")); // Lấy thông tin Room từ cơ sở dữ liệu
        room.getUsers().add(user);
        user.getRooms().add(room);
        roomRepository.save(room);
        userRepository.save(user);
        return roomId;
    }

    @Override
    public int findRoomByRoomNumberAndBuildingId(int roomNumber, int buildingId){
        int roomId = roomRepository.findByRoomNumberAndBuildingId(roomNumber, buildingId);
        return roomId;
    }

    @Override
    public Room getById(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Room not found"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int create(RoomDto roomDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(roomDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Room room = mapper.createEntity(roomDto);
        roomRepository.save(room);
        return room.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int update(int id, RoomDto roomDto) {
        List<String> errors = new ArrayList<>();
        validator.validate(roomDto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException("400", "error", errors.get(0));
        }
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Room not found"));
        RoomDto roomDto1 = mapper.toDto(room);
        Room room1 = mapper.createEntity(roomDto1);
        mapper.updateEntity(room1, roomDto);
        roomRepository.save(room1);
        return room1.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(int id) {
        roomRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Room not found"));
        roomRepository.deleteById(id);
        return true;
    }
}
