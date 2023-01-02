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

public class Dechiffrement {

    /**
     * @param chiffrei
     * @param algorithme
     * @param cleSecrete
     * @param generateurAleatoire
     * @return texteDechiffrei
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static String dechiffrerTexte(String chiffrei, String algorithme, SecretKey cleSecrete,
            IvParameterSpec generateurAleatoire) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher dechiffrer = Cipher.getInstance(algorithme);
        dechiffrer.init(Cipher.DECRYPT_MODE, cleSecrete, generateurAleatoire);
        byte[] texteDechiffrei = dechiffrer.doFinal(Base64.getDecoder().decode(chiffrei));

        return new String(texteDechiffrei);

    }

    public static void dechiffrerFichier(String fichierClair, String fichierDestination, String algorithme,
            SecretKey cleSecrete, IvParameterSpec generateurAleatoire)
            throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher dechiffrer = Cipher.getInstance(algorithme);
        dechiffrer.init(Cipher.DECRYPT_MODE, cleSecrete, generateurAleatoire);

        FileInputStream lecteurFichier = new FileInputStream(fichierClair);
        FileOutputStream ecrireFichier = new FileOutputStream(fichierDestination);

        byte[] blockDetexte = new byte[64];
        int lecteurDeBytes;

        while ((lecteurDeBytes = lecteurFichier.read(blockDetexte)) != -1) {

            byte[] ecrire = dechiffrer.update(blockDetexte, 0, lecteurDeBytes);
            if (ecrire != null) {
                ecrireFichier.write(ecrire);

            }

        }

        byte[] texteChiffrei = dechiffrer.doFinal();

        if (texteChiffrei != null) {

            ecrireFichier.write(texteChiffrei);

        }

        lecteurFichier.close();
        ecrireFichier.close();
    }

}
