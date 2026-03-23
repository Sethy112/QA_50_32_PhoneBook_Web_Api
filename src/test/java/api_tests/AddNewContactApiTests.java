package api_tests;

import dto.Contact;
import dto.ResponseMessageDto;
import dto.TokenDto;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.UserFactory.positiveUser;
import static utils.PropertiesReader.*;
import static utils.ContactFactory.*;

public class AddNewContactApiTests implements BaseApi {
    TokenDto token;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login() {
        User user = new User(getProperty("base.properties",
                "login"), getProperty("base.properties",
                "password"));
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response.code() == 200) {
            try {
                token = GSON.fromJson(response.body()
                        .string(), TokenDto.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void addNewContactPositiveApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void addNewContactPositiveApiTest2() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request)
                .execute()) {
            softAssert.assertEquals(response.code(), 200,
                    "validate status code");
            ResponseMessageDto responseMessageDto = GSON.fromJson
                    (response.body().string(),
                            ResponseMessageDto.class);
            System.out.println(responseMessageDto);
            softAssert.assertTrue(responseMessageDto
                            .getMessage().contains("Contact was added!")
                    , "validate message");
            softAssert.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("created exception");
        }
    }

    @Test
    public void addNewContactNegative_WO_Token_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 403);
    }

    @Test
    public void addNewContactNegative_Wrong_Token_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, "token.getToken()")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void addNewContactNegative_Wrong_MediaType_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void addNewContactNegative_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URLWRONG + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500, "Заменил https на  http. БАГ!!!");
    }
//    @Test
//    public void addNewNegativeContact_With_NameIsEmpty_ApiTest() {
//        Contact contact = positiveContact();
//        contact.setName(" ");
//        RequestBody requestBody = RequestBody
//                .create(GSON.toJson(contact), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + ADD_NEW_CONTACT_URL)
//                .addHeader(AUTH, token.getToken())
//                .post(requestBody)
//                .build();
//        try (Response response = OK_HTTP_CLIENT.newCall(request)
//                .execute()) {
//            softAssert.assertEquals(response.code(), 400,
//                    "validate status code");
//
//            ResponseMessageDto responseMessageDto = GSON.fromJson
//                    (response.body().string(),
//                            ResponseMessageDto.class);

    /// /            System.out.println(responseMessageDto);
    /// /            softAssert.assertTrue(responseMessageDto
    /// /                            .getMessage().contains("Contact was added!")
    /// /                    ,"validate message");
    /// /            softAssert.assertAll();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(responseMessageDto.code(), 401);
//    }
    @Test
    public void addNewContactNegative_Wrong_MediaTypeXML_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), XML);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500, "Must be Code 415");
    }

    @Test
    public void addNewContactNegative_Wrong_MediaTypeHTML_ApiTest() {
        Contact contact = positiveContact();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(contact), HTML);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
            softAssert.assertEquals(response.code(),500,"Validate code");
            ResponseMessageDto responseMessageDto=GSON.fromJson(response.body().string(), ResponseMessageDto.class);
            System.out.println(responseMessageDto);
            softAssert.assertTrue(responseMessageDto.getMessage().contains("not supported"), "validate message");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(response.code(), 500);
    }


    @Test
    public void addNewContactNegative_WrongKeyName_ApiTest() {
        Contact contact = positiveContact();
        Map<String, String> invalidJson = new HashMap<>();
        invalidJson.put("Shopopalo", contact.getName());
//        invalidJson.put("lastName", contact.getLastName());
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(invalidJson), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        System.out.println(invalidJson);
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(contact);
        System.out.println(response.code());
        Assert.assertEquals(response.code(), 400, "Must ");
    }

    @Test
    public void addNewContactNegative_WrongKeyLastName_ApiTest() {
        Contact contact = positiveContact();
        Map<String, String> invalidJson = new HashMap<>();
        invalidJson.put("Shopopalo", contact.getLastName());
//        invalidJson.put("lastName", contact.getLastName());
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(invalidJson), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        System.out.println(invalidJson);
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(contact);
        System.out.println(response.code());
        Assert.assertEquals(response.code(), 400, "Must be");
    }

    @Test
    public void addNewContactNegative_WrongKeyAdress_ApiTest() {
        Contact contact = positiveContact();
        Map<String, String> invalidJson = new HashMap<>();
        invalidJson.put("Shopopalo", contact.getAddress());
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(invalidJson), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT_URL)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        System.out.println(invalidJson);
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request)
                    .execute();
            softAssert.assertEquals(response.code(), 400, "validate status code");
            ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);
            System.out.println(responseMessageDto);
            softAssert.assertTrue(responseMessageDto.getMessage().contains("   "), "validate code");
            softAssert.assertAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(contact);
    }


        @Test
        public void addNewContactPositiveApiTestWith() {
            Contact contact = Contact.builder()

            RequestBody requestBody = RequestBody
                    .create(GSON.toJson(contact), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + ADD_NEW_CONTACT_URL)
                    .addHeader(AUTH, token.getToken())
                    .post(requestBody)
                    .build();
            Response response;
            try {
                response = OK_HTTP_CLIENT.newCall(request)
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals(response.code(), 200);
        }
    }



