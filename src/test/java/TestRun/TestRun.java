package TestRun;

import AuditLogs.GetTokenPasswordFlow;
import Config.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestRun {
    @Test
    public void testCases() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body( " { \"projectId\": \"7654ab96-db68-4d6b-b8f9-ba5daf1873d8\"}")
                .post("/testRun/getTestRunList");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

}
