package main.java.api;

import com.mashape.unirest.http.exceptions.UnirestException;
import main.java.model.Wrestler;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;
import java.util.Map;

public class APIWrestlerClient {
    private APIClient apiClient = new APIClient();

    @Step
    public void authorize(String name, String password) {
        try {
            apiClient.authorize(name, password);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Step
    public String createWrestler(Wrestler wrestler) {
        Map<String, String> map = APIWrestlerAdapter.adaptWrestler(wrestler);
        try {
            return apiClient.create(map);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Step
    public String deleteWrestler(String id) {
        try {
            return apiClient.delete(id);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step
    public Wrestler readWrestler(String id) {
        try {
            return apiClient.read(id);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step
    public String updateWrestler(Wrestler wrestler, String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        Map<String, String> adaptWrestlerMap = APIWrestlerAdapter.adaptWrestler(wrestler);
        for (Map.Entry<String, String> entry : adaptWrestlerMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        try {
            return apiClient.update(map);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }
}
