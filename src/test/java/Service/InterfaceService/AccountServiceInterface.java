package Service.InterfaceService;

import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import io.restassured.response.Response;

public interface AccountServiceInterface {

    // Layerul 2

    Response createAccount(RequestAccount requestAccount);         // metodele de interfata trebuie sa fie metode abstracte , si orice metoda facem trebuie sa avem "return de response"
    Response generateToken(RequestAccountToken requestAccountToken);
    Response getSpecificAccount(String userID,String token);
}
