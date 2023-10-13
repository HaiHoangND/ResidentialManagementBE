package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.EntryExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryExitRepository extends JpaRepository<EntryExit,Integer> {
}
