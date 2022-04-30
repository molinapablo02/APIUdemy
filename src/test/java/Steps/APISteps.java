package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class APISteps {


private static RequestSpecification request;
private Response response;
private ValidatableResponse json;

// ^ ... $ = la frase empieza exactamente en ^ y termina en $ 
 @Given("^I sent a GET request to the endpoint$")
 public void sendGETRequest(){

   //given de rest assured, no cucumber
   //content type es los q nos devuelve, en este caso un json 
    request = given()
               .baseUri("https://api.github.com")
               .contentType(ContentType.JSON);
 }

 //(//d+) = valor int no hardcodeado

 @Then("^I get a list of (//d+) users$")
 public void validateListOfUsers(int expectedStatusCode){
   response = request
               .when()
               .get("/users/TheFreeRangeTester/repos");

               json = response.then().statusCode(expectedStatusCode);
               

 }
}
