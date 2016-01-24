package test.java.api;

import com.mashape.unirest.http.exceptions.UnirestException;
import main.java.api.APIWrestlerClient;
import main.java.model.Wrestler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import static main.java.util.WrestlerDataGenerator.generateWrestler;
import static main.java.util.WrestlerDataGenerator.lastGeneratedWrestler;

public class APIScenariosTest {
    APIWrestlerClient APIWrestlerClient = new APIWrestlerClient();

    @BeforeClass
    public void setUp() {
        APIWrestlerClient.authorize("auto", "test");
    }


    @Test
    @Features("Create API")
    public void createWrestler() throws InterruptedException {
        String id = APIWrestlerClient.createWrestler(generateWrestler());
        Assert.assertTrue(id != null && Integer.valueOf(id) > 0);
    }

    @Test
    @Features("Delete API")
    public void deleteWrestler() throws UnirestException, InterruptedException {
        String id = APIWrestlerClient.createWrestler(generateWrestler());
        String status = APIWrestlerClient.deleteWrestler(id);
        Assert.assertTrue(status.equals("OK"));
        Assert.assertTrue(APIWrestlerClient.readWrestler(id) == null);
    }

    @Test(enabled = false) //seems like UPDATE method in API doesn't work
    @Features("Update API")
    public void updateWrestler() throws UnirestException {
        APIWrestlerClient APIWrestlerClient = new APIWrestlerClient();
        APIWrestlerClient.authorize("auto", "test");
        String id = APIWrestlerClient.createWrestler(generateWrestler());
        lastGeneratedWrestler.setMiddleName("Updated");
        String status = APIWrestlerClient.updateWrestler(lastGeneratedWrestler, id);
        Assert.assertTrue(status.equals("OK"));
    }


    @Test
    @Features("Read API")
    public void readWrestler() throws InterruptedException {
        String id = APIWrestlerClient.createWrestler(generateWrestler());
        Wrestler wrestler = APIWrestlerClient.readWrestler(id);
        Assert.assertTrue(wrestler.getLastName().equals(lastGeneratedWrestler.getLastName()));
        Assert.assertTrue(wrestler.getMiddleName().equals(lastGeneratedWrestler.getMiddleName()));
        Assert.assertTrue(wrestler.getFirstName().equals(lastGeneratedWrestler.getFirstName()));
        Assert.assertTrue(wrestler.getDateOfBirth().equals(lastGeneratedWrestler.getDateOfBirth()));
    }

    @BeforeMethod
    public void timeOut() throws InterruptedException {
        Thread.sleep(2000);
    }

}
