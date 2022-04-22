package Steps;

import static io.restassured.RestAssured.given;





public class PruebaAPI {

    public void GetRequest(){

        given().baseUri("https://api.github.com")
        .when()
        .get("/users")
        .getBody();
    }

    public void PostRequest(){
        given()
        .baseUri("https://api.blogEjemplo.com")
        .when()
        .post("/posts", "titulo: texto");

    }

    //put es casi lo mismo 

    public void DeleteRequest(){
        given()
        .baseUri("https://api.blogEjemplo.com/posts/texto")
        .when()
        .delete();
    }
    
    // TENGO QUE SABER QUE TIPO DE AUTENTICACION ESTOY USANDO, EJ: NO PUEDO USAR AUTH_BASIC CON LA DE FORMULARIO NI VICEVERSA 
    //POBLEMAS CON LA SESION Y EL BROWSER 


    public void auth_basic(String userName, String password){

        given().auth().basic(userName, password)
        .get("AUTH_ENDPOINT")
        .then()
        .assertThat().statusCode(200);

    }

    public void formAuth(String userName, String password){

        given().auth().form(userName, password)
        .get("services")
        .then()
        .assertThat().statusCode(200);

    }
}


