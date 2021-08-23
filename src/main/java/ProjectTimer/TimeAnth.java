package ProjectTimer;

public class TimeAnth {
    String output;
    int hours;
    int minutes;
    int seconds;

    public TimeAnth() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.output = this.hours + ":" + this.minutes + ":" + this.seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    public String turnToString() {
        String toString = "";

        if (this.hours < 10  && this.minutes >= 10 && this.seconds >= 10) {
            toString = "0" + this.hours + ":" + this.minutes + ":" + this.seconds;
        } else if (this.hours < 10 && this.minutes < 10  && this.seconds >= 10) {
            toString = "0" + this.hours + ":0" + this.minutes + ":" + this.seconds;
        } else if (this.hours < 10 && this.minutes < 10 && this.seconds < 10) {
            toString = "0" + this.hours + ":0" + this.minutes + ":0" + this.seconds;
        } else if (this.hours >= 10  && this.minutes >= 10 && this.seconds >= 10) {
            toString = this.hours + ":" + this.minutes + ":" + this.seconds;
        } else if (this.hours >= 10  && this.minutes >= 10 && this.seconds < 10) {
            toString = this.hours + ":" + this.minutes + ":0" + this.seconds;
        } else if (this.hours < 10  && this.minutes >= 10 && this.seconds < 10) {
            toString = "0" + this.hours + ":" + this.minutes + ":0" + this.seconds;
    }


        return toString;
    }


    
    public String toString() {
        return turnToString();
    }
}
