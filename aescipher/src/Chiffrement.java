import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    /**
     * @param text
     * @param algorithme
     * @param cleSecrete
     * @param generateurAleatoire
     * @return monTexteChiffrei
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String chiffrerTexte(String text, String algorithme, SecretKey cleSecrete,
            IvParameterSpec generateurAleatoire) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher chiffrer = Cipher.getInstance(algorithme);
        chiffrer.init(Cipher.ENCRYPT_MODE, cleSecrete, generateurAleatoire);
        byte[] texteChiffrei = chiffrer.doFinal(text.getBytes());
        String monTexteChiffrei = Base64.getEncoder().encodeToString(texteChiffrei);

        return monTexteChiffrei;

    }

    /**
     * @param fichierClair
     * @param fichierDestination
     * @param algorithme
     * @param cleSecrete
     * @param generateurAleatoire
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static void chiffrerFichier(String fichierClair, String fichierDestination, String algorithme,
            SecretKey cleSecrete, IvParameterSpec generateurAleatoire) throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        
        Cipher chiffrer = Cipher.getInstance(algorithme);
        chiffrer.init(Cipher.ENCRYPT_MODE, cleSecrete, generateurAleatoire);

        FileInputStream lecteurFichier = new FileInputStream(fichierClair);
        FileOutputStream ecrireFichier = new FileOutputStream(fichierDestination);

        byte [] blockDetexte = new byte[64];
        int lecteurDeBytes;

        while ((lecteurDeBytes = lecteurFichier.read(blockDetexte)) != -1) {

            byte [] ecrire = chiffrer.update(blockDetexte, 0, lecteurDeBytes);
            if (ecrire != null) {
                ecrireFichier.write(ecrire);
                
            }
            

        }

        byte [] texteChiffrei = chiffrer.doFinal();
        
        if (texteChiffrei != null) {

            ecrireFichier.write(texteChiffrei);
            
        }

        lecteurFichier.close();
        ecrireFichier.close();
    }

}
