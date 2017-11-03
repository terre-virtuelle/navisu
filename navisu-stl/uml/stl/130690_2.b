class DemElevationLoader
!!!137218.java!!!	DemElevationLoader()
        wwd = GeoWorldWindViewImpl.getWW();
        model = wwd.getModel().getGlobe().getElevationModel();
!!!137346.java!!!	getElevations(inout positions : List<? extends Position>, in ptsCountX : int, in ptsCountY : int, in latInc : double, in lonInc : double, in offset : double) : double
        double longitude = positions.get(0).getLongitude().getDegrees();
        double latitude;
        double[][] elevations = new double[ptsCountX][ptsCountY];
        for (int u = 0; u < ptsCountX; u++) {
            latitude = positions.get(0).getLatitude().getDegrees();
            for (int v = 0; v < ptsCountY; v++) {
                double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
                if (el < 0) {
                    el = 0;
                }
                elevations[u][v] = el+offset;
                latitude += latInc;
            }
            longitude += lonInc;
        }
        return elevations;
