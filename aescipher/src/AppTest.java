import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testerChiffrementTexte() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, KeyStoreException, CertificateException, IOException, UnrecoverableEntryException{


        String monTexte = "Mansa est un Hacker ";
        String algorithme =  "AES/CBC/PKCS5Padding"; 
        SecretKey maCle = MansaCrypt.genererCleSecrete("AES",  256);
        AjouterCle.AjouterUneCle("changeit", maCle, "test");
        SecretKey maCleDecrypte = LireCleJava.LireUneCle("changeit", "test");
        IvParameterSpec genAlea = MansaCrypt.generateurPseudoAleatoire();
        String texteChiffrei = Chiffrement.chiffrerTexte(monTexte, algorithme, maCle, genAlea);
        String texteDechiffrei = Dechiffrement.dechiffrerTexte(texteChiffrei, algorithme, maCleDecrypte, genAlea);

        Assert.assertEquals(monTexte, texteDechiffrei);
    }

    // @Test
    // public void testerChiffrementFichier() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {

    //     // String path = System.getProperty("user.dir");
    //     // System.out.println(path);
    //     // String fichier = "/plaintext.txt";
    //     // String destination = "/encrypted.txt";

    //      SecretKey maCle = MansaCrypt.genererCleMotDePasse("Hacker", "Good");
    //      String algorithme =  "AES/CBC/PKCS5Padding"; 
    //      IvParameterSpec genAlea = MansaCrypt.generateurPseudoAleatoire();

         



         

    //     Chiffrement.chiffrerFichier(fichier, destination, algorithme, maCle, genAlea);
        


        
    // }


    
}
