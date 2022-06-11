package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class APISteps {

  private static RequestSpecification request;
  private Response response;
  private ValidatableResponse json;

  // ^ ... $ = la frase empieza exactamente en ^ y termina en $
  // given de rest assured, no cucumber
  // content type es los q nos devuelve, en este caso un json
  // (.+) recibe un String
  @Given("^I sent a GET request to the (.+) URI$")
  public void sendGETRequest(String URI) {

    request = given()
        .baseUri(URI)
        .contentType(ContentType.JSON);
  }

  // (//d+) = valor int no hardcodeado

  @Then("^I get a list of (\\d+) users$")
  public void validateListOfUsers(int expectedStatusCode) {

    response = request
        .when()
        .get("/users/TheFreeRangeTester/repos");

    json = response.then().statusCode(expectedStatusCode);

  }

  // getList("$") = que obtenga todas las entradas dentro de la respuesta json que
  // estamos recibiendo
  @Then("^I validate there are {int} items on the \\/users endpoint$")
  public void validateSize(int expectedSize, String endpoint) {

    response = request
        .when()
        .get(endpoint);

    List<String> jsonResponse = response.jsonPath().getList("$");
    int actualSize = jsonResponse.size();
    assertEquals(expectedSize, actualSize);

  }

  @Then("^I validate there is a value: (.+) in the response at (.+) endpoint$")
  public void validateValue(String expectedValue, String endpoint) {

    response = request
        .when()
        .get(endpoint);

    // como comparar el valor esperado con el primer valor que encuentre, poco
    // practico
    List<String> jsonResponse = response.jsonPath().getList("username");
    // String actualValue = jsonResponse.get(0);

    // primero el valor esperado, el valor original
    // assertEquals(expectedValue, actualValue);

    assertTrue("el valor " + expectedValue + "no fue encontrado en la lista", jsonResponse.contains(expectedValue));

  }

  @Then("^I can validate the nested value: (.+) on the response at (.+) endpoint$")
  public void validateNestedValue(String expectedValue, String endpoint) {

    response = request
        .when()
        .get(endpoint);

    String jsonResponse = response.jsonPath().getString("addres.street");

    assertTrue(jsonResponse.contains(expectedValue));
  }
}