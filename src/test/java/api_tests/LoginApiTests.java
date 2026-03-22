package api_tests;

import com.google.gson.Gson;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;
import static utils.PropertiesReader.getProperty;


public class LoginApiTests implements BaseApi {

    @Test
    public void LoginPositiveTest(){
        User user = new User(getProperty("base.properties","login"),
                getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response=OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 200);
    }


  @Test
    public void loginNegativeTest_WithOutAtEmail_ApiTest() {
        User user = new User(getProperty("loginyoh@.com", "login"),
                getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }



    @Test
    public void loginNegativeTestisBlank_ApiTest() {
        User user = new User(" ", " ");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }


    @Test
    public void loginNegativeTestWithTWoDots_ApiTest() {
        User user = new User("login@yoho..com", getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);

    }

    @Test
    public void loginNegativeTestPasswordIsBlank_ApiTest() {
        User user = new User("login@@yoho.com", "");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }
    @Test
    public void loginNegativeTestEmailIsBlank_ApiTest() {
        User user = new User("", getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);

    }
    @Test
    public void loginNegativeTestEmailIsTwoAts_ApiTest() {
        User user = new User("login@@yoho.com", getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);

    }
    @Test
    public void loginNegativePasswordisTrueEmail_Correct_ButWrongApiTests() {
        User user = new User("login@gmaisss.il",
                getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }

//    5 Test with pattern password (Parol12!)
@Test
public void loginNegativeWithOut_UpperCase_ApiTests() {
    User user = new User(getProperty("base.properties","login"),
            ("parol12!"));
    RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
    Request request = new Request.Builder()
            .url(BASE_URL + LOGIN_URL)
            .post(requestBody)
            .build();
    Response response;
    try {
        response = OK_HTTP_CLIENT.newCall(request).execute();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    Assert.assertEquals(response.code(), 401);
}
    @Test
    public void loginNegativeWithOut_LowerCase_ApiTests() {
        User user = new User(getProperty("base.properties","login"),
                ("PAROL12!"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }
    @Test
    public void loginNegativeWithOut_Number_ApiTests() {
        User user = new User(getProperty("base.properties","login"),
                ("Parolll!"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }
    @Test
    public void loginNegativeWithOut_SpecSymbol_ApiTests() {
        User user = new User(getProperty("base.properties","login"),
                ("Parol12l!"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }
    @Test
    public void loginNegative_LongWrong_ApiTests() {
        User user = new User(getProperty("base.properties","login"),
                ("Parl12l!"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }
//    _Wrong_MediaType
@Test
public void loginNegative__Wrong_MediaType_ApiTests() {
    User user = new User(getProperty("base.properties","login"),
            getProperty("base.properties","password"));
    RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);
    Request request = new Request.Builder()
            .url(BASE_URL + LOGIN_URL)
            .post(requestBody)
            .build();
    Response response;
    try {
        response = OK_HTTP_CLIENT.newCall(request).execute();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    Assert.assertEquals(response.code(), 400,"Must be code 500");
}
    @Test
    public void loginNegative__Wrong_EndPoint_ApiTests() {
        User user = new User(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 403);
    }

}
