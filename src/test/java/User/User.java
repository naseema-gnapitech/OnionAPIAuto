package User;


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


public class User extends TestBase {
    @Test
    public void getCurrentUser() {
        test = extent.createTest("Get Current User");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .when()
                .get("/user/getCurrentUser")
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
    public void getProjects() {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .when()
                .get("/user/getProjects")
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
    public void getUsers() throws IOException {
        test = extent.createTest("getProjects");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .body(reqBody).
                post("/user/getUsers")
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
    public void inviteUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/addUser.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .post("/user/inviteUser")
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
    public void updateUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
       String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/updateUser.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .put("/user/updateUser")
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
    public void deleteUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = (Response) given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "  \"userId\": \"689727e5-1ed0-466f-bf5a-3a2a6ace93b4\"\n" +
                        "}")
                .when()
                .delete("user/deleteUser")
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
    public void searchUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = (Response) given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "  \"searchTerm\": \"Neha\"\n" +
                        "}")
                .when()
                .post("user/searchUser")
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
    public void changePassword() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "  \"userId\": \"3faf6a65-28ea-4cc9-8c35-de3dd5cf81af\",\n" +
                        "  \"newPassword\": \"newSecureP@ssword1234\"\n" +
                        "}")
                .when()
                .post("/user/changepassword")
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
    public void updateCurrentUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/updateCurrentUser.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .post("/user/updateCurrentUser")
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
    public void addUser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/addUser.json")));
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .post("/user/inviteUser")
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
    public void updateuser() throws IOException {
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/updateUser.json")));
        Response response = (Response) given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body(reqBody)
                .when()
                .put("/user/updateUser")
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



}
