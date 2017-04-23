package propertiesPack;

import javafx.beans.property.DoubleProperty;
import mainPack.IntegrativeInterface;
import physicsPack.electricity1.CoulombLaw;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Java Bean property for force.
 *
 * @author Jeremiah Tiongson
 */
public class Force {

    //Both charges are in micro units, thus have a value of 10^(-6)
    private static final double chargeOne = 1 * Math.pow(10, -6);
    private static double chargeTwo = 1 * Math.pow(10, -6);

    //Define double to store property value
    private static final DoubleProperty magnitudeForce = new SimpleDoubleProperty();

    //Force implementation for Coloumb Law
    public static final double getForce(double distance) {
        setCoulombForce(distance);
        return magnitudeForce.get();
    }

    //Setter method calculating the force for Coloumb Law
    public static final void setCoulombForce(double distance) {
        if (CoulombLaw.isOpposite == true) {
            chargeTwo *= -1;
        }

        magnitudeForce.set((IntegrativeInterface.K * chargeOne * chargeTwo) / Math.pow(distance, 2));
    }

    //Based on my code above, @Sean write the implementation below for Newton Second Law
    /* public static final void setNewtonForce(){
    
    }*/
}
