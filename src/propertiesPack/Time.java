package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import static physicsPack.waves1.SimpleHarmonicMotion.*;
import static propertiesPack.AngularVelocity.*;
import static propertiesPack.Distance.*;

/**
 * Java Bean property for mass.
 *
 * @author Naomy Valdez
 */
public class Time {

    private static final DoubleProperty time = new SimpleDoubleProperty();
    
    public static final double getSHMTime() {
        setSHMTime();
        return time.get();
    }

    public static final void setSHMTime() {
        time.set(Math.acos(blockSpring.getPosX() / getDistance()) / getAngularVelocity());
    }
    
}