package ProjectTimer;

import java.util.Date;


public class TickTock {

    TimeAnth time = new TimeAnth();
    boolean pauseOn;
    boolean hasRestarted;
    boolean firstRun;
    boolean manualPause;
    int milliOnDefined;
    Date date;

    public TickTock() {
        this.pauseOn = false;
        this.hasRestarted = false;
        this.firstRun = true;
        this.manualPause = false;
        this.date = new Date();
        this.milliOnDefined = (int) this.date.getTime();
    }

    public TimeAnth getTime() {
        return this.time;
    }

    public void incrementSec() {
        if (!pauseOn) {
            time.setSeconds(time.getSeconds() + 1);
            numberCalculator();
        }

    }

    public void setFirstRun(boolean value) {
        this.firstRun = value;
    }

    public boolean isManualPause() {
        return manualPause;
    }

    public void setManualPause(boolean manualPause) {
        this.manualPause = manualPause;
    }

    public boolean isPauseOn() {
        return pauseOn;
    }

    public void setPauseOnTrue() {
        pauseOn = true;
    }

    public void setTickerPauseFalse() {
        pauseOn = false;
    }

    public boolean isHasRestartedOn() {
        return hasRestarted;
    }

    public void setHasRestartedTrue() {
        this.hasRestarted = true;
    }

    public void setHasRestartedFalse() {
        this.hasRestarted = false;
    }

    public void numberCalculator() {

        if (time.getSeconds() == 60) {
            time.setSeconds(0);
            time.setMinutes(time.getMinutes() + 1);
        }

        if (time.getMinutes() == 60) {
            time.setMinutes(0);
            time.setHours(time.getHours() + 1);
        }

        if (time.getHours() == 60) {
            time.setHours(0);
            time.setMinutes(0);
            time.setSeconds(0);
        }

    }



    public String toString() {
        return time.toString();
    }
}

