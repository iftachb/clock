package com.example.user.clock;

import android.text.format.DateUtils;

import java.util.Locale;

public class Stopper implements Machine {
    private static final long MINUTE = 1000 * 60;
    private long startTime;
    private long stopTime;
    private long timeToTheStopper = 0;

    private boolean isActive = false;

    public void set() {
        timeToTheStopper += MINUTE;
    }

    public void start() {
        startTime = startTime - stopTime + System.currentTimeMillis();
        isActive = true;
    }

    public void stop() {
        if (startTime != 0) {
            stopTime = System.currentTimeMillis();
        }
        isActive = false;
    }

    public String getValue() {
        long time;
        if (isActive) {
            time = startTime + timeToTheStopper - System.currentTimeMillis();
        }
        else {
            time = startTime + timeToTheStopper - stopTime;
        }

        if (time < 0) {
            time = 0;
        }

        return "Stopper: " + secondsForTimeFormat(time / 1000);
    }

    private String secondsForTimeFormat(long duration) {
        String time = DateUtils.formatElapsedTime(duration);
        if (time.startsWith("0")) {
            time = time.substring(1);
        }
        return time;
    }
}