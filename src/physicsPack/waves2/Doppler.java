package physicsPack.waves2;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import static mainPack.Functions.timeline;
import mainPack.IntegrativeInterface;
import propertiesPack.Delta;
import static propertiesPack.Frequency.*;
import static propertiesPack.Velocity.*;
import static windowsPack.UserInterface.*;

/*
 *Implementation of Animation and Graph
 * x = 4
 * @author Naomy Valdez
 */
public class Doppler implements IntegrativeInterface {

    public static ImageView ambulance;
    public static double ambulanceWidth;
    private static double ambulanceHeight;
    private static ImageView guy;
    private static double guyWidth, guyHeight;
    public static double guyX;
    private static double ambulanceDistance, time, period;
    private static Ellipse ellipse1, ellipse2, ellipse3, ellipse4, ellipse5;

    public static void setDopplerAnim() {
        ambulance = new ImageView(new Image(AMBULANCE));
        ambulanceWidth = 200;
        ambulanceHeight = 110;
        guy = new ImageView(new Image(GUY));
        guyWidth = 32;
        guyHeight = 84;
        guyX = SCREEN_WIDTH / 3 - guyWidth / 2;

        ambulance.setX(SCREEN_WIDTH - ambulanceWidth);
        ambulance.setY(SCREEN_HEIGHT / 2 - ambulanceHeight);

        guy.setX(guyX);
        guy.setY(SCREEN_HEIGHT / 2 - guyHeight);

        distanceTxt.setText("initial Δx = " + (int) (ambulance.getX() - guyX) + "m");
        distanceTxt.setX(10);
        distanceTxt.setY(SCREEN_HEIGHT / 2 - 10);

        topLayout.getChildren().addAll(ambulance, guy, distanceTxt);

        final Delta dragDelta = new Delta();
        // mouse event
        ambulance.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dragDelta.x = ambulance.getLayoutX() - mouseEvent.getSceneX();
                ambulance.setCursor(Cursor.MOVE);
            }
        });
        ambulance.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ambulance.setCursor(Cursor.HAND);
                distanceTxt.setText("initial Δx = " + (int) (mouseEvent.getSceneX() - guyX - guyWidth / 2) + "m");
            }
        });
        ambulance.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSceneX() > guyX + ambulanceWidth / 2) {
                    ambulance.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                }
            }
        });
        ambulance.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ambulance.setCursor(Cursor.HAND);
            }
        });

        time = 6000; // milliseconds
        ambulanceDistance = getVelocity() * time * Math.pow(10, -3);

        period = 1 / getFrequency();

        // to change label????? i guess
        /*timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), new KeyValue(label.textProperty(), "1")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5), new KeyValue(label.textProperty(), "2")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(6), new KeyValue(label.textProperty(), "3")));*/
        timeline = TimelineBuilder.create()
                .autoReverse(true)
                .keyFrames(
                        new KeyFrame( // initial
                                new Duration(0),
                                new KeyValue(ambulance.translateXProperty(), 0)
                        ),
                        new KeyFrame(
                                new Duration(0),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                series.getData().add(new XYChart.Data<Number, Number>(0, (SOUND_SPD / (SOUND_SPD - getVelocity()) * getFrequency())));
                                series2.getData().add(new XYChart.Data<Number, Number>(0, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(1000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(1, (SOUND_SPD / (SOUND_SPD - getVelocity()) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(1, (SOUND_SPD / (SOUND_SPD + getVelocity()) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(1, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(2000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(2, (SOUND_SPD / (SOUND_SPD - getVelocity()) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(2, (SOUND_SPD / (SOUND_SPD + getVelocity()) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(2, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(3000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(3, (SOUND_SPD / (SOUND_SPD - getVelocity()) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(3, (SOUND_SPD / (SOUND_SPD + getVelocity()) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(3, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(4000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(4, ((SOUND_SPD / (SOUND_SPD - getVelocity())) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(4, ((SOUND_SPD / (SOUND_SPD + getVelocity())) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(4, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(5000),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(5, ((SOUND_SPD / (SOUND_SPD - getVelocity())) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(5, ((SOUND_SPD / (SOUND_SPD + getVelocity())) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(5, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(time),
                                new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (ambulance.getX() > guyX) {
                                    series.getData().add(new XYChart.Data<Number, Number>(6, ((SOUND_SPD / (SOUND_SPD - getVelocity())) * getFrequency())));
                                } else {
                                    series.getData().add(new XYChart.Data<Number, Number>(6, ((SOUND_SPD / (SOUND_SPD + getVelocity())) * getFrequency())));
                                }
                                series2.getData().add(new XYChart.Data<Number, Number>(6, getFrequency()));
                            }
                        }
                        ),
                        new KeyFrame(
                                new Duration(time),
                                new KeyValue(ambulance.translateXProperty(), -ambulanceDistance, Interpolator.LINEAR)
                        )
                )
                .build();

        timeline.setCycleCount(1);
    }

    public static void setDopplerGraph() {
        lineChart.setTitle("Frequency perceived vs. Frequency produced");
        series.setName("Freq. perceived");
        series2.setName("Freq. produced");
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Frequency (Hz)");
    }
}
