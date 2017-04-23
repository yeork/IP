package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import static windowsPack.UserInterface.*;

/**
 * Java Bean property for mass.
 *
 * @author Naomy Valdez
 */
public class Mass {

    private static final DoubleProperty mass = new SimpleDoubleProperty();

    public static final double getMass() {
        setMass();
        return mass.get();
    }

    public static final void setMass() {
        mass.bind(massSlider.valueProperty());
    }
}
