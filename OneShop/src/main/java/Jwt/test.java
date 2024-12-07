package Jwt;

import org.mindrot.jbcrypt.BCrypt;

public class test {

    public static void main(String[] args) {
        String originalPassword = "phamvani";
        
        String hashedPassword1 = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
        System.out.println(hashedPassword1);
        
        System.out.println(BCrypt.checkpw(originalPassword, hashedPassword1));
    }
}
