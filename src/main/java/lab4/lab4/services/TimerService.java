package lab4.lab4.services;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimerService {

    long startTime;

    public TimerService() {
        restart();
    }

    public void restart() {
        startTime = System.nanoTime();
    }

    public float getTimerValue() {
        return  (float) (System.nanoTime() - startTime)/1_000_000;
    }

    public String getCurrentTime() {
        String pattern = "dd.MM.yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}