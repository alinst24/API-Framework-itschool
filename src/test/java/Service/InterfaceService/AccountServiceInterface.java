package Service.InterfaceService;

import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import io.restassured.response.Response;

public interface AccountServiceInterface {

    // Layerul 2

    // locul unde definim noul nostru request pe care vrem sa il faca
    Response createAccount(RequestAccount requestAccount);         // metodele de interfata trebuie sa fie metode abstracte , si orice metoda facem trebuie sa avem "return de response" ;    //reprezinta primul post pe care serviciul o poate face
    Response generateToken(RequestAccountToken requestAccountToken);     //definesti ce metoda vrei sa faca si in parametrii ii pui structura
    Response getSpecificAccount(String userID,String token);
    Response deleteSpecificUser(String userID, String token);
}
