package ProjectTimer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

import java.util.TimerTask;

public class RunEverySecond extends TimerTask {
    TickTock ticker;
    Label timerTicking;
    Label nextExercise;
    int newCycle;
    boolean hasPaused;
    int cycle;
    boolean hasCycled;
    ExerCalculator exerCalc = new ExerCalculator();
    AudioClip audio1;
    AudioClip audio2;
    AudioClip audio3;
    public RunEverySecond(TickTock ticker, Label timerTicking, Label nextExercise, AudioClip audio1, AudioClip audio2, AudioClip audio3) {
        this.ticker = ticker;
        this.timerTicking = timerTicking;
        this.nextExercise = nextExercise;
        this.newCycle = 0;
        this.hasPaused = false;
        this.cycle = 0;
        this.hasCycled = false;
        this.audio1 = audio1;
        this.audio2 = audio2;
        this.audio3 = audio3;
    }

    public int getCycle() {
        return this.cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void setNewCycle(int newCycle) {
        this.newCycle = newCycle;
    }


    public void run() {
        ticker.incrementSec();
        runLate();
    }

    public void runLate() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timerTicking.setText(ticker.toString());



                if (ticker.getTime().getMinutes() % 30 == 0 && ticker.getTime().getSeconds() == 0 && !hasPaused) {
                    try
                    {
                        Thread.sleep(50);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    setPaused(true);
                    ticker.setPauseOnTrue();
                    nextExercise.setText(exerCalc.exToDoCalculator(newCycle, audio1, audio2, audio3));

                    if (nextExercise.getText().equals(exerCalc.exToDoCalculator(newCycle, audio1, audio2, audio3))) {
                    }

                    newCycle++;
                    if (newCycle == 3) {
                        newCycle = 0;
                    }

                }
                    }
                });
    }

    public void resetExerCalc() {
        exerCalc = null;
        exerCalc = new ExerCalculator();
    }

    public void setPaused(boolean setter) {
        this.hasPaused = setter;
    }




}
