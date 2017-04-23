package physicsPack.waves1;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static mainPack.Functions.timeline;
import mainPack.IntegrativeInterface;
import static physicsPack.electricity1.CoulombLaw.initialLine;
import static physicsPack.waves1.SimpleHarmonicMotion.*;
import static propertiesPack.Period.*;
import static propertiesPack.Time.getSHMTime;
import static propertiesPack.Velocity.getSHMVelocity;
import static windowsPack.UserInterface.*;

/**
 * Class that creates block & spring
 *
 * @author Naomy Valdez
 *
 */
public class BlockSpring implements IntegrativeInterface {

    private double width, height;
    private double springWidth, springHeight;
    private double origX, origY;
    private Rectangle block;
    private Path spring;

    /**
     * Constructor that instantiates Block object constructor.
     *
     * @param width block width
     * @param height block height
     * @param posX x position of the block
     * @param posY y position of the block
     */
    public BlockSpring(double width, double height, double posX, double posY) {
        this.width = width;
        this.height = height;

        origX = posX;
        origY = posY;

        createBlockSpring();
    }

    /**
     * Method that initializes elements of block & spring and their place in
     * space and animate in timeline.
     */
    public void createBlockSpring() {
        block = new Rectangle(SCREEN_WIDTH / 2 - width / 2 + origX, SCREEN_HEIGHT / 2 - height, width, height);
        spring = new Path();

        springWidth = block.getX() - width / 8;
        springHeight = 0.75 * height;

        spring.getElements().add(new MoveTo(0, SCREEN_HEIGHT / 2 - height / 2));
        for (int i = 1; i <= 15; i++) {
            spring.getElements().add(new LineTo(i * springWidth / 16, +Math.pow(-1, i) * springHeight / 2 + SCREEN_HEIGHT / 2 - height / 2));
        }
        spring.getElements().add(new LineTo(SCREEN_WIDTH / 2 - blockWidth / 2 + origX, SCREEN_HEIGHT / 2 - blockHeight / 2));

        timeline = TimelineBuilder.create()
                .autoReverse(true)
                .keyFrames(
                        new KeyFrame( // initial
                                new Duration(0),
                                new KeyValue(block.translateXProperty(), 0)
                        ),
                        new KeyFrame( // initial
                                new Duration(0),
                                new KeyValue(spring.translateXProperty(), 0)
                        ),
                        new KeyFrame(
                                new Duration(0),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(0, -blockSpring.getPosX()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(getPeriod() / 8 * 1000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(getPeriod() / 8 * 1000, -blockSpring.getPosX()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(getPeriod() / 4 * 1000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(getPeriod() / 4 * 1000, -blockSpring.getPosX()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(getPeriod() / 2 * 1000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(getPeriod() / 2 * 1000,  blockSpring.getPosX()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(getPeriod() * 1000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(getPeriod() * 1000,  blockSpring.getPosX()));
                            }
                        }
                        ),                        
                        new KeyFrame(
                                new Duration(getPeriod() * 1000),
                                new KeyValue(block.translateXProperty(), -block.getX() + minPos, Interpolator.EASE_BOTH)
                        ),
                        new KeyFrame(
                                new Duration(getPeriod() * 1000),
                                new KeyValue(spring.translateXProperty(), -block.getX() + minPos, Interpolator.EASE_BOTH)
                        )
                )
                .build();

        timeline.setCycleCount(Timeline.INDEFINITE);

        topLayout.getChildren().addAll(block, spring);
    }

    public double getPosX() {
        return origX;
    }

}
