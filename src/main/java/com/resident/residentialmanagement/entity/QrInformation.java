package com.resident.residentialmanagement.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QrInformation implements Serializable {
    private String userName;
    private String userPhone;
    private String destination;
    private boolean visitorRequest;
    private String task;
    private String visitorName;
    private LocalDateTime createAt;
    private LocalDateTime expireAt;
}
