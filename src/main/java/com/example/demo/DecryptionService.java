package com.example.demo;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class DecryptionService {

    private static final String RSA = "RSA";

    public String decrypt(String encryptedText, String privateKeyStr) throws Exception {
        System.out.println("Decrypting: " + encryptedText);
        JSONObject json = new JSONObject(encryptedText);
        String text = json.getString("encryptedData");
        System.out.println("Decrypting: " + text);
        byte[] encryptedBytes = Base64.getDecoder().decode(text);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}