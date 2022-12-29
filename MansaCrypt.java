import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

import javax.crypto.NoSuchPaddingException;

import javax.crypto.SecretKey;

/**
 * 
 */

public class MansaCrypt {

    public static void main(String[] args)

            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, IOException, ClassNotFoundException {

        // final String texteClair = "Ceci represente le text a crypter";
        // byte[] texteClairEnBytes = texteClair.getBytes();

    
        if (args[0].equals("encrypt")) {

            System.out.println ("Encrypting");

        

        // Input Stream for file to encrypt
        FileInputStream toBeCrypted = new FileInputStream(args[1]);

        //Output Stream for encrypted file
        FileOutputStream encryptedFile = new FileOutputStream(args[2]);

        

        //Algorithm of encryption
        String algo = args[3];

        //Length of key 
        int keyLenght = Integer.parseInt(args[4]);

        
        // Generation de la cle 
        Key maCle = genererCle(algo, keyLenght);
        // byte[] maCleBytes = maCle.getEncoded();

        //Output Stream for the Key
        FileOutputStream key = new FileOutputStream("FileSecretKey.txt");
        ObjectOutputStream oos = new ObjectOutputStream(key);

        // for (byte b : maCleBytes) {
        //     key.write(b);
        // }

        oos.writeObject(maCle);
        oos.close();

        
        
        
        
        //reader 
        int data;
        byte [] chiffrei;

        while ((data = toBeCrypted.read()) != -1) {

            
            chiffrei = chiffrerTexte(String.valueOf(data), maCle, algo);
            System.out.println((char)(data));
            System.out.println("Byte to hex " + (chiffrei));
            encryptedFile.write((chiffrei));            
        }

        toBeCrypted.close();
        encryptedFile.close();
        key.close();


            
        }





        if (args[0].equals("decrypt")) {

        

            // Input Stream for file to decrypted
            FileInputStream toBeDeCrypted = new FileInputStream(args[1]);
    
            //Output Stream for encrypted file
            FileOutputStream decryptedFile = new FileOutputStream(args[2]);

            //Input Stream for the key
            FileInputStream key = new FileInputStream(args[4]);
            ObjectInputStream ois = new ObjectInputStream(key);

            Key maCle;

            maCle = (Key)ois.readObject();

    
            
    
            //Algorithm of decryption
            String algo = args[3];
    
            //Length of key 
            // int keyLenght = Integer.parseInt(args[4]);

            // byte[] keyChar= new byte[256];
            // int data ;
    
            // int i = 0;
            // // La cle de chiffrement 
            // while ((data = key.read())!= -1) {

            //     keyChar[i] = (byte)data;
            //     i++;
                
            // }



            
            // SecretKey maCle = new SecretKeySpec(keyChar, 0, keyChar.length, algo);
            System.out.print("Cle recupeirei : " + maCle.getEncoded());
    
            
            
            
            
            //reader   
            // int dataprime;
            String datadecrypted;
            int data;
    
            while ((data = toBeDeCrypted.read()) != -1) {
                
                
                // datadecrypted = dechiffrerTexte( (String.valueOf((char)data)).getBytes() , maCle, algo);
                datadecrypted = dechiffrerTexte((String.valueOf((char)data)), maCle, algo);

                System.out.println(bigIntToByteArray(data));
                decryptedFile.write(Integer.parseInt(datadecrypted));            
            }
    
            toBeDeCrypted.close(); 
            decryptedFile.close();
            key.close();
            ois.close();

    
    
                
            }



        // byte[] textChiffrei = chiffrerTexte(texteClair, maCle, "AES");

        // String texteClaire = dechiffrerTexte(textChiffrei, maCle, "AES");

        // System.out.println(texteClaire);

    }

    /**
     * @param bytes
     *              Cette fonction convertit les bytes en hexadecimal
     * @return
     */
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
    public static byte[] chiffrerTexte(String aChiffrer, Key maCleSecret, String algo)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher chiffrer = Cipher.getInstance("AES/CBC/PKCS5Padding");

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

        System.out.println("Cle : " + maCleSecret.getEncoded());

        return maCleSecret;

    }

    public static String dechiffrerTexte(String texteCHiffrei, Key maCleSecret, String algo)
            throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // ON APPLIQUE LE DECHIFFREMENT
        Cipher dechiffrer = Cipher.getInstance("AES/CBC/PKCS5Padding");
        dechiffrer.init(Cipher.DECRYPT_MODE, maCleSecret);
        String texteClair = new String(dechiffrer.doFinal(texteCHiffrei.getBytes()));
        System.out.println(texteClair);
        

        return texteClair;

    }

    private static byte[] bigIntToByteArray( final int i ) {
        BigInteger bigInt = BigInteger.valueOf(i);      
        return bigInt.toByteArray();
    }

}