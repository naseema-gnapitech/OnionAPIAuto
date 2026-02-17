package Organization;

import Config.ConfigReader;
import Config.GetTokenPasswordFlow;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Organization extends TestBase {
    @Test
    public void getUserOrganizations() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "    \"userId\": \"58b91be9-94d0-4dd7-87b4-0bc975c71d96\"\n" +
                        "}")
                .when()
                .post("/organization/getUserOrganizations")
                .then()
                .log()
                .all()
                .extract().response();
        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            test.pass("Status Code Verified successfully");
        } catch (AssertionError e) {
            test.fail("Status code mismatch! Actual=" + response.getStatusCode());
            throw e;  // Required for FAIL status
        }
    }
}