package PropertyUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertyUtility {

    public Properties properties;                                // facem o logica pe baza careia prin faptul ca fisierul nostru extern are cam acelasi nume cu testul nostru, facem un mecanism prin care cand ruleaza testul sa caute daca exista un fisier cu acelasi nume si sa incarce automat datele


    // facem un constructor
    public PropertyUtility(String fileName) {        // Stringul reprezinta cum se numeste testul
        loadDataFile(fileName);
    }

    // metoda care incarca un fisier de tip "properties"

    private void loadDataFile(String fileName) {
        properties = new Properties();        //este obiectul care se ocupa sa ia fisierul si sa-l transforme in properties
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/test/resources/TestData/"+fileName+"Data.properties");        // si path-urile sunt de 2 tipuri : aboslute si relative; intotdeauna sa dam pathul relativ , pt ca daca dam absolut ne da adresa de pe local
            properties.load(fileInputStream);                                                                              // la linia de mai sus, trebuie sa nu hardcodam path-ul , si de aceea am pus +fileName+ si Data , care este terminatia dupa care identifica
        } catch (IOException ignored) {         // try and catch este folosit la exceptii (more actions>surround with try and catch)  si stergem "throw new runtimeexception(e) pt ca face o noua exceptie  ( try = incearca sa realizeze ce ii dam noi , iar catch( in caz ca nu merge)
        }
    }

    // metoda care returneaza toate datele din fisierul de proprietati

    public HashMap<String ,String> getAllData (){                   // constructia este de tip cheie=valoare  ,adica string=string
        HashMap<String,String> testData = new HashMap<>();
        for (String Key:properties.stringPropertyNames()){    // apoi logica care sa parcurga continutul fisierului de proprietati si sa scoata datele din el    ; trebuie facut cu for each ... pentru fiecare cheie din multimea de chei ; stringPropertyNames returneaza toate cheile care le vede din proprietatea noastra
            testData.put(Key,properties.getProperty(Key));     // accesand toate cheile, putem scoate valorile. Apoi cu aceasta linie adaugam cheia=valoarea in testdata ; valoarea din proprietate o scoatem chemand proprietatea.getProperty si parametrul cheie
        }
        return testData;
    }                                      // iar apoi trebuie sa le mapam ca sa functioneze pentru testul nostru , si mergem pachetul ObjectData.
}
