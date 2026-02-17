package Project;

import Config.GetTokenPasswordFlow;
import com.github.javafaker.Faker;
import Config.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.given;

public class Project {


    @Test
    public void getOrganizationProjects() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getOrganizationProjects.json")));
        String replace = reqBody.replace("2701Project", new Faker().name().name()).replace("description", new Faker().name().name());
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody).
                post("/project/getOrganizationProjects");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        System.out.println("TOKEN = " + token);

    }

    @Test
    public void addProject() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/addProject.json")));
        String replace = reqBody.replace("Project Gnapi", new Faker().name().name()).replace("description", new Faker().name().name());
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody).
                post("/project/addProject");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void ProjectById() {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .when()
                .get("/project/ea2defd8-2bd0-4d9a-bfa5-13804eb7b109");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

    }

    @Test
    public void updateProject() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"description\": \"Update124\",\n" +
                        "\"name\": \"2701Project\",\n" +
                        "\"projectId\": \"ea2defd8-2bd0-4d9a-bfa5-13804eb7b109\"}")
                .put("/project/updateProject");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void deleteProject() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = (Response) given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"projectId\": \n" +
                        "\"241928b0-a064-4184-b9a1-45cfbd768711\"}")
                .when()
                .delete("/project/archiveProject");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void getProjectUsers() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
             Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"projectId\": \"ea2defd8-2bd0-4d9a-bfa5-13804eb7b109\"\n" +
                        "}").
                post("/project/getProjectUsers");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void switchProjectUserRole() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"projectId\": \"48af8633-9574-4b3e-bdf5-72a2a99c1b9c\",\n" +
                        "  \"userId\": \"72930240-7ded-4f70-935b-d0ff7a1c9dd1\",\n" +
                        "  \"role\": \"Admin\"\n" +
                        "}").
                post("/project/switchProjectUserRole");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void assignProjectUsers() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        //String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/assignProjectUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\n" +
                        "  \"projectId\": \"48af8633-9574-4b3e-bdf5-72a2a99c1b9c\",\n" +
                        "  \"userId\": \"72930240-7ded-4f70-935b-d0ff7a1c9dd1\",\n" +
                        "  \"role\": \"Admin\"\n" +
                        "}")
                .post("/project/assignProjectUsers");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    public void deleteProjectUser() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        Response response = (Response) given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"userId\": \"719ea14c-2d9f-4d9a-8c06-7561096d6c38\",\n" +
                        "  \"projectId\": \"68dfa5f4-69c6-4d1d-a00a-9b05dd39fd2b\"}")
                .when()
                .post("user/deleteProjectUser");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void missingProjectDetails() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/addProjectMissingValues.json")));
        Response response = given().baseUri(baseUrl)
               // header("Authorization", " Bearer " + token)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody).
                post("/project/addProject");
        System.out.println(response.asString());
    }

    @Test
    public void auditLogs() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(reqBody).
                post("/auditLog/getAuditLogs");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test
    public void getUserOrganizations() throws IOException {
        String baseUrl = ConfigReader.get("base.url");
        String token = GetTokenPasswordFlow.getAccessToken();
      //  String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/getUsers.json")));
        Response response = given().baseUri(baseUrl).
                header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body("{\"userId\": \"72930240-7ded-4f70-935b-d0ff7a1c9dd1\"}").
                post("/organization/getUserOrganizations");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }



}