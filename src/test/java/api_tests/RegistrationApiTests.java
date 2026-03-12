package api_tests;

import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.positiveUser;


public class RegistrationApiTests implements BaseApi {

    @Test
    public void registrationPositiveApiTest() {
        User user = positiveUser();
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 200);
//        System.out.println(response.code());
    }

    @Test
    public void registrationNegative_Wrong_Password_ApiTest() {
        User user = positiveUser();
        user.setPassword("wrong password");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegativeTEst_WithoutEmail_ApiTest() {
        User user = positiveUser();
        user.setUsername(null);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegativeTEst_Withoutpassword_ApiTest() {
        User user = positiveUser();
        user.setPassword(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_WithWrongEndpoint_ApiTest() {
        User user = positiveUser();
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
    public void registrationNegativeTest_passwordIsEmpty_ApiTest() {
        User user = positiveUser();
        user.setPassword("");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 400);

    }
    @Test
    public void registrationNegativeTest_DublicateUser_ApiTest() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
                RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 409);
    }
    @Test
    public void registrationNegativeTest_WrongUrl_ApiTest() {
        User user = positiveUser();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 403);
        System.out.println(response.code());
    }
}
