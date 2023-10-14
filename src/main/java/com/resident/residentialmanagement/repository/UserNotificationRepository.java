package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Integer> {
}
