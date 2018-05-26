package com.example.user.clock;

public class Gps implements Machine{
    private String[] locations = {
        "Tel Aviv", "Jerusalem", "Ramat Gan", "Bat Yam"
    };

    private int currentLocationIndex = 0;

    private boolean isMoving = false;

    public void set() {
        currentLocationIndex = 0;
    }

    public void start() {
        isMoving = true;
    }

    public void stop() {
        isMoving = false;
    }

    public String getValue() {
        if (isMoving) {
            currentLocationIndex = (currentLocationIndex + 1) % locations.length;
        }
        return "GPS location is " + locations[currentLocationIndex];
    }
}
