package Service;

import Rest.RestRequest;
import Rest.RestRequestType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonAPIService {

    // Aceasta clasa contine metode pentru tipuri de requesturi cu diferiti parametrii

    //serviciu comun ex. "Account" , "B
    //aceasta clasa contine metode pentru tipuri de requesturi cu diferiti parametrii
    //unele requesturi pot sa  aiba body, altele pot sa aiba body + auth etc
    public Response post(Object body, String url) {
        //un post ce contine un body + url
        //pun body-ul
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);

        APIServiceHelper.requestLogs(requestSpecification, url, RestRequestType.REQUEST_POST); //pentru logg-uri

        // trebuie facut response-ul
        Response response = performRequest(RestRequestType.REQUEST_POST, requestSpecification, url);

        APIServiceHelper.responseLogs(response);
        return response;
    }            // prima metoda facuta sa functioneze pentru orice fel de metoda post, care are un body si url

    public Response post(Object body, String url, String token) {                  // polimorfism static
        //un post ce contine un body + url + auth
        //pun body-ul + pun tokenul
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        requestSpecification.body(body);

        //trebuie facut response-ul
        Response response = performRequest(RestRequestType.REQUEST_POST, requestSpecification, url);
        return response;
    }                 // aceasta metoda are in plus autorizarea (un post cu autorizare)

    // facem o instanta de RestRequest care sa apeleze metoda de performRequest
    private Response performRequest(String requestType,RequestSpecification requestSpecification,String url){
        return new RestRequest().performRequest(requestType,requestSpecification,url);
    }

    public Response get(String url, String token) {                // get-ul nostru era cu autorizare si nu avea body, doar url si token
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);

        APIServiceHelper.requestLogs(requestSpecification, url, RestRequestType.REQUEST_GET);

        // trebuie facut response-ul
        Response response = performRequest(RestRequestType.REQUEST_GET, requestSpecification, url);
        APIServiceHelper.responseLogs(response);
        return response;
    }
    public Response delete(String URL, String token){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);

        APIServiceHelper.requestLogs(requestSpecification, URL, RestRequestType.REQUEST_DELETE);

        //trebuie facut response-ul
        Response response = performRequest(RestRequestType.REQUEST_DELETE, requestSpecification, URL);
        APIServiceHelper.responseLogs(response);
        return response;

    }

}
