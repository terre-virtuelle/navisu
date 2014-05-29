package bzh.terrevirtuelle.navisu.widgets.radialmenu.menu;


/**
 * NaVisu
 *
 * @author Jordan Mens
 */
public class RadialMenuSeparator extends RadialMenuItem {

    public RadialMenuSeparator() {
         
    }

    protected void update() {
        
    	//TODO Must be review
    	double delta = 2;
        double center = (getOuterRadius() + getInnerRadius()) / 2;

        //Arc lenght angle
        double arcAngleLengthInRadians = Math.toRadians(getLength());

        //Angle rotation of the item
        double itemAngleInRadians = Math.toRadians(getStartAngle() + ANGLE_TO_START_AT_ZERO_DEGREE);

        moveTo.setX((center - delta) * Math.cos(itemAngleInRadians));
        moveTo.setY((center - delta) * Math.sin(itemAngleInRadians));

        lineTo.setX((center + delta ) * Math.cos(itemAngleInRadians));
        lineTo.setY((center + delta ) * Math.sin(itemAngleInRadians));

        arcTo.setX((center + delta) * Math.cos(itemAngleInRadians + arcAngleLengthInRadians));
        arcTo.setY((center + delta) * Math.sin(itemAngleInRadians + arcAngleLengthInRadians));
        arcTo.setRadiusX(center);
        arcTo.setRadiusY(center) ;
        arcTo.setSweepFlag(true);

        lineTo2.setX((center - delta) * Math.cos(itemAngleInRadians + arcAngleLengthInRadians));
        lineTo2.setY((center - delta) * Math.sin(itemAngleInRadians + arcAngleLengthInRadians));

        arcToInner.setX((center - delta) * Math.cos(itemAngleInRadians));
        arcToInner.setY((center - delta) * Math.sin(itemAngleInRadians));
        arcToInner.setRadiusX(center);
        arcToInner.setRadiusY(center);
    }
}
