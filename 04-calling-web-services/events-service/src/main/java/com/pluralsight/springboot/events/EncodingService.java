package com.pluralsight.springboot.events;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncodingService {    
    public EncodingService(){
    }

    public String encode(String value) {
        return Base64.getUrlEncoder()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public int decode(String encoded) {
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(encoded);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
            return Integer.parseInt(decoded);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid cursor", e);
        }
    }
}
