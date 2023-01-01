import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Chiffrement {

    public static String chiffrerTexte(String text, String algorithme, SecretKey cleSecrete, IvParameterSpec generateurAleatoire) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher chiffrer = Cipher.getInstance(algorithme);
        chiffrer.init(Cipher.ENCRYPT_MODE, cleSecrete, generateurAleatoire);
        byte[] texteChiffrei = chiffrer.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(texteChiffrei);
        
    }
    
}
