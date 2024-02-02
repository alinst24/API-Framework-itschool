package Tests;

import Actions.AccountActions;
import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import Objects.ResponseObject.ResponseAccountAuthSuccess;
import Objects.ResponseObject.ResponseAccountSuccess;
import Objects.ResponseObject.ResponseTokenSuccess;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest {

    public String userID;

    public String username;

    public String password;            // Deci sunt niste valori pe care le vom da si trebuie pastrate tot timpul pe aceleasi

    public String token;

    public AccountActions accountActions;

    @Test
    public void metodaTest() {

        System.out.println("Step 1 : Create user");
        createUser();
//        System.out.println("Step 2 : Generate token");
//        generateToken();
//        System.out.println("Step 3 : Obtain new user");
//        interractNewUser();

    }

    public void createUser() {

// Un request facut de tip 201 care creeaza o resursa :

        // Definim caracteristicile clientului
//        RequestSpecification requestSpecification = RestAssured.given();            // prin aceasta linie de cod vrem sa configuram clientul cu anumite specificatii
//        requestSpecification.baseUri("https://demoqa.com");       // adica URL de baza pe care vrem sa il configuram
//        requestSpecification.contentType("application/json");       // Trebuie sa ii specificam ca e content de tip JSON


        // Configuram request-ul     (La un request ne trebuie 3 chestii : URL(il avem), HEADER-ul ( sa ia configurari de pe client) si BODY-ul( este structura aceea de JSON completata de noi cu user si parola)
        // Ca sa facem un BODY de tip JSON , trebuie sa definim un JSON object

        accountActions = new AccountActions();
        username = "Alin" + System.currentTimeMillis();
        password = "Alinache1.@";
        RequestAccount requestAccount = new RequestAccount(username, password);
        ResponseAccountSuccess responseAccountSuccess = accountActions.createNewAccount(requestAccount);

//        username = "Alin" + System.currentTimeMillis();           // ca sa ne dea de fiecare data alt user(pt ca altfel ar fi zis mereu existed user), folosim o metoda care returneaza timpul curent raportat in milisecunde
//        password = "Alinache1.@";                     // Am sters "String" pentru a lua variabila de sus, sa fie globala , nu vizibila doar in metoda

//        JSONObject requestBody= new JSONObject();     //construim un body de tipul Json, functioneaza ca un fel de hashmap (cheie - valoare)
//        requestBody.put("userName",username);      //stabilim cheia-valoarea
//        requestBody.put("password","Alinache1.@");       //stabilim cheia-valoarea
        // Astfel am configurat requestul

        // Apoi trebuie sa atasam BODY-ul acesta pe constructia de sus    (pt ca vrem ca atunci cand se face requestul sa respecte un anumit BODY)
//        RequestAccount requestAccount = new RequestAccount(username, password);
//        requestSpecification.body(requestAccount);     // astfel i-am spus la requestul nostru ca vrem sa contina body-ul facut mai sus

        // Accesam response-ul
//        Response response = requestSpecification.post("/Account/v1/User");       // accesam response-ul trimitand un request (de tip POST)
//        ResponseBody body = response.getBody();
//        body.prettyPrint();       // prettyPrint este un fel de "sout" deci nu folosim si sout

        // Validam statusul request-ului
//        Assert.assertEquals(response.getStatusCode(), 201);
//
//        // Validam response BODY
//        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);
////        System.out.println(responseAccountSuccess.getUserID());
//
//        Assert.assertNotNull(responseAccountSuccess.getUserID());       // verificam ca exista o valoare pentru field    ; nu am folosit Equals pentru ca mereu va fi unic
//        Assert.assertEquals(responseAccountSuccess.getUsername(), username);      // verificam ca username are valoarea din request
//        Assert.assertNotNull(responseAccountSuccess.getBooks());           // chiar daca este goala, validam ca nu este nulla , adica are acele paranteze drepte ( este diferit de empty)

        userID = responseAccountSuccess.getUserID();
    }

    // Facem un request care ne genereaza un token
    public void generateToken() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.contentType("application/json");

        RequestAccountToken requestAccountToken = new RequestAccountToken(username, password);
        requestSpecification.body(requestAccountToken);

        // Accesam response-ul
        Response response = requestSpecification.post("/Account/v1/GenerateToken");
        ResponseBody body = response.getBody();
        body.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);

        Assert.assertNotNull(responseTokenSuccess.getToken());
        Assert.assertNotNull(responseTokenSuccess.getExpires());
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");

        token = responseTokenSuccess.getToken();
    }

    // Facem un GET pentru user-ul creat(generat)

    public void interractNewUser() {

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.contentType("application/json");
        requestSpecification.header("Authorization", "Bearer " + token);

        Response response = requestSpecification.get("/Account/v1/User/" + userID);    // Compunere de endpoint din url+userID
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseAccountAuthSuccess responseAccountAuthSuccess = response.body().as(ResponseAccountAuthSuccess.class);

        Assert.assertNotNull(responseAccountAuthSuccess.getUserId());
        Assert.assertEquals(responseAccountAuthSuccess.getUsername(), username);
        Assert.assertNotNull(responseAccountAuthSuccess.getBooks());
    }
}
