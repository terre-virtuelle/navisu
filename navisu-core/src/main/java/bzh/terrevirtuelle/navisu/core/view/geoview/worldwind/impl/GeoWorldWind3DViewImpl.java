package bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.Projection;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWind3DView;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.globes.Earth;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class GeoWorldWind3DViewImpl extends GeoWorldWindViewImpl implements GeoWorldWind3DView {

    protected Earth globe3D;

    public GeoWorldWind3DViewImpl() {
        super();
        this.globe3D = new Earth();
        this.wwd.getModel().setGlobe(this.globe3D);
    }

    @Override
    public void setPitch(double pitch) {
        this.wwd.getView().setPitch(Angle.fromDegrees(pitch));
    }

    @Override
    public double getPitch() {
        return this.wwd.getView().getPitch().degrees;
    }

    @Override
    public void setRoll(double roll) {
        this.wwd.getView().setRoll(Angle.fromDegrees(roll));
    }

    @Override
    public double getRoll() {
        return this.wwd.getView().getRoll().degrees;
    }

    @Override
    public void setHeading(double heading) {
        this.wwd.getView().setHeading(Angle.fromDegrees(heading));
    }

    @Override
    public double getHeading() {
        return this.wwd.getView().getHeading().degrees;
    }

    @Override
    public void setProjection(Projection projection) {
        throw new UnsupportedOperationException("Unable to set projection for 3D globe.");
    }
}
