package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import static propertiesPack.AngularVelocity.*;

/**
 * Java Bean property for mass.
 *
 * @author Naomy Valdez
 */
public class Period {

    private static final DoubleProperty period = new SimpleDoubleProperty();

    public static final double getPeriod() {
        setPeriod();
        return period.get();
    }

    public static final void setPeriod() {
        period.set(2 * Math.PI / getAngularVelocity());
    }
}
