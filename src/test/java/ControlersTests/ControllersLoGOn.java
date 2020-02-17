package ControlersTests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
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
    private int task_id = 0;
    private String user_id = null;


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
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        JSONObject jsonTaskId = jsonArray.getJSONObject(4);
        task_id = jsonTaskId.getInt("task_id");
        System.out.println("Task id: " + task_id);
        Assert.assertEquals(task_id, 85322713);
    }

    @Test(priority = 1)
    public void testGetUsersInfo(){
        ValidatableResponse response;
        response = given()
                .header("X-Application-Key", xApplicationKeyControlsApp)
                .header("X-Session-Id", session_id)
                .param("task_date", "2019-12-09")
                .param("pjscs_id", 21)
                .param("rgc_controller_id", controllers_id)
                .when()
                .post("api/v2/controllers/all")
                .then()
                .assertThat().statusCode(200);

        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONArray jsonArray = jsonResponse.getJSONArray("tasks");
        System.out.println(jsonArray);
        //JSONArray jsonArray2 = jsonArray.getJSONArray(1);
        JSONArray jsonTaskId = jsonArray.getJSONArray(3);
        user_id = jsonArray.getString(0);
        System.out.println("Task id: " + user_id);
        Assert.assertEquals(user_id, 424201251);
    }


}
