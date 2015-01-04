class RadarImpl
!!!168578.java!!!	on() : void
        RadarController radarController = new RadarController(KeyCode.A, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, radarController);
        guiAgentServices.getRoot().getChildren().add(radarController); //Par defaut le radar n'est pas visible Ctrl-A
        radarController.scale(0.5);
        radarController.setVisible(true);
        radarController.start();
