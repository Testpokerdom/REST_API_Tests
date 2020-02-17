package ControlersTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
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
    private String task_id = null;

    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "https://mobile.104.ua/billing/";
        //RestAssured.baseURI = "https://api.trello.com";
        //RestAssured.basePath = "/1/boards";
    }
/*
    @Test
    public void getRequsetBoardInfo() {
        //Response res =
        given()
                .param("key", "8d766f6e379168515a4993104c937c80")
                .param("token", "46af8f3f4e6d93d572e45635479619522ccd2e1dff3e96ed947b1c2594c9ac29")
                .when()
                .get("/5d826642c999291ed8290832")
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("id", equalTo("5d826642c999291ed8290832"))
                .and()
                .body("prefs.backgroundColor",  equalTo("#0079BF"))
                .contentType(ContentType.JSON);
        // System.out.println(res.body().prettyPrint());
    }
    @Test (dependsOnMethods = {"getRequsetBoardInfo"})
    public void getRequsetBoardCards() {
//	 Response res =
        given()
                .param("key", "8d766f6e379168515a4993104c937c80")
                .param("token", "46af8f3f4e6d93d572e45635479619522ccd2e1dff3e96ed947b1c2594c9ac29")
                .when()
                .get("/5d80fed45ea7514f279bc675/cards")
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("id[0]", equalTo("5d811b257b35c24d2c87a5bf"))
                .contentType(ContentType.JSON);

//	 System.out.println(res.body().prettyPrint());
    }
    */

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
        JSONObject jsonresponce = new JSONObject(response.extract().asString());
        session_id = jsonresponce.getString("session_id");
        controllers_id = jsonresponce.getString("controller_id");
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
                //.and()
                //.body("data.task_id[4]", equalTo("85322713"));

        JSONObject jsonresponce = new JSONObject(response.extract().asString());
        System.out.println("task_id id: " + jsonresponce);
        task_id = jsonresponce.getString("task_id[4]");
        System.out.println("task_id id: " + task_id);

    }

}
