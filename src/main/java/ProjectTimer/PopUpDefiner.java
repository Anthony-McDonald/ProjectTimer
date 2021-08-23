package ProjectTimer;

import javafx.application.*;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.awt.*;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.util.*;

public class PopUpDefiner {
    Timer notificationTimer = new Timer();
    PopupMenu popup = new PopupMenu();
    DateFormat timeFormat = SimpleDateFormat.getTimeInstance();
    Stage stage;
    String iconImageLoc = "http://icons.iconarchive.com/icons/scafer31000/bubble-circle-3/16/GameCenter-icon.png";
    Button start;
    Button pause;
    Button restart;

    public PopUpDefiner(Stage stage, Button start, Button pause, Button restart) {
        this.stage = stage;
        this.start = start;
        this.pause = pause;
        this.restart = restart;
    }

    public PopupMenu menuReturner() {


        //-------------------------------------------------------------------

        // doesnt exit when last app window is shut
        Platform.setImplicitExit(false);

        // tray icon set up using method below
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);

        // give outstage transparent style
        stage.initStyle(StageStyle.UNIFIED);




        return popup;
    }

    private void addAppToTray() {
        try {
            // ensure awt toolkit is initialized.
            Toolkit.getDefaultToolkit();

            // app requires system tray support, just exit if there is no support.
            if (!SystemTray.isSupported()) {
                System.out.println("No system tray support, application exiting.");
                Platform.exit();
            }

            // set up a system tray icon.
            SystemTray tray = SystemTray.getSystemTray();
            URL imageLoc = new URL(iconImageLoc);
            Image image = ImageIO.read(imageLoc);
            TrayIcon trayIcon = new TrayIcon(image);

            // if the user double-clicks on the tray icon, show main app stage (using method defined below)
            trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

            // if the user selects default menu item. show main app stage
            MenuItem openItem = new MenuItem("Exercise Timer");
            openItem.addActionListener(event -> Platform.runLater(this::showStage));

            //minimiser
            MenuItem minimiseItem = new MenuItem("Minimise");
            minimiseItem.addActionListener(event -> Platform.runLater((this::minimiseStage)));

            // set default icon for opening the application stage in bold font
            Font defaultFont = Font.decode(null);
            Font boldFont = defaultFont.deriveFont(Font.BOLD);
            openItem.setFont(boldFont);

            //proper application exit
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(event -> {
                notificationTimer.cancel();
                Platform.exit();
                tray.remove(trayIcon);
            });


            //setup popup menu
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            popup.add(minimiseItem);
            trayIcon.setPopupMenu(popup);

            //timer for notif
            notificationTimer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            javax.swing.SwingUtilities.invokeLater(() ->
                                    trayIcon.displayMessage("Hello!", "Current time is: " + timeFormat.format(new Date()),TrayIcon.MessageType.INFO
                                    )
                            );
                        }
                    }, 0, 1800000

            );

            //Start Functionality
            MenuItem startItem = new MenuItem("Start");
            startItem.addActionListener(event -> Platform.runLater(this::fireStart));

            //Pause Functionality
            MenuItem pauseItem = new MenuItem("Pause");
            pauseItem.addActionListener(event -> Platform.runLater(this::firePause));

            //Restart Functionality
            MenuItem restartItem = new MenuItem("Restart");
            restartItem.addActionListener(event -> Platform.runLater(this::fireRestart));

            popup.add(startItem);
            popup.add(pauseItem);
            popup.add(restartItem);


            // add app tray icon to sys tray
            tray.add(trayIcon);

        } catch (AWTException | IOException e) {
            System.out.println("Unable to init system tray");
            e.printStackTrace();
        }
    }

    private void showStage() {
        if (stage != null) {
            stage.show();
            stage.toFront();
        }
    }

    private void minimiseStage() {
        stage.hide();
    }

    private void fireStart() {
        this.start.fire();

    }

    private void firePause() {
        this.pause.fire();
    }

    private void fireRestart() {
        this.restart.fire();

    }

}
