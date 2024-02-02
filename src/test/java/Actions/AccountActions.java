package Actions;

import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import Objects.ResponseObject.ResponseAccountSuccess;
import Objects.ResponseObject.ResponseTokenSuccess;
import Rest.RestRequestStatus;
import Service.ServiceImplementation.AccountServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountActions {

    // Ultimul layer ( de actiuni) (specificam o actiune pe care sa o faca, si actiunea in sine presupune toata constructia)
    // Aceasta clasa cuprinde actiunile focusate pe parte de Account( sa apeleze serviciul si tot ce ne intereseaza)  (si pe acelasi tipar BookStoreActions)

    // Fiecare clasa de actions trebuie sa aibe un obiect de tip Service (in acest caz : accountService)

    public AccountServiceImpl accountService;

    public ResponseAccountSuccess createNewAccount(RequestAccount requestAccount){        // facem aceasta metoda de tip ResponseAccountSucces , ca sa returnam direct obiectul response-ului

        accountService = new AccountServiceImpl();

        Response response = accountService.createAccount(requestAccount);
        Assert.assertEquals((int)RestRequestStatus.SC_CREATED,response.getStatusCode());    //aici daca apare subliniat=eroare, trebuie facut un "Cast" pentru ca nu intelege ca SC_CREATED va fi de tip integer ; si astfel punem "(int)" sa forteze.

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);      // deserealizarea
        Assert.assertNotNull(responseAccountSuccess.getUserID());
        Assert.assertEquals(responseAccountSuccess.getUsername(),requestAccount.getUserName());
        Assert.assertNotNull(responseAccountSuccess.getBooks());

        return responseAccountSuccess;      // astfel , returnam tot obiectul de body si putem scoate de pe el ce ne intereseaza
    }

    public ResponseTokenSuccess generateToken(RequestAccountToken requestAccountToken){

        accountService= new AccountServiceImpl();
        Response response = accountService.generateToken(requestAccountToken);
        Assert.assertEquals(RestRequestStatus.SC_OK,response.getStatusCode());

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);

        Assert.assertNotNull(responseTokenSuccess.getToken());
        Assert.assertNotNull(responseTokenSuccess.getExpires());
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");

        return responseTokenSuccess;
    }

}
