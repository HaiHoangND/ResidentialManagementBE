package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query("select r FROM Room r WHERE r.building.id = :buildingId")
    Page<Room> findByBuildingId(PageRequest pageRequest, int buildingId);

    @Query("select r.id FROM Room r WHERE r.number = :roomNumber and r.building.id = :buildingId")
    int findByRoomNumberAndBuildingId(int roomNumber, int buildingId);
}
