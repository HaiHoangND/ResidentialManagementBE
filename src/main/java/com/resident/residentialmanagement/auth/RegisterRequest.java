package com.resident.residentialmanagement.auth;

import com.resident.residentialmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String password;
    private String phone;
    private boolean status;
    private String gender;
    private Role role;
    private int gateId;
}
