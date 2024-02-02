package Tests;

import Objects.RequestObject.RequestAccount;
import Objects.ResponseObject.ResponseAccountFailed;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserInvalidTest {

    public String username;

    public String password;

    @Test
    public void metodaTest(){
        System.out.println("Step 1 : Create user");
        createUser();
    }

    public void createUser(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.contentType("application/json");

        username = "Alin";
        password = "Alinache1.@";

        RequestAccount requestAccount = new RequestAccount(username, password);
        requestSpecification.body(requestAccount);

        Response response = requestSpecification.post("/Account/v1/User");
        ResponseBody body = response.getBody();
        body.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 406);                  // Ex: codul il putem vedea daca completam si dam try it out >execute

        ResponseAccountFailed responseAccountFailed = response.body().as(ResponseAccountFailed.class);
        Assert.assertEquals(responseAccountFailed.getCode(),"1204");
        Assert.assertEquals(responseAccountFailed.getMessage(),"User exists!");
    }
}
