package AuditLogs;

import Config.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Dashboard {
    @Test
    public void getDashboard() throws IOException {
        String token = ConfigReader.get("auth.token");
        String baseUrl = ConfigReader.get("base.url");
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"projectId\": \"6dd8dadf-c7a4-4b74-8fad-3518b639c107\"}").
                post("/dashboard/getDashboard");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void getDashboardTestSummary() throws IOException {
        String token = ConfigReader.get("auth.token");
        String baseUrl = ConfigReader.get("base.url");
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"projectId\": \"6dd8dadf-c7a4-4b74-8fad-3518b639c107\"}").
                post("dashboard/getDashboardTestRunSummary");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void getDashboardTestRun() throws IOException {
        String token = ConfigReader.get("auth.token");
        String baseUrl = ConfigReader.get("base.url");
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"projectId\": \"6dd8dadf-c7a4-4b74-8fad-3518b639c107\"}").
                post("/dashboard/getDashboardTestRun");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

}
