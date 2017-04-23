package mainPack;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import windowsPack.MenuWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static mainPack.IntegrativeInterface.*;
import static physicsPack.electricity1.CoulombLaw.*;
import static mainPack.Functions.*;
import static windowsPack.MenuWindow.*;
import static windowsPack.UserInterface.*;
import static javafx.application.Application.launch;

/**
 * Program core which holds handler class and its usage, launch, and main
 * method.
 *
 * @author Jeremiah Contributions: Naomy
 */
public class IntegrativeMain extends Application implements IntegrativeInterface {

    protected static int x; // used to determine physics formula used
    public static Handler handler = new Handler();
    public static boolean isLooping = false;

    //Handler class
    public static class Handler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Exit
            if (event.getSource() == MenuWindow.sureItem) {
                exitMenu.setDisable(true);
                greetingText.setVisible(false);
                adiosText.setVisible(true);
                new Timer().schedule(
                        new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, TWO_THOUSAND); //2 seconds
            }
            if (event.getSource() == newtonItem) {
                x = 1; // Newton's Second Law
                uiTransition();
            } else if (event.getSource() == kineticItem) {
                x = 2; // Kinetic Energy
                uiTransition();
            } else if (event.getSource() == simpleHarmonicItem) {
                x = 3; // Simple Harmonic Motion
                uiTransition();
            } else if (event.getSource() == dopplerItem) {
                x = 4; // Doppler
                uiTransition();
            } else if (event.getSource() == coulombItem) {
                x = 5; // Coulomb's Law
                uiTransition();
            } else if (event.getSource() == electricPotentialItem) {
                x = 6; // Electric Potential
                uiTransition();
            }
            // return to menu window
            if (event.getSource() == doneBtn) {
                userStage.close();
                menuStage.show();
            }
            // construction alert message
            if (event.getSource() == construction1Item || event.getSource() == construction2Item
                    || event.getSource() == construction3Item) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(HOLD_UP);
                alert.setHeaderText(CONSTRUCTION_TEXT);
                alert.setContentText(WORKING_TEXT);
                alert.show();
            }
            //Implementations of Buttons
            // start animation and disable use of slider and start button
            if (event.getSource() == startBtn) {
                startBtn.setDisable(true);
                pauseBtn.setDisable(false);
                resetBtn.setDisable(false);
                timeline.play();
                switch (x) {
                    case 1: // Newton's Second Law
                        break;
                    case 2: // Kinetic Energy
                        break;
                    case 3: // SHM
                        distanceSlider.setDisable(true);
                        massSlider.setDisable(true);
                        break;
                    case 4: // Doppler
                        speedCarSlider.setDisable(true);
                        freqSlider.setDisable(true);
                        break;
                    case 5: // Coulomb's Law
                        break;
                    case 6: // Electric Potential
                        break;
                }
            }
            if (event.getSource() == pauseBtn) {
                timeline.pause();
                switch (x) {
                    case 1: // Newton's Second Law
                        break;
                    case 2: // Kinetic Energy
                        break;
                    case 3: // SHM
                        pauseBtn.setDisable(true);
                        startBtn.setDisable(false);
                        distanceSlider.setDisable(false);
                        massSlider.setDisable(false);
                        break;
                    case 4: // Doppler
                        pauseBtn.setDisable(true);
                        startBtn.setDisable(false);
                        speedCarSlider.setDisable(false);
                        freqSlider.setDisable(false);
                        break;
                    case 5: // Coulomb's Law
                        break;
                    case 6: // Electric Potential
                        break;
                }
            }
            if (event.getSource() == resetBtn) {
                reset();
            }
            // displays help message for the user
            if (event.getSource() == helpBtn) {
                helpText.setVisible(true);
            }
            if (event.getSource() == likeSignBtn) {
                isOpposite = false;
                setAnimGraph(false);
            }
            if (event.getSource() == loopBtn) {
                loopBtn.setDisable(true);
                timeline.setCycleCount(Timeline.INDEFINITE);
                isLooping = true;
                setAnimGraph(isLooping);
            }
        }
        
    }

    @Override
    public void start(Stage primaryStage) {
        new MenuWindow(); //Create Menu Window object
    }

    public static void main(String[] args) {
        launch(args);
    }
}
