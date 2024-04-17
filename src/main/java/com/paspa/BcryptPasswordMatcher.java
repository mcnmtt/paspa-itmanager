package com.paspa;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptPasswordMatcher {
    // Metodo per criptare una password
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    // Metodo per confrontare una password non crittografata con una crittografata
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
