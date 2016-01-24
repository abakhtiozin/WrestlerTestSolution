package main.java.api;

import main.java.model.Wrestler;

import java.util.HashMap;
import java.util.Map;

import static main.java.api.APIWrestlerDataAdapter.adapt;

public class APIWrestlerAdapter {
    public static Map<String, String> adaptWrestler(Wrestler wrestler) {
        Map<String, String> wrestlerFields = new HashMap<>();

        wrestlerFields.put("lname",wrestler.getLastName());
        wrestlerFields.put("fname",wrestler.getFirstName());
        wrestlerFields.put("mname",wrestler.getMiddleName());
        wrestlerFields.put("dob",wrestler.getDateOfBirth());
        wrestlerFields.put("style","1");
        wrestlerFields.put("region1","2");
        wrestlerFields.put("fst1","3");
        wrestlerFields.put("fst2","4");
        wrestlerFields.put("expires","2013");
        wrestlerFields.put("lictype",adapt(wrestler.getAge()));
        wrestlerFields.put("card_state",adapt(wrestler.getCardStatus()));

        return wrestlerFields;
    }
}
