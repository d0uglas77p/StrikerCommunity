package application.util;

import java.security.SecureRandom;
import java.math.BigInteger;

public class Token {
    private SecureRandom random = new SecureRandom();

    public String gerarToken() {
        return new BigInteger(130, random).toString(32);
    }
}
