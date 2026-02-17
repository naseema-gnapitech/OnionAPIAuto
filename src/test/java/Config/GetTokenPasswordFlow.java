package Config;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetTokenPasswordFlow {
    public static String getAccessToken() {
        Response response = given()
                .baseUri("https://dev.auth.onion.gnapitech.org")
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "password")
                .formParam("client_id", "onion-ws")
                .formParam("client_secret", "zdJlkyMcFMwrQiaPyRtxjRK7LCHpzPUh")
                .formParam("username", "onion@gnapi.tech")
                .formParam("password", "Gnapi@1234")
                .post("/realms/Katalyst/protocol/openid-connect/token");

        response.prettyPrint();
        String token = response.jsonPath().getString("access_token");
        return token;
    }
}