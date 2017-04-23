package physicsPack.electricity1;

import java.util.Random;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import mainPack.Functions;
import mainPack.IntegrativeInterface;
import propertiesPack.*;
import static propertiesPack.Distance.*;
import windowsPack.UserInterface;
import static windowsPack.UserInterface.*;
import static mainPack.Functions.*;

/**
 * Implementation of Animation and Graph x = 5
 *
 * @author Jeremiah Tiongson
 */
public class CoulombLaw implements IntegrativeInterface {

    public static boolean isOpposite;
    public static int opposition = 1; //negative if charges like-sign, pos is charges are opposite
    private static double gap;
    public static Circle charge1, charge2;
    public static Line gapLine, initialLine;
    private static final double CENTER_Y = SCREEN_HEIGHT / 4;
    private static Color randomColor;
    private static Color positiveColor = Color.rgb(238, 44, 44);
    private static Color negativeColor = Color.rgb(16, 78, 139);

    public static void renderGapLine() {
        gapLine = LineBuilder.create()
                .startY(CENTER_Y + 50)
                .endY(CENTER_Y + 50)
                .strokeWidth(4)
                .stroke(Color.BLACK)
                .build();

        if (isOpposite == false) {
            gapLine.setStartX(SCREEN_WIDTH / 2 - getDistance() - 50);
            gapLine.setEndX(SCREEN_WIDTH / 2 + getDistance() + 50);
        } else {
            gapLine.setStartX(SCREEN_WIDTH / 2 - 50);
            gapLine.setEndX(SCREEN_WIDTH / 2 + 50);
        }

        UserInterface.topLayout.getChildren().addAll(gapLine);
    }

    public static void setCoulombAnim(boolean loop) {
        topLayout.getChildren().clear();

        //Force.setCoulombForce(Distance.getDistance);
        //Random charge color tag for like charges
        Random rn = new Random();
        int range = 2 - 1 + 1; //where 2 is max and 1 is min
        int randomNum = rn.nextInt(range) + 1;
        if (randomNum == 1) {
            randomColor = positiveColor;
        } else {
            randomColor = negativeColor;
        }

        if (isOpposite == false) {
            charge1 = new Circle(SCREEN_WIDTH / 2 - 50, CENTER_Y, 50, randomColor);
            charge2 = new Circle(SCREEN_WIDTH / 2 + 50, CENTER_Y, 50, randomColor);
            opposition = -1;
            gap = getDistance();
        } else {
            charge1 = new Circle(SCREEN_WIDTH / 2 - getDistance(), CENTER_Y, 50, positiveColor);
            charge2 = new Circle(SCREEN_WIDTH / 2 + getDistance(), CENTER_Y, 50, negativeColor);
            opposition = 1;
            gap = ((charge2.getCenterX() / 2) - (charge1.getCenterX() / 2)) - 50;//-50 accounts for half the radius of each charge
        }
        //renders initialLine
        initialLine = LineBuilder.create()
                .startX(charge1.getCenterX())
                .endX(charge2.getCenterX())
                .startY(CENTER_Y + 50)
                .endY(CENTER_Y + 50)
                .strokeWidth(4)
                .stroke(Color.BLACK)
                .strokeLineCap(StrokeLineCap.SQUARE)
                .build();

        topLayout.getChildren().addAll(charge1, charge2, initialLine);

        timeline = TimelineBuilder.create()
                .autoReverse(false)
                .keyFrames(
                        new KeyFrame( // initial keyframe
                                new Duration(0.0),
                                new KeyValue(charge1.translateXProperty(), 0)
                        ),
                        new KeyFrame(
                                new Duration(1000.0),
                                new KeyValue(charge1.translateXProperty(), opposition * gap, Interpolator.LINEAR)
                        ),
                        new KeyFrame(
                                new Duration(1000.0),
                                new KeyValue(charge2.translateXProperty(), (-1) * opposition * gap, Interpolator.LINEAR)
                        ),
                        new KeyFrame(
                                new Duration(0.0),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                topLayout.getChildren().remove(initialLine);
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(1000.0),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                renderGapLine();
                            }
                        }
                        )
                )
                .build();

        if (loop) {
            timeline.setCycleCount(Timeline.INDEFINITE);
        } else {
            timeline.setCycleCount(1);
        }
    }

    public static void setCoulombGraph() {
        lineChart.setTitle("Force vs. distance");
        xAxis.setLabel("Force (N)");
        yAxis.setLabel("Δx (cm)");
        series.getData().add(new XYChart.Data<Number, Number>(1, 23));
        series.getData().add(new XYChart.Data<Number, Number>(2, 114));
        series.getData().add(new XYChart.Data<Number, Number>(3, 15));
        series.getData().add(new XYChart.Data<Number, Number>(4, 124));
    }
}
