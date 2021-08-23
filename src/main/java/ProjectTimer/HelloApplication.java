package ProjectTimer;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import java.awt.PopupMenu;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import javafx.scene.media.AudioClip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IllegalStateException {

        // time test
        TickTock ticker = new TickTock();


        // pane setup
        BorderPane layout = new BorderPane();
//        File file = new File("C:\\Users\\user\\IdeaProjects\\ThreeStepExercisesJavaFX\\treeImage.jpg");
        File file = new File("/home/anthony/IdeaProjects/ProjectTimer/src/main/resources/treeImage.jpg");
        Image image = new Image (file.toURI().toString());
        layout.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        BorderPane middleLayout = new BorderPane();
        GridPane gridPane = new GridPane();
        layout.setPrefSize(500, 300);
        HBox buttonPanel = new HBox();
        VBox bottomLines = new VBox();
        bottomLines.setSpacing(2);
        // Button setup
        Button start = buttonCreator("start");
        Button pause = buttonCreator("pause");
        Button restartCycle = buttonCreator("restart");

        // initial label declaration
        Label timeOnScreen = new Label(ticker.toString());
        Label timerStarted = new Label("Timer not started yet");
        Label exerciseToDo = new Label("Next Exercise: Back Bends");
        Separator separator = new Separator();
        separator.setStyle("-fx-border-color:#000000; -fx-border-width: 2;");
        Separator separator2 = new Separator();
        separator2.setStyle("-fx-border-color:#000000; -fx-border-width: 2;");
        exerciseToDo.setFont(new Font("Arial", 30));
        exerciseToDo.setTextFill(Color.web("#FFFFFF"));

//        exerciseToDo.setStyle("-fx-stroke:#e80202; -fx-stroke-width: 2");
        timerStarted.setFont(new Font("Arial", 30));
        timerStarted.setTextFill(Color.web("#FFFFFF"));
        timeOnScreen.setFont(new Font("Arial", 100));
        timeOnScreen.setTextFill(Color.web("#FFFFFF"));
        buttonPanel.getChildren().addAll(start, pause, restartCycle);
        layout.setBottom(buttonPanel);
        layout.setTop(gridPane);
        gridPane.add(timeOnScreen, 100, 100);
        bottomLines.getChildren().add(exerciseToDo);
        bottomLines.getChildren().add(separator);
        bottomLines.getChildren().add(timerStarted);
        bottomLines.getChildren().add(separator2);
        middleLayout.setBottom(bottomLines);
        layout.setCenter(middleLayout);

        // Media declaration

        SoundCreator soundBackBend = new SoundCreator();
        soundBackBend.addAudio("backBend1.wav");
        soundBackBend.addAudio("backBend2.wav");
        soundBackBend.addAudio("backBend3.wav");

        SoundCreator soundBedTurn = new SoundCreator();
        soundBedTurn.addAudio("bedTurns1.wav");
        soundBedTurn.addAudio("bedTurns2.wav");
        soundBedTurn.addAudio("bedTurns3.wav");

        SoundCreator soundDoorSquat = new SoundCreator();
        soundDoorSquat.addAudio("doorSquats1.wav");
        soundDoorSquat.addAudio("doorSquats2.wav");
        soundDoorSquat.addAudio("doorSquats3.wav");


        // Button functionality
        Timer timer = new Timer();
        RunEverySecond runner = new RunEverySecond(ticker, timeOnScreen, exerciseToDo, soundBackBend.randomAudio(), soundBedTurn.randomAudio(), soundDoorSquat.randomAudio());
        ExerCalculator exerCalc = new ExerCalculator();

        // Start button
        start.setOnAction((event) -> {

            DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

            if (!ticker.isPauseOn()) {
                timer.schedule(runner, 0, 1000);
                timerStarted.setText("Timer started at: " + timeFormat.format(new Date()));
            }
            if (ticker.isHasRestartedOn()) {
                timerStarted.setText("Timer started at: " + timeFormat.format(new Date()));
            }
            ticker.setFirstRun(false);

            if (!ticker.firstRun && !ticker.manualPause) {
                exerciseToDo.setText(exerCalc.nextExerCalculator(runner.getCycle()));
                runner.setCycle(runner.getCycle() + 1);
                if (runner.getCycle() == 3) {
                    runner.setCycle(0);
                }

            }
            System.out.println("all set to false");
            ticker.setManualPause(false);
            ticker.setHasRestartedFalse();
            ticker.setTickerPauseFalse();
            runner.setPaused(false);


        });

        pause.setOnAction((event) -> {
            ticker.setPauseOnTrue();
            ticker.setManualPause(true);
        });

        restartCycle.setOnAction((event) -> {
            ticker.setPauseOnTrue();
            ticker.getTime().setHours(0);
            ticker.getTime().setMinutes(0);
            ticker.getTime().setSeconds(0);

            ticker.setHasRestartedTrue();

            runner.setCycle(0);
            runner.setNewCycle(0);
            runner.resetExerCalc();
            runner.exerCalc.setRestarting(true);

        });

        // Popups
        PopUpDefiner popUpDef = new PopUpDefiner(stage, start, pause, restartCycle);
        popUpDef.menuReturner();







        // scene setup
        Scene scene = new Scene(layout);


        stage.setScene(scene);

        stage.setResizable(false);


        stage.show();

    }

    public Button buttonCreator(String name) {
        Button button = new Button(name);

        button.setPrefSize(166.67, 40);

        return button;
    }



    public static void main(String[] args) {
        launch();


    }
}