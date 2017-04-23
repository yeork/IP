package physicsPack.waves1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import static mainPack.Functions.setAnimGraph;
import mainPack.IntegrativeInterface;
import static propertiesPack.Distance.*;
import static propertiesPack.Period.getPeriod;
import static propertiesPack.Time.*;
import static propertiesPack.Velocity.*;
import static windowsPack.UserInterface.*;

/**
 * Implementation of Animation and Graph x = 3
 *
 * @author Naomy Valdez
 */
public class SimpleHarmonicMotion implements IntegrativeInterface {

    public static Line maxPosLine, maxNegLine, zeroLine;
    public static BlockSpring blockSpring;
    public static double blockWidth, blockHeight, equilibrium, maxPos, minPos;

    public static void setSHMAnim() {

        blockWidth = SCREEN_WIDTH / 6;
        blockHeight = SCREEN_HEIGHT / 6;

        blockSpring = new BlockSpring(blockWidth, blockHeight, getDistance(), 0);

        maxPos = SCREEN_WIDTH / 2 + blockSpring.getPosX() + blockWidth / 2;
        minPos = SCREEN_WIDTH / 2 - blockSpring.getPosX() - blockWidth / 2;
        equilibrium = (maxPos + minPos) / 2;

        maxPosLine = new Line();
        maxPosLine.setStroke(Color.RED);
        maxPosLine.setStartX(maxPos);
        maxPosLine.setEndX(maxPos);
        maxPosLine.setStartY(SCREEN_HEIGHT / 2);
        maxPosLine.setEndY(0);
        maxNegLine = new Line();
        maxNegLine.setStroke(Color.RED);
        maxNegLine.setStartX(minPos);
        maxNegLine.setEndX(minPos);
        maxNegLine.setStartY(SCREEN_HEIGHT / 2);
        maxNegLine.setEndY(0);
        zeroLine = new Line();
        zeroLine.setStroke(Color.CORNFLOWERBLUE);
        zeroLine.setStartX(equilibrium);
        zeroLine.setEndX(equilibrium);
        zeroLine.setStartY(SCREEN_HEIGHT / 2);
        zeroLine.setEndY(0);

        topLayout.getChildren().addAll(maxNegLine, maxPosLine, zeroLine, springCstTxt);

    }

    public static void setSHMGraph() {
        lineChart.setTitle("Time vs. Position");
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Position (m)");
        series.getData().add(new XYChart.Data<Number, Number>(0, 0));
    }
}
