package propertiesPack;

import static java.lang.Math.sqrt;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import mainPack.IntegrativeInterface;
import static propertiesPack.Mass.*;

/**
 * Java Bean property for mass
 *
 * @author Naomy Valdez
 */
public class AngularVelocity implements IntegrativeInterface {

    private static final DoubleProperty angularVelocity = new SimpleDoubleProperty();

    public static final double getAngularVelocity() {
        setAngularVelocity();
        return angularVelocity.get();
    }

    public static final void setAngularVelocity() {
        angularVelocity.set(sqrt(SPRING_CST / getMass()));
    }
}
