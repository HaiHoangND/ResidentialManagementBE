package com.resident.residentialmanagement.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.resident.residentialmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String type;
    private String message;
    @JsonProperty("access_token")
    private String accessToken;
    private String userName;
    private Role role;
    private Integer id;
    private String phone;
    private int roomNumber;
    private String building;
    private String gender;
    private int gate;
}
