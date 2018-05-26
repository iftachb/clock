package com.example.user.clock;

//import java.text.DateFormat;
//import java.util.Date;

import android.text.format.DateUtils;

import java.util.Locale;

public class Timer implements Machine{
    private long time;
    private long stopTime;

    private boolean isActive = false;

    public void set() {
        time = System.currentTimeMillis();
        stopTime = 0;
    }

    public void start() {
        time = System.currentTimeMillis() - stopTime;
        isActive = true;
    }

    public void stop() {
        if (time != 0) {
            stopTime = System.currentTimeMillis() - time;
        }
        isActive = false;
    }

    public String getValue() {
        if (isActive) {
            long currentTime = System.currentTimeMillis() - time;

            return "Timer: " + secondsForTimeFormat(currentTime / 1000);
        }
        else  {
            return "Timer: " + secondsForTimeFormat(stopTime / 1000);
        }
    }

    private String secondsForTimeFormat(long duration) {
        String time = DateUtils.formatElapsedTime(duration);
        if (time.startsWith("0")) {
            time = time.substring(1);
        }
        return time;
    }
}
