package propertiesPack;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import mainPack.IntegrativeInterface;
import static propertiesPack.Velocity.*;
import static windowsPack.UserInterface.freqSlider;

public class Frequency implements IntegrativeInterface {

    private static final DoubleProperty frequency = new SimpleDoubleProperty();

    public static final double getFrequency() {
        setFrequency();
        return frequency.get();
    }
    public static final double getPerceivedFrequency() {
        setPerceivedFrequency();
        return frequency.get();
    }

    public static final void setFrequency() {
        frequency.bind(freqSlider.valueProperty());
    }
    public static final void setPerceivedFrequency() {
        frequency.set((SOUND_SPD + getVelocity()) * getFrequency());
    }
}
