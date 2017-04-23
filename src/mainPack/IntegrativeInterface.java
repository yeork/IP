package mainPack;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Interface holding every constants.
 *
 * @author Jeremiah, Naomy
 */
public interface IntegrativeInterface {

    //Integers
    int TWO_THOUSAND = 2000;
    //Constants
    double GRAVITY = 9.8066; // Earth's gravity constant
    double K = 9 * Math.pow(10, 9); //Coulomb's constant
    double SOUND_SPD = 340.29;
    double SPRING_CST = 100;
    //Dimensions
    Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();
    double SCREEN_WIDTH = SCREEN_BOUNDS.getWidth();
    double SCREEN_HEIGHT = SCREEN_BOUNDS.getHeight();
    //Image locations
    String AMBULANCE = "resourcePack/ambulance.png";
    String GUY = "resourcePack/guy.png";
    //Texts
    String ADIOS_TEXT = "We hope you learned something new, see you next time!";
    String GREETING_TEXT = "Ready to learn about Physics? Pick a formula.";
    String HOLD_UP = "Hold up!";
    String CONSTRUCTION_TEXT = "In Construction...";
    String WORKING_TEXT = "We're working on it... Select another formula.";
    //Help Messages
    String NEWTON_HELP = "The bigger the force, the bigger the acceleration. The higher the mass, the higher the force is needed.";
    String KINETIC_HELP = "The higher the mass and velocity, the higher the energy is produced. Velocity has a huge effect on kinetic energy.";
    String SHM_HELP = "The red lines are the maximum positions and the blue line is the equilibrium point where\nthe velocity is at its maximum speed.";
    String DOPPLER_HELP = "The ambulance emits a certain sound frequency. The guy, seeing the ambulance approaching,\nperceives another sound frequency due to Doppler Effect.";
    String COULOMB_HELP = "TIP1: A red ball represents a positive charge. Blue is a negative charge."
            + "\nTIP2: Press the '++' button for like-sign charges."
            + "\nTIP3: When the distance doubles, the force reduces by a quarter of the initial value, and vice versa";
    String POTENTIAL_HELP = "The relation is W = Fd. F is the Lorentz force. F can also be expressed as Q * E. The magnitude of the charge times the Electric Field result in Newtons.";
}
