package AuditLog;

import Config.ConfigReader;
import Config.GetTokenPasswordFlow;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AuditLog extends TestBase {

    @Test
    public void getAuditLog() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
       // String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/addUser.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "  \"projectId\": \"4d74b8ca-cd35-4663-bd28-733cf9815658\",\n" +
                        "  \"page\": 1,\n" +
                        "  \"limit\": 10\n" +
                        "}")
                .when()
                .post("/auditLog/getAuditLogs")
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
