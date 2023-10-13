package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
