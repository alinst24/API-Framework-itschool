package Tests;

import Actions.AccountActions;
import Hooks.Hooks;
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

public class CreateUserV2Test extends Hooks {

    public String userID;

    public String username;

    public String password;            // Deci sunt niste valori pe care le vom da si trebuie pastrate tot timpul pe aceleasi

    public String token;

    public AccountActions accountActions;

    @Test
    public void metodaTest() {

        System.out.println("Step 1 : Create user");
        createUser();
        System.out.println("Step 2 : Generate token");
        generateToken();
//        System.out.println("Step 3 : Obtain new user");
//        interractNewUser();

    }

    public void createUser() {

        accountActions = new AccountActions();
        username = "Alin" + System.currentTimeMillis();
        password = "Alinache1.@";
        RequestAccount requestAccount = new RequestAccount(username, password);
        ResponseAccountSuccess responseAccountSuccess = accountActions.createNewAccount(requestAccount);   // acest createNewAccount returneaza un responseAccountSuccess si trebuie astfel salvat pentru ca de pe el vom obtine rezultatul

        userID = responseAccountSuccess.getUserID();        // vrem sa il folosim mai departe, in urmatoarele requesturi(sa scoatem de pe el ce ne intereseaza) (mai jos:)
    }

    public void generateToken() {
        accountActions = new AccountActions();

        RequestAccountToken requestAccountToken = new RequestAccountToken(username, password);
        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountToken);

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
