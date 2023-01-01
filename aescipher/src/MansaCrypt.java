
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class MansaCrypt {




    /**
     * @param keyLength
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey genererCleSecrete(String algorithme, int tailleDeLaCle) throws NoSuchAlgorithmException{

        KeyGenerator key = KeyGenerator.getInstance(algorithme);
        key.init(tailleDeLaCle);
        SecretKey cleSecrete  = key.generateKey();

        System.out.println(cleSecrete);    

        return cleSecrete; 
    }


    public static SecretKey genererCleMotDePasse(String motDePasse, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey cleSecrete = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        

        return cleSecrete;
        
    }

    public static SecretKey genererCleMotDePasse(String motDePasse, String salt, String instance, int iteration, int tailleDeLaCle, String algorithme) throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(instance);
        KeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), salt.getBytes(), iteration, tailleDeLaCle);
        SecretKey cleSecrete = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), algorithme);

        
        

        return cleSecrete;
        
    }


    //Generateur de nombre pseudo-aleatoire

    public static IvParameterSpec generateurPseudoAleatoire(){

        byte[] alea = new byte[16];
        new SecureRandom().nextBytes(alea);
        IvParameterSpec nombreAleatoire = new IvParameterSpec(alea);
        return nombreAleatoire;
    }


    
}
