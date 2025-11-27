package TestCase;

import AuditLogs.GetTokenPasswordFlow;
import Config.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class TestCase {
    @Test
    public void getTestCaseById() {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .when()
                .get("/testCase/68dfa5f4-69c6-4d1d-a00a-9b05dd39fd2b/20931ef6-9a3b-4535-9532-eac4aa0d2568");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void createTestCase() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/createTestCaseWithMandatoryFields.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody)
                .post("/testCase/createTestCase");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void updateTestCase() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        // String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/createTestCaseWithMandatoryFields.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"testCase\": {\n" +
                        "    \"id\": \"fb169623-6ca1-43bd-ab64-cb0ffa3b88b6\",  \n" +
                        "    \"title\": \"I have edited this testcase1\"    \n" +
                        "}}")
                .put("/testCase/updateTestCase");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void deleteTestCase() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = (Response) given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"id\": \"123e4567-e89b-12d3-a456-426614174000\"\n" +
                        "}")
                .when()
                .delete("/testCase/48af8633-9574-4b3e-bdf5-72a2a99c1b9c/deleteTestCase");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }


    @Test
    public void getTestCaseList() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/createTestCaseWithMandatoryFields.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"projectId\": \n" +
                        "\"cff96902-99fb-4147-b68b-14a667df84ff\"}")
                .post("/testCase/getTestCaseList");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        //a8d0bc4a-8ed2-4952-859a-27cf2c43cb04 //eeb9d42b-a933-447c-97b8-0af5f82ae7e7
    }

    @Test
    public void approveTestCase() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"projectId\": \"8756b902-ed8a-4d07-b7cb-82dd7a257a49\",\n" +
                        "  \"testCaseId\": \"a1eda6e7-569e-4271-b377-657099673a8b\",\n" +
                        "  \"approvalStatus\": \"Pending\",\n" +
                        "  \"approverId\": \"e8cdf874-846e-4812-a0cc-89cbb9e2e0e5\",\n" +
                        "  \"comment\": \"Testcase one\"\n" +
                        "}")
                .patch("/testCase/8756b902-ed8a-4d07-b7cb-82dd7a257a49/approveTestCase");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
}