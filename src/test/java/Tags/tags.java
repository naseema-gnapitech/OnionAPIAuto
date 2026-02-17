package Tags;

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

public class tags extends TestBase {
    @Test
    public void createTag() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "    \"name\": \"smokee\",\n" +
                        "\"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                        "}\n")
                .post("/tag/create")
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

    @Test
    public void getTags() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "    \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                        "}")
                .post("/tag/getTags")
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

    @Test
    public void updateTag() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "    \"id\": \"7feedf8c-1cd0-4637-8992-eb12a2aa5ca9\",\n" +
                        "    \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\",\n" +
                        "    \"name\": \"Jagery\"\n" +
                        "}")
                .post("/tag/update")
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
    @Test
    public void deleteTag() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/importTestCase.json")));
        Response response = given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", " Bearer " + token)
                .body("{\n" +
                        "    \"tagId\": \"ef275309-0cb1-4cdf-b5c6-94f8a3bb18b7\",\n" +
                        "    \"projectId\": \"2d8828d7-3fc0-4297-bb07-b122d92f797e\"\n" +
                        "}")
                .post("/tag/delete")
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
