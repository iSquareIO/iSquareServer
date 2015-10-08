package io.iosquare.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mva on 08/10/15.
 */
public class DataCollector {
    private static List<DataSourceProxy> clients = Collections.synchronizedList(new ArrayList<DataSourceProxy>());

    public static void addClient(DataSourceProxy proxy) {
        clients.add(proxy);
    }

    public static synchronized void postData(String data) {
        for(DataSourceProxy proxy: clients) {
            if(proxy.isActive()) {
                proxy.emitEvent(data);
            }
        }
    }
}
