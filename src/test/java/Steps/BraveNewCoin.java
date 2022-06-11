package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BraveNewCoin {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I have a valid API key for the (.+) URI@")
    public void iSetTheRequestParams(String URI) {

        // buscar el VALOR en la pc
        // ------------------------------------------------------------
        // .log().all() = nos permite saber que header y body estuvimos mandando a que
        // endpoint que URI todo eso se va a imprimir.

        request = given()
                .header("x-rapidapi-key", "VALOR")
                .header("x-rapidapi-host", "VALOR")
                .contentType(ContentType.JSON)
                .baseUri(URI)
                .log().all();

    }

    @When("^I send a POST request with a valid body to the (.+) endpoint$")
    public void sentPostRequest(String endpoint) {
        // manera rustica
        String requestBody = "{\n" + " \"audience\": \"https://api.bravnewcoin.com\", \n"
                + " \"client_id\": \"VER EL VALOR EN LA PC\",\n"
                + " \"grant_type\": \"VER EL VALOR EN LA PC\",\n" + "}";

        response = request.when().body(requestBody).post(endpoint).prettyPeek();
    }
}
