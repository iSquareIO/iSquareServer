package io.iosquare.web;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

/**
 * Created by mva on 08/10/15.
 */
public class DataGenerator {
    private Random rand = new Random();

    public String getData() {
        JSONObject dataSnapshot = new JSONObject();
        dataSnapshot.put("timestamp", new Date().getTime());

        Collection<String> columnsList = new ArrayList<String>();
        columnsList.add("Toronto");
        columnsList.add("New York");
        columnsList.add("Kiev");
        String[] columnArray = {"Toronto", "New York", "Kiev"};
        JSONArray columns = new JSONArray(columnArray);
        dataSnapshot.put("columns", columns);

        int[] data0 = {randomValue(), randomValue(), randomValue()};
        JSONArray line0 = new JSONArray(data0);
        int[] data1 = {0, 2, 0};
        JSONArray line1 = new JSONArray(data1);

        JSONArray data = new JSONArray();
        data.put(line0);
        data.put(line1);

        dataSnapshot.put("data", data);
        return dataSnapshot.toString();
    }

    private int randomValue() {
        return rand.nextInt(100)+1;
    }
}
