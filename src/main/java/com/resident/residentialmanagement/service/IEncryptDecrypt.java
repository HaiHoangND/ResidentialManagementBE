package com.resident.residentialmanagement.service;

import com.resident.residentialmanagement.entity.QrInformation;

public interface IEncryptDecrypt {
    String encryptObject(QrInformation qrInformation);
    String decryptObject(String encryptedString);
}
