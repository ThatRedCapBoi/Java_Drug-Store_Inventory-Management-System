/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infra;

import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Itadori
 */
public class PasswordUtil {

    public static boolean verify(String rawPassword, String stored) {
        if (rawPassword == null || stored == null) {
            return false;
        }
        String[] parts = stored.split("\\$");
        if (parts.length != 4) {
            return false;
        }
        if (!"pbkdf2".equalsIgnoreCase(parts[0])) {
            return false;
        }
        int iterations;
        try {
            iterations = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        byte[] salt;
        byte[] expected;
        try {
            salt = Base64.getDecoder().decode(parts[2]);
            expected = Base64.getDecoder().decode(parts[3]);
        } catch (IllegalArgumentException e) {
            return false;
        }
        byte[] actual = pbkdf2("PBKDF2WithHmacSHA256", rawPassword.toCharArray(), salt, iterations, expected.length);
        if (actual != null && MessageDigest.isEqual(expected, actual)) {
            return true;
        }
        actual = pbkdf2("PBKDF2WithHmacSHA1", rawPassword.toCharArray(), salt, iterations, expected.length);
        return actual != null && MessageDigest.isEqual(expected, actual);
    }

    private static byte[] pbkdf2(String algo, char[] password, byte[] salt, int iterations, int keyLenBytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLenBytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algo);
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            return null;
        }
    }
}
