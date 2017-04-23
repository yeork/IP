package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import static windowsPack.UserInterface.*;

/**
 * Java Bean property for distance.
 * 
 * @author Jeremiah Tiongson
 */
public class Distance {
    
    private static final DoubleProperty distance = new SimpleDoubleProperty();

    public static final double getDistance() {
        setDistance();
        return distance.get();
    }

    public static final void setDistance(){
        distance.bind(distanceSlider.valueProperty());
    }
        
}
