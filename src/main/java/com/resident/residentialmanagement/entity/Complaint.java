package com.resident.residentialmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "complaint")
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String image;

    @CreationTimestamp
    private LocalDateTime date;

    @Column(length = 65535,columnDefinition="Text")
    private String description;

    @Column(name = "processed_status")
    private boolean processedStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
