package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import static physicsPack.waves1.SimpleHarmonicMotion.*;
import static propertiesPack.AngularVelocity.*;
import static propertiesPack.Distance.*;
import static windowsPack.UserInterface.*;

/**
 * Java Bean property for mass.
 *
 * @author Naomy Valdez
 */
public class Velocity {

    private static final DoubleProperty velocity = new SimpleDoubleProperty();

    public static final double getVelocity() {
        setVelocity();
        return velocity.get();
    }

    public static final double getSHMVelocity() {
        setSHMVelocity();
        return velocity.get();
    }

    public static final void setVelocity() {
        velocity.bind(speedCarSlider.valueProperty());
    }

    public static final void setSHMVelocity() {
        if (blockSpring.getPosX() + blockWidth / 2 > SCREEN_WIDTH / 2) {
            velocity.set(getDistance() * getAngularVelocity());
        } else {
            velocity.set(-getDistance() * getAngularVelocity());
        }
    }
}
