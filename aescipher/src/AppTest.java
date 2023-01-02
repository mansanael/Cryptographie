import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testerChiffrementTexte() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{


        String monTexte = "Mansa est un Hacker ";
        String algorithme =  "AES/CBC/PKCS5Padding"; 
        SecretKey maCle = MansaCrypt.genererCleSecrete("AES",  256);
        IvParameterSpec genAlea = MansaCrypt.generateurPseudoAleatoire();
        String texteChiffrei = Chiffrement.chiffrerTexte(monTexte, algorithme, maCle, genAlea);
        String texteDechiffrei = Dechiffrement.dechiffrerTexte(texteChiffrei, algorithme, maCle, genAlea);

        Assert.assertEquals(monTexte, texteDechiffrei);
    }


    
}
