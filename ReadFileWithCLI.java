import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFileWithCLI {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Create stream connection between Java and the input file
        FileInputStream monFichier = new FileInputStream(args[0]);

        // Create stream connection between Java and the output file
        FileOutputStream encryptedFile = new FileOutputStream(args[1]);

        // lire le contenu
        // int contenu = monFichier.read();
        int contenu;

        // lire le contenu

        while ((contenu = monFichier.read()) != -1) {

            System.out.println((char) contenu);
            encryptedFile.write((char)contenu);

        }

        // afficher le contenue
        // System.out.println((char) contenu);

        monFichier.close();
        encryptedFile.close();

    }
    
}
