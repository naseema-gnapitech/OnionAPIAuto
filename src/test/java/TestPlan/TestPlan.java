package TestPlan;

import Config.ConfigReader;
import Config.GetTokenPasswordFlow;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class TestPlan {
    @Test
    public void getProjects() {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .when()
                .get("/testPlan/d23e800a-6a25-4f74-94d4-ac6d15b389f5/5680d517-2070-4cea-818f-208c7af1fb1c");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

@Test
public void testPlanList() throws IOException {
    String baseUrl = ConfigReader.get("base.url");
    String token = GetTokenPasswordFlow.getAccessToken();
    Response response = given().baseUri(baseUrl).
            header("Authorization", " Bearer " + token)
            .header("Content-Type", ContentType.JSON)
            .log()
            .all()
            .body( " { \"projectId\": \"d23e800a-6a25-4f74-94d4-ac6d15b389f5\"}")
            .post("/testPlan/list");
    System.out.println(response.getStatusCode());
    System.out.println(response.asString());
}

    @Test
    public void createTestPlan() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body( " { \"projectId\": \"d23e800a-6a25-4f74-94d4-ac6d15b389f5\"}")
                .post("/testPlan/{projectId}/createTestPlan");
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
                .delete("/testPlan/{projectId}/deleteTestPlan");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void testCases() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body( " { \"projectId\": \"d23e800a-6a25-4f74-94d4-ac6d15b389f5\"}")
                .post("/testPlan/testCases");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void updateTestPlanCase() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/updateTestPlanCase.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody)
                .put("/testPlan/{projectId}/updateTestPlanCase");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void testPlanCaseList() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body( " {\n" +
                        "    \"testPlanId\": \"c9be9c33-92db-47d1-94d5-eda1e6d9da47\"\n" +
                        "}")
                .post("/testPlan/testPlanCaseList");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

}