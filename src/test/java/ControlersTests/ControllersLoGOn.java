package ControlersTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ControllersLoGOn {
    private TestData testData = new TestData();

    private String xApplicationKeyControlsApp = testData.getxApplicationKeyControlsApp();
    private String logon_name = testData.getLogin();
    private String passwword = testData.getPasswordConrolApp();
    private String session_id = null;
    private String controllers_id = null;
    private String task_id = null;
    private Object task_idd = null;
    private JSONArray task_iddd = null;

    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "https://mobile.104.ua/billing/";

    }

    @Test(priority = 0)
    public void testLogin(){
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
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        session_id = jsonResponse.getString("session_id");
        controllers_id = jsonResponse.getString("controller_id");
        System.out.println("Session id: " + session_id);
        System.out.println("Controller id: " + controllers_id);
    }

    @Test(priority = 1)
    public void testGetControllersTaskList(){
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
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        System.out.println("Response body: " + jsonResponse);
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        System.out.println("Response body: " + jsonResponse);
        JSONObject jsonTaskId = jsonArray.getJSONObject(4);
        System.out.println("Task id array: " + jsonTaskId);
        String status_message = jsonTaskId.getString("task_id");
        Assert.assertEquals(status_message, "85322713");

        //task_id = jsonTaskId.getString("task_id");
        /*
        JSONObject jsonTaskNumber = jsonTaskId.getJSONObject("task_id");

        System.out.println("task_id id: " + jsonTaskNumber);
*/
        /*
        JSONObject jsonresponce = new JSONObject(response.extract().asString());
        System.out.println("task_id is: " + jsonresponce);
        task_id = jsonresponce.getString("data.task_id");
        System.out.println("task_id id: " + task_id);
*/
    }

}
