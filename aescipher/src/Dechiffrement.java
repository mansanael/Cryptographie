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

public class Dechiffrement {

    public static String dechiffrerTexte(String chiffrei, String algorithme, SecretKey cleSecrete,
            IvParameterSpec generateurAleatoire) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher dechiffrer = Cipher.getInstance(algorithme);
        dechiffrer.init(Cipher.DECRYPT_MODE, cleSecrete, generateurAleatoire);
        byte[] texteDechiffrei = dechiffrer.doFinal(Base64.getDecoder().decode(chiffrei));

        return new String(texteDechiffrei);

    }

}
