package com.example.others.dp.fp;

import java.util.Arrays;

public abstract class DwarvenMineWorker {

    public void goToSleep() {
       System.out.println("{} goes to sleep."+name());
    }

    public void wakeUp() {
       System.out.println("{} wakes up."+name());
    }

    public void goHome() {
       System.out.println("{} goes home."+name());
    }

    public void goToMine() {
       System.out.println("{} goes to the mine."+name());
    }

    private void action(Action action) {
        switch (action) {
            case GO_TO_SLEEP -> goToSleep();
            case WAKE_UP -> wakeUp();
            case GO_HOME -> goHome();
            case GO_TO_MINE -> goToMine();
            case WORK -> work();
            default ->System.out.println("Undefined action");
        }
    }

    public void action(Action... actions) {
        Arrays.stream(actions).forEach(this::action);
    }

    public abstract void work();

    public abstract String name();

    enum Action {
        GO_TO_SLEEP,WAKE_UP,GO_HOME,GO_TO_MINE,WORK
    }
}