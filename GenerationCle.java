import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.security.Provider;

import java.security.Security;

import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

import javax.crypto.NoSuchPaddingException;

import javax.crypto.SecretKey;

public class GenerationCle {

    private static StringBuilder append;

    public static void main(String[] args)

            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        final String texteClair = "Ceci represente le text a crypter";
        // byte[] texteClairEnBytes = texteClair.getBytes();

        SecretKey maCle = genererCle("AES", 256);

        byte[] textChiffrei = chiffrerTexte(texteClair, maCle, "AES");

        String texteClaire = dechiffrerTexte(textChiffrei, maCle, "AES");

        System.out.println(texteClaire);

    }

    public static String byteToHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {

            sb.append(String.format("%2X ", b));

        }

        return sb.toString();

    }

    /**
     * Cette fonction prend en entree un texte clair et le chiffre
     * 
     * @param aChiffrer
     * @return texteChiffrer
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] chiffrerTexte(String aChiffrer, SecretKey maCleSecret, String algo) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher chiffrer = Cipher.getInstance(algo);

        chiffrer.init(Cipher.ENCRYPT_MODE, maCleSecret);
        System.out.println("Le texte a chiffrei : " + aChiffrer);
        // ON APPLIQUE LE CHIFFREMENT
        byte[] texteChiffrer = chiffrer.doFinal(aChiffrer.getBytes());
        System.out.println("Le texte chiffrei en hex : " + byteToHex(texteChiffrer));

        return texteChiffrer;

    }

    /**
     * Cette fonction prend l'algorithme de cryptage et la taille de cle
     * 
     * @param algo
     * @param taille
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey genererCle(String algo, int taille) throws NoSuchAlgorithmException {

        KeyGenerator keygen = KeyGenerator.getInstance(algo);
        keygen.init(taille);
        SecretKey maCleSecret = keygen.generateKey();
        // byte[] maCleSecretEnByte = maCleSecret.getEncoded();

        System.out.println("Cle : " + maCleSecret);

        return maCleSecret;

    }

    public static String dechiffrerTexte(byte[] texteCHiffrei, SecretKey maCleSecret, String algo) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // ON APPLIQUE LE DECHIFFREMENT
        Cipher dechiffrer = Cipher.getInstance(algo);
        dechiffrer.init(Cipher.DECRYPT_MODE, maCleSecret);
        String texteClair = new String(dechiffrer.doFinal(texteCHiffrei));
        System.out.println(texteClair);

        return texteClair;

    }

}