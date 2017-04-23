package mainPack;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import static mainPack.IntegrativeInterface.*;
import static mainPack.IntegrativeMain.*;
import windowsPack.*;
import static physicsPack.electricity1.CoulombLaw.*;
import static physicsPack.waves1.SimpleHarmonicMotion.*;
import static physicsPack.waves2.Doppler.*;
import static windowsPack.MenuWindow.*;
import static windowsPack.UserInterface.*;

/**
 * Methods to automate tasks pertaining to User Interface.
 *
 * @author Jeremiah Tiongson Contributions: Naomy
 */
public class Functions {

    public static Timeline timeline;

    /**
     * Called every time a formula is picked. Help text changes based on x case
     * and adds corresponding tools to UI.
     */
    public static void uiTransition() {
        menuStage.hide();
        new UserInterface();
        userStage.show();
        // add related tools
        switch (x) {
            case 1: // Newton's Second Law
                helpText.setText(NEWTON_HELP);
                break;
            case 2: // Kinetic Energy
                helpText.setText(KINETIC_HELP);
                break;
            case 3: // SHM
                helpText.setText(SHM_HELP);
                distanceLbl.setText("Δx (m): ");
                leftLayout.add(distanceLbl, 0, 0);
                distanceSlider.setMin(0);
                distanceSlider.setMax(500);
                distanceSlider.setValue(0);
                leftLayout.add(distanceSlider, 1, 0);
                leftLayout.add(massLbl, 0, 3);
                massSlider.setMin(2);
                massSlider.setMax(10);
                massSlider.setValue(2);
                leftLayout.add(massSlider, 1, 3);
                break;
            case 4: // Doppler
                helpText.setText(DOPPLER_HELP);
                leftLayout.add(speedCarLbl, 0, 0);
                leftLayout.add(speedCarSlider, 1, 0);
                leftLayout.add(frequencyLbl, 0, 3);
                leftLayout.add(freqSlider, 1, 3);
                lineChart.getData().add(series2);
                break;
            case 5: // Coulomb's Law
                helpText.setText(COULOMB_HELP);
                leftLayout.add(likeSignBtn, 0, 3);
                leftLayout.add(loopBtn, 1, 2);
                distanceLbl.setText("Δx (cm): ");
                leftLayout.add(distanceLbl, 0, 0);
                distanceSlider.setMin(100);
                distanceSlider.setMax(500);
                distanceSlider.setValue(300);
                leftLayout.add(distanceSlider, 1, 0);
                isOpposite = true;
                // disable properties
                startBtn.disableProperty().bind(timeline.statusProperty().isNotEqualTo(Animation.Status.STOPPED));
                pauseBtn.disableProperty().bind(timeline.statusProperty().isNotEqualTo(Animation.Status.RUNNING));
                resetBtn.disableProperty().bind(timeline.statusProperty().isNotEqualTo(Animation.Status.STOPPED));
                break;
            case 6: // Electric Potential
                helpText.setText(POTENTIAL_HELP);
                break;
        }
        setAnimGraph(false);

    }

    /**
     * Resets animation based on x case.
     */
    public static void reset() {
        isLooping = false;
        series.getData().clear(); // clears existing data
        series2.getData().clear();
        timeline.stop();
        switch (x) {
            case 1: // Newton's Second Law
                break;
            case 2: // Kinetic Energy
                break;
            case 3: // SHM
                startBtn.setDisable(false);
                pauseBtn.setDisable(false);
                distanceSlider.setDisable(false);
                massSlider.setDisable(false);
                distanceSlider.setValue(0);
                massSlider.setValue(2);
                break;
            case 4: // Doppler
                startBtn.setDisable(false);
                pauseBtn.setDisable(false);
                ambulance.setX(SCREEN_WIDTH - ambulanceWidth);
                distanceTxt.setText("initial Δx= " + (int)(ambulance.getX() - guyX) + "m");
                speedCarSlider.setDisable(false);
                freqSlider.setDisable(false);
                speedCarSlider.setValue(250);
                freqSlider.setValue(800);
                break;
            case 5: // Coulomb's Law
                isOpposite = true;
                likeSignBtn.setDisable(false);
                loopBtn.setDisable(false);
                distanceSlider.setValue(300);
                break;
            case 6: // Electric Potential
                break;
        }
        setAnimGraph(false);
    }

    /**
     * Sets physics animation based on x case.
     */
    public static void setAnimGraph(boolean looping) {
        topLayout.getChildren().clear(); // clears top layout for new animation/graph
        switch (x) {
            case 1: // Newton's Second Law
                break;
            case 2: // Kinetic Energy
                break;
            case 3: // SHM
                setSHMAnim();
                setSHMGraph();
                break;
            case 4: // Doppler
                setDopplerAnim();
                setDopplerGraph();
                break;
            case 5: // Coulomb's Law
                setCoulombAnim(looping);
                setCoulombGraph();
                break;
            case 6: // Electric Potential
                break;
        }
    }
}
