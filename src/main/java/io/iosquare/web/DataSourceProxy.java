package io.iosquare.web;

import org.eclipse.jetty.servlets.EventSource;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mva on 08/10/15.
 */
public class DataSourceProxy implements EventSource {
    private Emitter emitter;

    public DataSourceProxy() {
    }

    @Override
    public void onOpen(Emitter emitter) throws IOException {
        this.emitter = emitter;
    }

    public void emitEvent(String dataToSend) {
        try {
            if(emitter != null) {
                this.emitter.data(dataToSend+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return emitter != null;
    }

    @Override
    public void onClose() {
        System.out.println("Stream closed");
        emitter = null;
    }
}
