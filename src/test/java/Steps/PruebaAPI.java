package Steps;

import static io.restassured.RestAssured.given;

import java.util.Base64;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PruebaAPI {

    public void GetRequest(){

        given()
        .baseUri("https://api.github.com")
        .when()
        .get("/users/TheFreeRangeTester/repos")
        .getBody().toString();
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

    /*
        1- Obtener el codigo del servicio original para obtener el token
        2- Obtener el token, intercambiando el codigo que obtuvimos 
        3- Acceder al recurso protegido con nuestro token
    */

    public static String clientId = "";
    public static String redirectUri = "";
    public static String scope = "";
    public static String userName = "";
    public static String password = "";
    public static String grantType = "";
    public static String accessToken = "";

    //encriptamos nuestros datos 

    public static String encode(String str1, String str2){
        return new String(Base64.getEncoder().encode((str1+ ":" + str2).getBytes()));
    }


    //lo mandamos 

    public static Response getCode(){
        String authorization = encode(userName, password);

        return
                given()
                .header("authorization","Basic" + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type","code")
                .queryParam("cliente_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .post("/oauth/authorize")
                .then()
                .statusCode(200)
                .extract()
                .response();
                
    }

    //extraemos el codigo de la respuesta 

    public static String parseForOauthCode(Response response){
        return response.jsonPath().getString("code");
    }

    //obtenemos el token "completo"

    public static Response getToken(String authCode){
        String authorization = encode(userName, password);

        return 
                given()
                .header("authorization","Basic" + authorization)
                .contentType(ContentType.URLENC)
                .formParam("response_type", authCode)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("grant_type", grantType)
                .post("/oauth/token")
                .then()
                .statusCode(200)
                .extract()
                .response();

    }
    
    //obtenemos el token "puro" 

    public static String parseForToken(Response logiResponse){
        return logiResponse.jsonPath().getString("access_token");
    }

    //accedeos al servicio

    public static void getFinalService(){

        given().auth().oauth2(accessToken)
        .when()
        .get("/service")
        .then()
        .statusCode(200);
        
        
    }

}


