package SuperAdminUser;

import Config.ConfigReader;
import Config.GetTokenPasswordFlow;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SuperAdmin extends TestBase {

    @Test
    public void userAll(){
        test = extent.createTest("Get Current User");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .when()
                .get("/user/all")
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
    public void superAdminStatus(){
        test = extent.createTest("Get Current User");
        String token = GetTokenPasswordFlow.getAccessToken();
        String baseUrl = ConfigReader.get("base.url");
        Response response = given().baseUri(baseUrl)
                .header("Authorization", " Bearer " + token)
                .header("Content-Type", ContentType.JSON)
                .body("{\n" +
                        "  \"userId\": \"string\",\n" +
                        "  \"isSuperAdmin\": true\n" +
                        "}")
                .when()
                .patch("/user/a4168fb5-ad2b-4933-afa4-23fca4bdab1b/superadmin")
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
