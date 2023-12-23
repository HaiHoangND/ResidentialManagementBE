package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Gate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate,Integer> {
    @Query("select g FROM Gate g WHERE g.id >= 2")
    Page<Gate> findAll(PageRequest pageRequest);

    @Query("select g FROM Gate g WHERE g.id >= 2")
    List<Gate> findAllNoPage();
}
