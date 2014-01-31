package bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.view;

import gov.nasa.worldwind.awt.AbstractViewInputHandler;
import gov.nasa.worldwind.awt.ViewInputAttributes;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;
import gov.nasa.worldwind.view.orbit.OrbitViewInputHandler;

import java.awt.event.MouseWheelEvent;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/11/2013 15:16
 */
public class CustomViewInputHandler extends OrbitViewInputHandler {

    protected final Logger LOGGER  = Logger.getLogger(CustomViewInputHandler.class.getName());

    public CustomViewInputHandler() {

        LOGGER.info("Input Handler : " + CustomViewInputHandler.class.getName());

        ViewInputAttributes attrs = this.getAttributes();

        // custom mouse wheel event
        attrs.getActionMap(ViewInputAttributes.DEVICE_MOUSE_WHEEL)
                .getActionAttributes(ViewInputAttributes.VIEW_VERTICAL_TRANSLATE)
                .setMouseActionListener(new ZoomActionHandler());

        //TODO Ne fonctionne pas
        // suppress click and go to action
        attrs.getActionMap(ViewInputAttributes.DEVICE_MOUSE)
                .getActionAttributes(ViewInputAttributes.VIEW_VERTICAL_TRANSLATE)
                .setMouseActionListener(null);
    }

    protected class ZoomActionHandler extends VertTransMouseWheelActionListener {

        @Override
        public boolean inputActionPerformed(
                AbstractViewInputHandler inputHandler,
                MouseWheelEvent mouseWheelEvent,
                ViewInputAttributes.ActionAttributes viewAction) {

            double zoomInput = mouseWheelEvent.getWheelRotation();
            Position position = computeSelectedPosition();

            // Zoom toward the cursor if we're zooming in. Move straight out when zooming out.
            if (zoomInput < 0 && position != null) {
                return this.zoomToPosition(position, zoomInput, viewAction);
            } else {
                return super.inputActionPerformed(inputHandler, mouseWheelEvent, viewAction);
            }
            
        }

        protected boolean zoomToPosition(
                Position position,
                double zoomInput,
                ViewInputAttributes.ActionAttributes viewAction) {

            BasicOrbitView orbitView = (BasicOrbitView) getView();
            Position centerPosition = orbitView.getCenterPosition();

            LatLon delta = position.subtract(centerPosition);
            double scale = getScaleValueRotate(viewAction);

            Angle latitudeChange = delta.getLatitude().multiply(scale);
            Angle longitudeChange = delta.getLongitude().multiply(scale);

            // Apply horizontal translation, if necessary.
            if (!latitudeChange.equals(Angle.ZERO) || !longitudeChange.equals(Angle.ZERO)) {
                Position newPosition = orbitView.getCenterPosition().add(new Position(latitudeChange, longitudeChange, 0.0));
                setCenterPosition(orbitView, uiAnimControl, newPosition, viewAction);
            }

            onVerticalTranslate(zoomInput * scale, viewAction);
            return true;
        }
    }
}
