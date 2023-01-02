import javax.crypto.SecretKey;


public class App {
    public static void main(String[] args) throws Exception {
        boolean motDePasse = true;

        

        if (motDePasse) {

            //Utiliser un mot de passe et un salt pour generer la cle secrete
            SecretKey maCleSecrete = MansaCrypt.genererCleMotDePasse("motDepasse", "plussure");
            System.out.println("Cle a partir de mot de passe " + maCleSecrete);
            System.out.println("Pseudo Aleatoire genere : " + MansaCrypt.generateurPseudoAleatoire().getIV());





            

            
        } else {

            // Generer aleatoirement la Cle Secrete

            SecretKey myKey = MansaCrypt.genererCleSecrete("AES", 256);

            System.out.println(myKey); 
            
        }

        

       
        
    }
}
