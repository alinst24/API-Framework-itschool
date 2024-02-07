package Service;

import LoggerUtility.LoggerUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import groovy.util.logging.Log;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIServiceHelper {    // Aici sa facem niste metode de ajutor la logare ( similare cu elementmethods din front-end)
    // in structura acestei clase sa introducem toata compozitia de log-uri pe care o avem  : trebuie sa avem log-uri de request si de response ( deci 2 metode) ;

    // Pentru un request este necesar sa specificam endpointul ,tipul metodei , si daca are sau nu body. ( sa specificam pentru orice fel de request).
    //Pentru un response : status-ul , status code-ul si daca are sau nu body.

    // Deci clasa contine metode care afiseaza informatii despre request si response.

    public static void requestLogs(RequestSpecification requestSpecification, String path, String methodType) {
        LoggerUtility.info("==========Request info==========");
        LoggerUtility.info(getRequestUrl(path));
        LoggerUtility.info(getRequestMethod(methodType));
        LoggerUtility.info(getRequestBody(requestSpecification));
    }

    public static void responseLogs(Response response){
        LoggerUtility.info("==========Response info==========");
        LoggerUtility.info(getResponseStatusCode(response));
        LoggerUtility.info(getResponseStatus(response));
        LoggerUtility.info(getResponseBody(response));
    }

    private static String getResponseStatusCode(Response response){
        return "Response Status : "+response.getStatusLine();
    }

    private static String getResponseStatus(Response response){
        return "Response Status Code: "+response.getStatusCode();
    }

    private static String getRequestUrl(String path) {
        return "Request URL : " + "https://demoqa.com" + path;
    }

    private static String getRequestMethod(String method) {
        return "Request METHOD: " + method;
    }

    private static String getRequestBody(RequestSpecification requestSpecification) {
        String requestBody = "Request BODY: \n";      // \n forteaza sa sara la randul urmator ; pt ca daca am lasa fara, JSON-ul il baga langa si poate arata problematic
        Object object = ((RequestSpecificationImpl)requestSpecification).getBody();
        if (object != null) {
            ObjectMapper mapper = new ObjectMapper();       // cea cu com.fast...... nu primul rezultat ! !! am facut importul de sus(object mapper) !!!
            try {
                requestBody = requestBody + mapper.readTree(object.toString()).toPrettyString();                    // surround with try and catch si stergem throw ..
            } catch (JsonProcessingException e) {
            }
        }
        return requestBody;
    }

    private static String getResponseBody(Response response){
        if (response.getBody() != null){
            return "Response BODY: \n" + response.getBody().asPrettyString();
        }
        else {
            return "Response BODY \n";
        }
    }
}