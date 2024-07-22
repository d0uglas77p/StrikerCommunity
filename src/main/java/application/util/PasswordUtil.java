package application.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String textoPassword) {
        return BCrypt.hashpw(textoPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String textoPassword, String hashedPassword) {
        return BCrypt.checkpw(textoPassword, hashedPassword);
    }
}