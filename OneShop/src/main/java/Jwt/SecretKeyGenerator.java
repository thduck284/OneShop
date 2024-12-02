package Jwt;

import java.security.SecureRandom;

public class SecretKeyGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 64; 

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();  
        StringBuilder secretKey = new StringBuilder(KEY_LENGTH);

        for (int i = 0; i < KEY_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            secretKey.append(CHARACTERS.charAt(randomIndex));  
        }

        return secretKey.toString(); 
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey();
        System.out.println("Generated Secret Key: " + secretKey);
    }
}
