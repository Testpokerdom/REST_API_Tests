package ControlersTests;

import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import java.util.HashMap;

public class TestData {
    private static HashMap<String, String> getTestData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("account","0801042832"); // test account number test
        map.put("userMail","aleksandra020189@gmail.com"); //test account credentials email
        map.put("password","Qwerty1234"); //test account credentials password
        map.put("counter_serial","01250847"); //test account  accounters number
        map.put("userPhone", "+380951865573"); //test account phone number
        map.put("userAccountersName", "Счетчик METRIX-U G4"); //test account accounter's name
        map.put("xApplicationKey","d8fda55bb54d984056666427e36345620967888b"); // app key - Личный кабинет 104.ua
        map.put("device_id","862400045447447"); // test device ID
        map.put("userMailForRecoverPass", "0200141729API@mailinator.com"); // test email for passwods recover test
        map.put("accountForRegistration", "0200149300"); // test account number for registration tests
        map.put("sessionIDWithSubsidy","47b9270874afa71fa59898f889d4835039ba939c"); // sessionID with subsidy data
        map.put("xApplicationKeyIBox","a02a70210b0eb63e4d725b4e7823c90d0eb63e4d"); // app key Payment Box 1
        map.put("xApplicationKeyPaymentSuppliers1","8a6723c5616a9332ddcb4a514fd4bdc4cb4a58a0"); // app key PaymentSuppliers 1
        map.put("xApplicationKeyPaymentSuppliersIPay","c02ec005495119723c561171d83b4232683e9927"); // app key PaymentSuppliers iPay
        map.put("xApplicationKeyTelecomSuppliersKyivstar","88dab8025ceb87560a28e75f2899351625ceb875"); // app key TelecomSuppliers Kyivstar
        map.put("xApplicationKeyCRM","ab724b37a39a737518aba0bbf2422f7edcf8f7a5"); // app key CRM

        return map;
    }

    private static HashMap<String,String> getTestDataControlsApp(){
        HashMap<String,String> map = new HashMap<>();
        map.put("xApplicationKeyControlsApp","c9d41b936a76107650648499107a165afcb63c21");
        map.put("login","glushko_nf");
        map.put("password", "123456");
        return map;
    }

    public String getxApplicationKeyControlsApp(){

        return getTestDataControlsApp().get("xApplicationKeyControlsApp");
    }
    public String getLogin(){

        return  getTestDataControlsApp().get("login");
    }
    public String getPasswordConrolApp(){

        return getTestDataControlsApp().get("password");
    }

}
