import java.io.FileInputStream;
import java.io.FileOutputStream;
// import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;

public class LireCleJava {


    public static SecretKey LireUneCle(String motDePasseBaseDeCle,  String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {

        // Creation de la base de Cle
        KeyStore baseCle = KeyStore.getInstance("JCEKS");

        //Chargement de la base
        char [] motDePasse = motDePasseBaseDeCle.toCharArray();
        // String path = "/usr/lib/jvm/java-17-openjdk-amd64/lib/security/cacerts";
        
        FileInputStream cle = new FileInputStream("BaseCle");
        
        baseCle.load(cle, motDePasse);

        // Creer la protection de la base de cle 

        KeyStore.ProtectionParameter parametreDeProtection = new KeyStore.PasswordProtection(motDePasse);

        KeyStore.SecretKeyEntry cleSecrete = (KeyStore.SecretKeyEntry)baseCle.getEntry(alias, parametreDeProtection);

        // Enregistrer la baseCle


        FileOutputStream baseSortie = null;
        baseSortie = new FileOutputStream("BaseCle");
        baseCle.store(baseSortie,motDePasse);

        // Creation de l'objet SecretKey

        SecretKey maReCleSecrete = cleSecrete.getSecretKey();
        System.out.println(maReCleSecrete.getEncoded());

        System.out.println("Cle enregistrer");

        return maReCleSecrete;

        
    }

   
    

        
    }

   
    
    
