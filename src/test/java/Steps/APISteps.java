package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class APISteps {

// ^ ... $ = la frase empieza exactamente en ^ y termina en $ 

 @Given("^I sent a GET request to the endpoint$")
 public void sendGETRequest(){

    System.out.println("hola");

 }

 //(//d+) = valor int no hardcodeado

 @Then("^I get a list of (//d+) users$")
 public void validateListOfUsers(int expectedUserSize){

 }
}
