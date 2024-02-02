package Objects.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseAccountSuccess {          // La request il construiam, dar aici nu mai construim. Asa cum ne da response-ul asa il tratam.

    @JsonProperty("userID")              //Adnotarile sunt folosite in functie de structura requestului(json,xml).    ; asa facem maparea unei variabile pe care response-ul o are.
    private String userID;

    @JsonProperty("username")
    private String username;

    @JsonProperty("books")                // mai intai trebuie sa facem clasa "BookObject"
    private List<BookObject> books;          // trebuie sa facem un obiect separat care sa reprezinte o structura de books ; books-ul este un array si contine(isbn,title,subtitle etc). Folosim conceptul de compozitie pentru a lua un obiect dintr-un obiect.
                                                // Astfel , facem o Lista <> de clasa respectiva
    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<BookObject> getBooks() {
        return books;
    }
}
