package Tests;

import Actions.AccountActions;
import Hooks.Hooks;
import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import Objects.ResponseObject.ResponseAccountAuthSuccess;
import Objects.ResponseObject.ResponseAccountSuccess;
import Objects.ResponseObject.ResponseTokenSuccess;
import PropertyUtility.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserV2Test extends Hooks {

    public String userID;
    // le facem variabile globale, sa le poti folosi in toate requesturile si de create si de auth si de delete etc..

    //    public String username;
//
//    public String password;            // Deci sunt niste valori pe care le vom da si trebuie pastrate tot timpul pe aceleasi
    public String token;

    public AccountActions accountActions;

    public RequestAccount requestAccount;

    @Test
    public void metodaTest() {          //facem intregul flow in acest test pentru a pastra logica de creare user, autentificare, token

        //Adica facem aici toate REQUESTURILE DE BACKEND

        System.out.println("Step 1. Create User");
        createUser();
        System.out.println();
        System.out.println("Step 2. Generate Token");
        generateToken();
        System.out.println();
        System.out.println("Step 3. Obtain new user");
        interractNewUser();
        System.out.println();
        System.out.println("Step 4. Delete new user");
        deleteSpecificUser();
        System.out.println();
        System.out.println("Step 5. Obtain new user");     // sa vedem daca chiar s-a sters
        interractNewUser();

    }

    public void createUser() {

        accountActions = new AccountActions();

        PropertyUtility propertyUtility = new PropertyUtility("CreateUser");

        requestAccount = new RequestAccount(propertyUtility.getAllData());
        ResponseAccountSuccess responseAccountSuccess = accountActions.createNewAccount(requestAccount);   // acest createNewAccount returneaza un responseAccountSuccess si trebuie astfel salvat pentru ca de pe el vom obtine rezultatul

        userID = responseAccountSuccess.getUserID();        // vrem sa il folosim mai departe, in urmatoarele requesturi(sa scoatem de pe el ce ne intereseaza) (mai jos:)
    }

    public void generateToken() {
        accountActions = new AccountActions();

        RequestAccountToken requestAccountToken = new RequestAccountToken(requestAccount.getUserName(), requestAccount.getPassword());
        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountToken);

        token = responseTokenSuccess.getToken();
    }

    // Facem un GET pentru user-ul creat(generat)

    public void interractNewUser() {

        accountActions = new AccountActions();
        accountActions.obtainSpecificAccount(userID, token, requestAccount.getUserName());
    }

    //Stergem noul user creat

    public void deleteSpecificUser(){
        accountActions = new AccountActions();
        accountActions.deleteSpecificAccount(userID, token);
    }
}
