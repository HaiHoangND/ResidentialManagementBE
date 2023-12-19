package com.resident.residentialmanagement.repository;

import com.resident.residentialmanagement.entity.Role;
import com.resident.residentialmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u FROM User u WHERE u.role = :role")
    Page<User> findAll(PageRequest pageRequest, Role role);

    @Query("select u from User u where u.phone = :phone")
    Optional<User> findByPhone(String phone);
}
