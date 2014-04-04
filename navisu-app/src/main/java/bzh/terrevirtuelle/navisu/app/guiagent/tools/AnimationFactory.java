package bzh.tibus.javafx.tools;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/03/2014 21:50
 */
public interface AnimationFactory {

    /**
     * Create a new 'Wizzzz' animation for a Node
     *
     * @param target The targeted node to apply the animation
     * @param angle Extreme angle.
     * @param millis
     *
     * @return
     */
    public static Animation newWizzAnimation(final Node target, double angle, double millis, int nbCycles) {

        final DoubleProperty rotateProperty = target.rotateProperty();

        final Timeline wizz = new Timeline();

        for (int i = 1; i <= nbCycles * 2 + 1; i += 2) {

            angle *= (i % 2 == 0) ? 1 : -1;

            wizz.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(millis * i), new KeyValue(rotateProperty, angle)),
                    new KeyFrame(Duration.millis(millis * (i + 1)), new KeyValue(rotateProperty, 0))
            );
        }

        return wizz;
    }

    public static Animation newScaleAnimation(Node target, double millis, double scaleX, double scaleY, int nbCycles) {
        //main timeline
        Timeline timeline = new Timeline();
        timeline.setCycleCount(nbCycles);
        //  timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyValue keyValueX = new KeyValue(target.scaleXProperty(), scaleX);
        KeyValue keyValueY = new KeyValue(target.scaleYProperty(), scaleY);
        Duration duration = Duration.millis(millis);
        KeyFrame keyFrame = new KeyFrame(duration, keyValueX, keyValueY);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        return timeline;
    }

    public static Animation newFadeAnimation(Node target, double millis, double from, double to) {

        FadeTransition fade = new FadeTransition(Duration.millis(millis));
        fade.setFromValue(from);
        fade.setToValue(to);
        fade.setNode(target);

        return fade;
    }

    public static Animation newTranslateAnimation(Node target, double millis, double to) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(millis), target);
        tt.setToY(to);
        tt.setAutoReverse(true);
        return tt;
    }

}
