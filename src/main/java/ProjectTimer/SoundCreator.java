package ProjectTimer;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundCreator {

    List<String> fileNames;
    Random ran;

    public SoundCreator() {
        this.fileNames = new ArrayList<>();
        this.ran = new Random();
    }

    public AudioClip randomAudio() {
        int randomGet = this.ran.nextInt(this.fileNames.size() - 1);
//        AudioClip audioOutput = new AudioClip("file:" + this.fileNames.get(randomGet));
        File file = new File("C:\\Users\\user\\IdeaProjects\\ProjectTimer\\src\\main\\resources\\" + this.fileNames.get(randomGet));
        System.out.println(file.toURI().toString());
        AudioClip test = new AudioClip(file.toURI().toString());

//        AudioClip buzzer = new AudioClip(getClass().getResource("/resources/backBend1.wav").toExternalForm());

        return test;
    }

    public void addAudio(String fileName) {
        this.fileNames.add(fileName);
    }
}