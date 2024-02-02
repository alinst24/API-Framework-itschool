package Rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestRequest {

    // Trebuie sa fac o metoda care sa execute un request la un endpoint
    public Response performRequest(String requestType,RequestSpecification requestSpecification, String url){
        switch (requestType){
            case RestRequestType.REQUEST_DELETE:
                return prepare(requestSpecification).delete(url);
            case RestRequestType.REQUEST_GET:
                return prepare(requestSpecification).get(url);
            case RestRequestType.REQUEST_POST:
                return prepare(requestSpecification).post(url);
            case RestRequestType.REQUEST_PUT:
                return  prepare(requestSpecification).put(url);
        }
        return null;
    }

    // Trebuie sa configurez setarile pentru client
    public RequestSpecification prepare (RequestSpecification requestSpecification){
        requestSpecification.baseUri("https://demoqa.com");        //url de baza
        requestSpecification.contentType("application/json");       // sa primeasca requesturi de tip json
        return requestSpecification;
    }

}
