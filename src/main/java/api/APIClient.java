package main.java.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.java.model.Wrestler;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIClient {
    private Map<String, String> headers = new HashMap<>();
    private String applicationUrl = "http://streamtv.net.ua/base";

    public APIClient() {
        setHeaders();
    }

    private void setHeaders() {
        headers.put("accept", "application/json");
        headers.put("accept", "text/plain");
        headers.put("Content-Type", "application/json");
        headers.put("Content-Type", "charset=utf-8");
    }

    private String getCookie(HttpResponse<JsonNode> loginResponse) {
        return loginResponse.getHeaders().get("set-cookie").get(0).replace("; path=/", "");
    }

    private HttpResponse<JsonNode> loginRequest(final String name, final String password) throws UnirestException {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", password);
        return Unirest.post(applicationUrl + "/php/login.php")
                .headers(headers)
                .body(new JSONObject(map).toString())
                .asJson();
    }

    public String authorize(String name, String password) throws UnirestException {
        HttpResponse<JsonNode> response = loginRequest(name, password);
        if (response.getStatusText().equals("OK")) {
            headers.put("Cookie", getCookie(response));
            return "OK";
        }
        return "Wrestler was not found";
    }

    public String create(Map<String, String> map) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(applicationUrl + "/php/wrestler/create.php")
                .headers(headers)
                .body(new JSONObject(map).toString())
                .asJson();
        if (!response.getStatusText().equals("OK")) {
            return "";
        } else {
            JSONObject jsonObject = new JSONObject(response.getBody().toString());
            return jsonObject.get("id").toString();
        }
    }

    public Wrestler read(String id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(applicationUrl + "/php/wrestler/read.php?id="+id)
                .headers(headers)
                .asJson();
        JSONObject jsonObject = new JSONObject(response.getBody().toString());
        if (jsonObject.get("region1").toString().equals("null")) {
            return null;
        }
        Wrestler wrestler = new Wrestler();
        wrestler.setLastName(jsonObject.get("lname").toString());
        wrestler.setFirstName(jsonObject.get("fname").toString());
        wrestler.setMiddleName(jsonObject.get("mname").toString());
        wrestler.setDateOfBirth(jsonObject.get("dob").toString());
        return wrestler;
    }

    public String delete(String id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(applicationUrl + "/php/wrestler/delete.php?id="+id)
                .headers(headers)
                .asJson();
        return response.getStatusText();
    }

    public String update(Map<String, String> map) throws UnirestException {
        System.out.println(new JSONObject(map).toString());
        HttpResponse<JsonNode> response = Unirest.post(applicationUrl + "/php/wrestler/update.php")
                .headers(headers)
                .body(new JSONObject(map).toString())
                .asJson();
        if (!response.getStatusText().equals("OK")) {
            return "";
        } else {
            return response.getStatusText();
        }
    }
}
