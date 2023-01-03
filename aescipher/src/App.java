import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class App {
    public static void main(String[] args) throws Exception {
        boolean motDePasse = true;

        

        if (motDePasse) {

            //Utiliser un mot de passe et un salt pour generer la cle secrete
            SecretKey maCleSecrete = MansaCrypt.genererCleMotDePasse("motDepasse", "plussure");
            System.out.println("Cle a partir de mot de passe " + maCleSecrete);
            System.out.println("Pseudo Aleatoire genere : " + MansaCrypt.generateurPseudoAleatoire().getIV());


            String path = System.getProperty("user.dir");
            System.out.println(path);

        System.out.println(path);
        String fichier = "plaintext.txt";
        String destination = "encrypted.txt";

        SecretKey maCle = MansaCrypt.genererCleMotDePasse("Hacker", "Good");
        String algorithme =  "AES/CBC/PKCS5Padding"; 
        IvParameterSpec genAlea = MansaCrypt.generateurPseudoAleatoire();

        

        Chiffrement.chiffrerFichier(fichier, destination, algorithme, maCle, genAlea);
        System.out.println(maCle);
        Dechiffrement.dechiffrerFichier(destination, "decrypter.txt", algorithme, maCle, genAlea);
        System.out.println(maCle); 




            

            
        } else {

            // Generer aleatoirement la Cle Secrete

            SecretKey myKey = MansaCrypt.genererCleSecrete("AES", 256);

            System.out.println(myKey); 
            
        }

        

       
        
    }
}
