package ImportTestCase;

import Config.ConfigReader;
import Config.GetTokenPasswordFlow;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ImportTestCase extends TestBase {
    @Test
    public void importTestCase() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");

        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .header("Authorization", " Bearer " + token)
                .body(reqBody)
                .post("/bulkTestCaseGeneration")
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
