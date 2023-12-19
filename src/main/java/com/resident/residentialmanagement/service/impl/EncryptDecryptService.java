package com.resident.residentialmanagement.service.impl;

import com.resident.residentialmanagement.entity.QrInformation;
import com.resident.residentialmanagement.service.IEncryptDecrypt;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class EncryptDecryptService implements IEncryptDecrypt {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    // Tạo một SecretKey cố định, bạn có thể thay đổi giá trị keyBytes để tạo khóa khác
    private static final byte[] keyBytes = "4564A821F9DEB0D308A3848B92FED004".getBytes(StandardCharsets.UTF_8);
    private static final SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

    // Tạo một vector khởi tạo (IV) cố định, bạn có thể thay đổi giá trị ivBytes để tạo IV khác
    // Tạo một vector khởi tạo (IV) cố định, sử dụng mảng byte có độ dài là 16
    private static final byte[] ivBytes = {
            (byte) 0x51, (byte) 0x55, (byte) 0x2B, (byte) 0x0F,
            (byte) 0x85, (byte) 0x22, (byte) 0x7F, (byte) 0x76,
            (byte) 0x1C, (byte) 0x1C, (byte) 0xFA, (byte) 0x01,
            (byte) 0xDE, (byte) 0xB6, (byte) 0x17, (byte) 0x9F
    };
    private static final IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);


    @Override
    public String encryptObject(QrInformation qrInformation) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            qrInformation.setCreateAt(LocalDateTime.now());
            qrInformation.setExpireAt(qrInformation.getCreateAt().plusHours(1));
            // Chuyển đối tượng QrInformation thành mảng byte
//            byte[] serializedObject = SerializationUtils.serialize(qrInformation);
            String serializedObject = qrInformation.toString();
            System.out.println(serializedObject);
            // Mã hóa dữ liệu
            byte[] encryptedData = cipher.doFinal(serializedObject.getBytes());

            // Chuyển đổi mảng byte thành chuỗi Base64
            return Base64.getUrlEncoder().encodeToString(encryptedData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            // Xử lý các ngoại lệ khi xảy ra lỗi
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decryptObject(String encryptedString) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            // Giải mã chuỗi Base64 thành mảng byte
            byte[] encryptedData = Base64.getUrlDecoder().decode(encryptedString);

            // Giải mã dữ liệu
            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            // Xử lý các ngoại lệ khi xảy ra lỗi
            e.printStackTrace();
        }
        return null;
    }
}
