package com.example.user.clock;

import android.text.format.DateUtils;

import java.util.Locale;

public class Clock {
    enum State{
        SET, START, STOP;
    }

    private static Clock instance;

    private State state;

    private Collector collector;
    private Machine machine;

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    private Clock() {
        collector = new Collector();
        collector.init();

        machine = collector.getCurrentMachine();
    }
    public void changeMode() {
        if (State.START.equals(state)) {
            machine.stop();
        }
        collector.setNextMachine();
        machine = collector.getCurrentMachine();
        state = State.SET;
    }

    public void set() {
        machine.set();
        state = State.SET;
    }

    public boolean start() {
        if (State.START.equals(state)) {
            return false;
        }
        machine.start();
        state = State.START;
        return true;
    }

    public boolean stop() {
        if (State.STOP.equals(state)) {
            return false;
        }
        machine.stop();
        state = State.STOP;
        return true;
    }

    public String getResult() {
        return machine.getValue();
    }
}
