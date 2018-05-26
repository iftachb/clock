package com.example.user.clock;

import java.util.LinkedList;
import java.util.List;

public class Collector {

    private List<Machine> machines;

    private int currentMachineIndex;

    public void init() {
        machines = new LinkedList<>();
        machines.add(new Stopper());
        machines.add(new Timer());
        machines.add(new Gps());

        // the first machine is the stopper
        currentMachineIndex = 0;
    }

    public Machine getCurrentMachine() {
        return machines.get(currentMachineIndex);
    }

    public void setNextMachine() {
        currentMachineIndex = (currentMachineIndex + 1) % machines.size();
    }
}
