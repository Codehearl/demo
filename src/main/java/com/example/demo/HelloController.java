package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private DecryptionService decryptionService;

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedText) {
        String privateKeyStr = "----BEGIN ENCRYPTED PRIVATE KEY-----\n"+
        "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\n"+
        "-----END ENCRYPTED PRIVATE KEY-----";  // replace with your actual private key

        try {
            return decryptionService.decrypt(encryptedText, privateKeyStr);
        } catch (Exception e) {
            e.printStackTrace();
            return "Decryption failed";
        }
    }
}