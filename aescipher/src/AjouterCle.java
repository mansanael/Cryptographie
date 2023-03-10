import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AjouterCle {


    public static void AjouterUneCle(String motDePasseBaseDeCle, SecretKey cleSecrete, String alias ) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

        // Creation de la base de Cle
        KeyStore baseCle = KeyStore.getInstance("JCEKS");

        //Chargement de la base
        char [] motDePasse = motDePasseBaseDeCle.toCharArray();
        String path = "/usr/lib/jvm/java-17-openjdk-amd64/lib/security/cacerts";
        
        FileInputStream cle = new FileInputStream(path);
        
        baseCle.load(cle, motDePasse);

        // Creer la protection de la base de cle 

        KeyStore.ProtectionParameter parametreDeProtection = new KeyStore.PasswordProtection(motDePasse);

        // Creer un objet de type SecretKey

        SecretKey maCleSecrete = new SecretKeySpec((cleSecrete.getEncoded()), "AES");

        // Creer un objet de type SecretKeyEntry

        KeyStore.SecretKeyEntry entreeCleSecrete = new KeyStore.SecretKeyEntry(maCleSecrete);
        
        // Creer l'entree de la base de cle

        baseCle.setEntry(alias, entreeCleSecrete, parametreDeProtection);


        // Enregistrer la baseCle

        FileOutputStream baseSortie = new FileOutputStream("BaseCle");
        baseCle.store(baseSortie,motDePasse);

        System.out.println("Cle enregistrer");

        
    }

   
    
}
