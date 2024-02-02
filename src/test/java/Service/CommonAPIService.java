package Service;

import Rest.RestRequest;
import Rest.RestRequestType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonAPIService {

    // Aceasta clasa contine metode pentru tipuri de requesturi cu diferiti parametrii
    public Response post(Object body,String url){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);

        APIServiceHelper.requestLogs(requestSpecification,url,RestRequestType.REQUEST_POST);

        Response response = performRequest(RestRequestType.REQUEST_POST,requestSpecification,url);
        return response;
    }            // prima metoda facuta sa functioneze pentru orice fel de metoda post, care are un body si url

    public Response post(Object body,String url,String token){                  // polimorfism static
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        requestSpecification.body(body);

        // de pus logg-urile pentru request si response

        Response response = performRequest(RestRequestType.REQUEST_POST,requestSpecification,url);
        return response;
    }                 // aceasta metoda are in plus autorizarea (un post cu autorizare)

    public Response get(String url,String token){                // get-ul nostru era cu autorizare si nu avea body, doar url si token
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);

        // de pus logg-urile pentru request si response

        Response response = performRequest(RestRequestType.REQUEST_GET,requestSpecification,url);
        return response;
    }

    // facem o instanta de RestRequest care sa apeleze metoda de performRequest
    private Response performRequest(String requestType,RequestSpecification requestSpecification,String url){
        return new RestRequest().performRequest(requestType,requestSpecification,url);
    }

}
