package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {
}
