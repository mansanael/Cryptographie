import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Chiffrement {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);


        SecretKey maCleSecrete1 = keygen.generateKey();
        Key  maCleSecrete2 = keygen.generateKey();

        System.out.println("La premiere Cle " + byteToHex(maCleSecrete1.getEncoded()));
        System.out.println("La seconde Cle " + byteToHex(maCleSecrete2.getEncoded()));

        Cipher chiffrer = Cipher.getInstance("AES");
        chiffrer.init(Cipher.WRAP_MODE, maCleSecrete2);

        byte[] CleChiffrei =  chiffrer.wrap(maCleSecrete1);
        
        System.out.println("La Cle chiffrei est  : " + byteToHex(CleChiffrei));

        Cipher dechiffrer = Cipher.getInstance("AES");
        dechiffrer.init(Cipher.UNWRAP_MODE, maCleSecrete2);
        Key Cledechiffrei = dechiffrer.unwrap(CleChiffrei, "AES", Cipher.SECRET_KEY);

        System.out.println("La cle d'origine est : " + byteToHex(Cledechiffrei.getEncoded()));
        
    }

    public static String byteToHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {

            sb.append(String.format("%2X ", b));

        }

        return sb.toString();

    }

}