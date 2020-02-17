package ControlersTests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ControllersLoGOn {
    private TestData testData = new TestData();

    private String xApplicationKeyControlsApp = testData.getxApplicationKeyControlsApp();
    private String logon_name = testData.getLogin();
    private String passwword = testData.getPasswordConrolApp();
    private String session_id = null;
    private String controllers_id = null;

    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "https://mobile.104.ua/billing/";
    }

    @Test()
    public void test0Login(){
        ValidatableResponse response;
        response = given()
                .header("X-Application-Key", xApplicationKeyControlsApp)
                .param("password", passwword)
                .param("logon_name", logon_name)
                .when()
                .post("api/v2/controllers/sessions")
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("controller_id", equalTo("22415385"));
        JSONObject jsonresponce = new JSONObject(response.extract().asString());
        session_id = jsonresponce.getString("session_id");
        controllers_id = jsonresponce.getString("controller_id");
    }

    @Test()
    public void test1GetControllersTasks(){
        ValidatableResponse response;
        response = given()
                .header("X-Application-Key", xApplicationKeyControlsApp)
                .header("X-Session-Id", session_id)
                .param("task_date", "2019-12-09")
                .param("pjscs_id", 21)
                .param("rgc_controller_id", controllers_id)
                .when()
                .post("api/v2/controllers/tasks")
                .then()
                .assertThat().statusCode(200);
    }

}
