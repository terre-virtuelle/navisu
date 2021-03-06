class NAVLNE_Stl_ShapefileWriter
!!!140674.java!!!	NAVLNE_Stl_ShapefileWriter(inout geometryEnveloppe : Geometry, inout polygon : Polygon)
        super();
        this.geometryEnveloppe = geometryEnveloppe;
        this.polygon = polygon;
!!!140802.java!!!	createPolyline(inout shp : Shapefile, inout attrs : ShapeAttributes) : Renderable
        super.createPolyline(shp, attrs);
        /*
        List<Integer> numberOfPoints = new ArrayList<>();
        while (shp.hasNext()) {
            record = shp.nextRecord();
            records.add(record);
            numberOfPoints.add(record.getNumberOfPoints());
        }
         */
        List<LatLon> latLon = new ArrayList<>();
        for (LatLon l : shp.getPointBuffer().getLocations()) {
            latLon.add(l);
        }

        System.out.println("latLon : " + latLon);

        return null;
!!!140930.java!!!	createX3D(inout latLon : List<LatLon>) : void
        int size = latLon.size() - 1;
        for (int i = 0; i < size; i++) {
           /* 
            positionList.stream().map((p) -> WwjGeodesy.getXYM(orig, p)).forEachOrdered((xy) -> {
            double x = 200 - xy.getX()*11.85;//*11.85;
            double y = -200 + xy.getY()*11.85;//*11.85;
            result += x + " " + y + (",");

        });
            */
           /*
            writeCylinder(latLon.get(i).getLatitude().getDegrees(),
                    latLon.get(i).getLongitude().getDegrees(),
                    latLon.get(i + 1).getLatitude().getDegrees(),
                    latLon.get(i + 1).getLongitude().getDegrees());
*/
        }
!!!141058.java!!!	writeCylinder(in lat0 : double, in lon0 : double, in lat1 : double, in lon1 : double) : String
        String str = "<Transform \n"
                + "   translation=\"" + lat0 + " 0.0000 " + lon0 + "\"\n"
                + "    scale=\"1.00000 1.00000 1.00000\"\n"
                + "    rotation=\"1.0 0.0 0.0 1.57058\">\n"
                + "     <Shape>\n"
                + "          <Appearance>\n"
                + "		     <Material DEF=\"MA_Shape\"\n"
                + "			diffuseColor=\"1.00 0.00 0.00\"\n"
                + "			specularColor=\"1.000 0.000 0.000\"\n"
                + "			emissiveColor=\"1.000 0.000 0.000\"\n"
                + "			ambientIntensity=\"0.0\"\n"
                + "			shininess=\"1.\"\n"
                + "			transparency=\"0.0\" \n"
                + "              />\n"
                + "		 </Appearance>\n"
                + "          <Cylinder height='5' radius='1.5'/>\n"
                + "    </Shape>\n"
                + "</Transform>\n";
        return str;
!!!141186.java!!!	compute() : String

        return result;
