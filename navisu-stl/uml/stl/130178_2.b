class BathyDemStlController
!!!135298.java!!!	BathyDemStlController(inout bathymetryDBServices : BathymetryDBServices, inout displayBathymetryServices : DisplayBathymetryServices, inout guiAgentServices : GuiAgentServices, inout layer : RenderableLayer, inout polyEnvelope : Polygon)
        this.bathymetryDBServices = bathymetryDBServices;
        this.displayBathymetryServices = displayBathymetryServices;
        this.guiAgentServices = guiAgentServices;
        this.layer = layer;
        this.polyEnvelope = polyEnvelope;

        Iterable<? extends LatLon> bounds = polyEnvelope.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }

        double latMin = listLatLon.get(0).getLatitude().getDegrees();
        double lonMin = listLatLon.get(0).getLongitude().getDegrees();
        double latMax = listLatLon.get(2).getLatitude().getDegrees();
        double lonMax = listLatLon.get(2).getLongitude().getDegrees();
        List<Point3D> points = bathymetryDBServices.retrieveIn(latMin, lonMin, latMax, lonMax);

        points.stream().filter((p) -> (maxElevation < p.getElevation())).forEachOrdered((p) -> {
            maxElevation = p.getElevation();
        });

!!!135426.java!!!	writeDem(inout positions : List<? extends Position>, inout pathname : Path, inout latLon : boolean) : void
        double latMin = positions.get(0).getLatitude().getDegrees();
        double lonMin = positions.get(0).getLongitude().getDegrees();
        double latMax = positions.get(2).getLatitude().getDegrees();
        double lonMax = positions.get(2).getLongitude().getDegrees();

        List<Point3D> points = bathymetryDBServices.retrieveIn(latMin, lonMin, latMax, lonMax);

!!!135554.java!!!	getMaxElevation() : double
        return maxElevation;
