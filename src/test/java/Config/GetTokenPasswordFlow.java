package Config;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class GetTokenPasswordFlow {
    public static void main(String[] args) {
        Response response = given()
                .baseUri("https://dev.auth.onion.gnapitech.org")
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "password")
                .formParam("client_id", "your-client-id")
                .formParam("username", "onion@gnapi.tech")
                .formParam("password", "Gnapi@1234")
                .post("/realms/Onion/protocol/openid-connect/token");

        response.prettyPrint();
        String accessToken = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
    }
}
