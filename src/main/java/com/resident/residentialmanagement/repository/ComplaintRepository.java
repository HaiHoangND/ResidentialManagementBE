package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
}
