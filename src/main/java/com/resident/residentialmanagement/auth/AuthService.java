package com.resident.residentialmanagement.auth;

import com.resident.residentialmanagement.config.JwtService;
import com.resident.residentialmanagement.entity.User;
import com.resident.residentialmanagement.exception.BusinessException;
import com.resident.residentialmanagement.repository.GateRepository;
import com.resident.residentialmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final GateRepository gateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<User> existingUser = repository.findByPhone(request.getPhone());
        if(existingUser.isPresent()){
            throw new BusinessException("400", "error", "Số điện thoại đã tồn tại");
        }
        var user = User.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .gender(request.getGender())
                .gate(gateRepository.findById(request.getGateId()).orElseThrow(null))
                .status(true)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            var res =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getPhone(),
                            request.getPassword()
                    )
            );
            var user = repository.findByPhone(request.getPhone())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .type("success")
                    .message("Đăng nhập thành công")
                    .id(user.getId())
                    .accessToken(jwtToken)
                    .userName(user.getName())
                    .role(user.getRole())
                    .phone(user.getPhone())
                    .roomNumber(user.getRooms().isEmpty() ? -1 : user.getRooms().get(0).getNumber())
                    .building(user.getRooms().isEmpty() ? "" : user.getRooms().get(0).getBuilding().getName())
                    .gender(user.getGender())
                    .gate(user.getGate().getId())
                    .build();
        } catch (BadCredentialsException e) {
            System.out.println(e);
            var user = repository.findByPhone(request.getPhone());
            if (!user.isPresent()) {
                // Tên người dùng không tồn tại
                return AuthenticationResponse.builder()
                        .type("failed")
                        .message("Số điện thoại không tồn tại")
                        .build();
            } else {
                // Mật khẩu không chính xác
                return AuthenticationResponse.builder()
                        .type("failed")
                        .message("Sai mật khẩu")
                        .build();
            }
        }

    }

}