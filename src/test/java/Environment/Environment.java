package Environment;

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

public class Environment extends TestBase {

    @Test
    public void createEnvironment() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        //String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "    \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\",\n" +
                        "    \"name\": \"Test123\",\n" +
                        "    \"description\": \"This is test env\"\n" +
                        "}")
                .post("/testRun/createEnvironment")
                .then()
                .log()
                .all()
                .extract().response();
        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            test.pass("Status Code Verified successfully");
        } catch (AssertionError e) {
            test.fail("Status code mismatch! Actual=" + response.getStatusCode());
            throw e;
        }
    }

    @Test
    public void getEnvironment() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        //String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "  \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                        "}")
                .post("/testRun/getEnvironments")
                .then()
                .log()
                .all()
                .extract().response();
        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            test.pass("Status Code Verified successfully");
        } catch (AssertionError e) {
            test.fail("Status code mismatch! Actual=" + response.getStatusCode());
            throw e;
        }
    }

    @Test
    public void usedEnvironments() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        //String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "  \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                        "}")
                .post("/testRun/used-environments")
                .then()
                .log()
                .all()
                .extract().response();
        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            test.pass("Status Code Verified successfully");
        } catch (AssertionError e) {
            test.fail("Status code mismatch! Actual=" + response.getStatusCode());
            throw e;
        }
    }
    @Test
    public void updateEnvironmentDetails() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/updateEnvironmentDetails.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .put("/testRun/updateEnvironmentDetails")
                .then()
                .log()
                .all()
                .extract().response();
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            test.pass("Status Code Verified successfully");
        } catch (AssertionError e) {
            test.fail("Status code mismatch! Actual=" + response.getStatusCode());
            throw e;  // Required for FAIL status
        }
}

    @Test
    public void deleteEnvironment() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response =
                given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .body("{\n" +
                                "  \"environmentId\": \"f63fcd26-8d28-46dd-83b1-4d6a9351ac98\",\n" +
                                "  \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                                "}")
                        .when()
                        .delete("/user/deleteUser")
                        .then()
                        .log().all()
                        .extract().response();
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code mismatch! Actual=" + response.getStatusCode());
    }


}