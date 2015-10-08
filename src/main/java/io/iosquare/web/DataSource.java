package io.iosquare.web;

import org.eclipse.jetty.servlets.EventSource;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mva on 08/10/15.
 */
public class DataSource implements EventSource {
    private Emitter emitter;
    private Timer timer;
    private TimerTask timerTask;

    public DataSource() {
        timer = new Timer();
        timerTask = new TimerTask() {
            private DataGenerator generator = new DataGenerator();
            @Override
            public void run() {
                if(emitter != null) {
//                    String data = "test data: " + new Date() +"\n";

//                    System.out.println("Sample data: "+generator.getData());
//                    String data = "{\"timestamp\":\"2015-10-08T17:52:16.729Z\",\"columns\":[\"Toronto\",\"New York\",\"Kiev\"],\"data\":[[58,28,51],[0,2,0]]}\n";
                    String data = generator.getData() + "\n";
                    emitEvent(data);
                    System.out.println("Data is sent: "+data);
                } else {
                    System.out.println("Emitter is null");
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    @Override
    public void onOpen(Emitter emitter) throws IOException {
        this.emitter = emitter;
    }

    public void emitEvent(String dataToSend) {
        try {
            this.emitter.data(dataToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClose() {
        System.out.println("Stream closed");
        this.timer.cancel();
    }
}
