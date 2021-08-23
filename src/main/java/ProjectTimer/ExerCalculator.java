package ProjectTimer;

import javafx.scene.media.AudioClip;


public class ExerCalculator {
    boolean hasCycled = false;
    boolean restarting = false;


    public void setRestarting(boolean restarting) {
        this.restarting = restarting;
        System.out.println(this.restarting + "setRestarting");
    }

    public String nextExerCalculator(int exNum) {
        if (hasCycled) {
            switch (exNum) {
                case 0:
                    return "Next Exercise: Back Bends";
                case 1:
                    return "Next Exercise: Bed Turns";
                case 2:
                    return "Next Exercise: Door Squats";

            }
        } else {
            this.hasCycled = true;
            return "Next Exercise: Back Bends";
        }

        return "";
    }

    public String exToDoCalculator(int exNum, AudioClip audio1, AudioClip audio2, AudioClip audio3) {
        if (restarting) {
            return "Do Back bends";

        } else {
            switch (exNum) {
                case 0:
                    audio1.play();
                    return "Do Back Bends";
                case 1:
                    audio2.play();
                    return "Do Bed Turns";
                case 2:
                    audio3.play();
                    return "Do Door Squats";
            }
        }
        setRestarting(false);
        return "";
    }
}