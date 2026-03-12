package api_tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public class AddContact implements BaseApi {
    String token;

    @BeforeClass
    public void login() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            String responseBody = response.body().string();

            JsonObject json = JsonParser
                    .parseString(responseBody)
                    .getAsJsonObject();

            token = json.get("token").getAsString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(token);
    }
    @Test
    public void addTest(){
        System.out.println(token);
    }




}
