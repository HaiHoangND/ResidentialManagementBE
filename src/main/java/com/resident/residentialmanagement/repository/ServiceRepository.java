package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {
}
