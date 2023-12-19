package com.resident.residentialmanagement.controller;

import com.resident.residentialmanagement.entity.QrInformation;
import com.resident.residentialmanagement.response.GeneralResponse;
import com.resident.residentialmanagement.service.impl.EncryptDecryptService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/encrypt")
public class EncryptDecryptController {
    private final EncryptDecryptService encryptDecryptService;

    public EncryptDecryptController(EncryptDecryptService encryptDecryptService) {
        this.encryptDecryptService = encryptDecryptService;
    }

    @PostMapping("/enc")
    GeneralResponse<?> encryptQr(@RequestBody QrInformation qrInformation) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", encryptDecryptService.encryptObject(qrInformation));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("/dec")
    GeneralResponse<?> decryptQr(@RequestParam String encryptedString) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", encryptDecryptService.decryptObject(encryptedString));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
