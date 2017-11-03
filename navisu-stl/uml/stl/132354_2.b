class RefWriter
!!!143490.java!!!	RefWriter(inout positions : List<? extends Position>, in tileSideX : double, in tileSideY : double)

        this.positions = positions;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        latDegrees = positions.get(0).getLatitude().getDegrees();
        lonDegrees = positions.get(0).getLongitude().getDegrees();
        latDegrees1 = positions.get(2).getLatitude().getDegrees();
        lonDegrees1 = positions.get(2).getLongitude().getDegrees();

        System.out.println(latDegrees + " " + lonDegrees + " " + latDegrees1 + " " + lonDegrees1);
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));

        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        scaleLatFactor = latRangeMetric / tileSideY;
        scaleLatFactor *= 10;
        scaleLonFactor = lonRangeMetric / tileSideX;
        scaleLonFactor *= 10;

        latMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(latDegrees),
                        Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 100));
        lonMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                        Angle.fromDegrees(lonDegrees), 100));
        latMetric *= scaleLatFactor;
        latMetric = -tileSideY + latMetric;
        lonMetric *= scaleLatFactor;
        lonMetric = tileSideX - lonMetric;

!!!143618.java!!!	compute() : String
        String transform = "<Transform "
                + "translation='" + 2 * tileSideX + " 0.0 " + 2 * tileSideY 
                +  " '>\n";

        return transform + baseData + "</Transform>\n";
