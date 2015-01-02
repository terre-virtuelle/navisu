class Widget2DController
!!!168962.java!!!	Widget2DController(inout keyCode : KeyCode, inout keyCombination : KeyCombination.Modifier)
        keyComb = new KeyCodeCombination(keyCode, keyCombination);
        initEvt();
!!!169090.java!!!	handle(inout event : KeyEvent) : void
        if (keyComb.match(event)) {
            if (isVisible()) {
                setVisible(false);
                stop();
            } else {
                setVisible(true);
                start();
            }
        }
!!!169218.java!!!	initEvt() : void
        setOnMouseEntered((MouseEvent me) -> {
            toFront();
        });
        setOnMousePressed((MouseEvent me) -> {
            initX = getTranslateX();
            initY = getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
        });
        setOnMouseDragged((MouseEvent me) -> {
            if (me != null && dragAnchor != null) {
                setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
            }
        });
        setOnMouseClicked((MouseEvent me) -> {
            if (me.isControlDown() && scale != 1.0) {
                scale(1.0);
                scale = 1.0;
            } else {
                if (me.isControlDown() && scale == 1.0) {
                    scale(0.5);
                    scale = 0.5;
                }
            }
        });
!!!169346.java!!!	scale(in scale : double) : void
        this.scale = scale;
        setScaleX(scale);
        setScaleY(scale);
!!!169474.java!!!	startFadeTransition(inout group : Group, in start : double, in end : double) : void
        FadeTransition fade = new FadeTransition(Duration.millis(1000), this);
        fade.setFromValue(start);
        fade.setToValue(end);
        fade.play();
!!!169602.java!!!	startScaleTransition(inout group : Group) : void
        ScaleTransition scaleTransition
                = new ScaleTransition(Duration.millis(2000), this);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(Transition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
!!!169730.java!!!	startRotateTransition(inout group : Group) : void
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), group);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(Transition.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
!!!169858.java!!!	startParallelTransition(inout group : Group, in x : float, in y : float, in xx : float, in yy : float, in scaleX : float, in scaleY : float, in scaleXX : float, in scaleYY : float) : void
        final Duration SEC_1 = Duration.millis(2000);
        final Duration SEC_2 = Duration.millis(3000);
        final Duration SEC_3 = Duration.millis(1000);

        group.setScaleX(scaleX);
        group.setScaleY(scaleY);

        FadeTransition fade = new FadeTransition(SEC_1);
        fade.setFromValue(0.0f);
        fade.setToValue(1.0f);

        TranslateTransition translate = new TranslateTransition(SEC_3);
        translate.setFromX(x);
        translate.setToX(xx);
        translate.setFromY(y);
        translate.setToY(yy);

        ScaleTransition scale = new ScaleTransition(SEC_2);
        scale.setToX(scaleXX);
        scale.setToY(scaleYY);
        ParallelTransition pt = new ParallelTransition(group, fade, translate, scale);
        pt.play();
