class PONTON_Stl_ShapefileWriter
!!!141314.java!!!	PONTON_Stl_ShapefileWriter(in filename : String, inout polyEnveloppe : Polygon, in scaleLatFactor : double, in scaleLonFactor : double, in tileSide : double)
        this.filename = filename;
        this.polyEnveloppe = polyEnveloppe;
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.tileSide = tileSide;
        List<List<? extends Position>> positions = polyEnveloppe.getBoundaries();
        orig = positions.get(0).get(0);
!!!141442.java!!!	createPolylineAttributes(inout record : ShapefileRecord) : ShapeAttributes
        this.record = record;
        shapefile = record.getShapeFile();

        CompoundVecBuffer buffer = record.getCompoundPointBuffer();
        createBuffers(buffer.getLocations());

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.BLUE);
        normalAttributes.setOutlineWidth(2.0);

        return normalAttributes;
!!!141570.java!!!	createPolygonAttributes(inout record : ShapefileRecord) : ShapeAttributes
        Color color = Color.BLACK;
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        return normalAttributes;
!!!141698.java!!!	createBuffers(inout latLon : Iterable<? extends LatLon>) : void

        List<Position> positionList = new ArrayList<>();

        for (LatLon l : latLon) {
            positionList.add(new Position(l, 10.0));
        }

        Path path = new Path(positionList);
        layer.addRenderable(path);

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        normalAttributes.setOutlineWidth(2.0);

        //PointPlacemark pp = new PointPlacemark(orig);
        //layer.addRenderable(pp);
        if (positionList.size() > 3 && positionList.get(0).equals(positionList.get(positionList.size() - 1))) {
            Polygon polygon = new Polygon(positionList);
            polygon.setAttributes(normalAttributes);
            layer.addRenderable(polygon);
            createSlCons(positionList);
        }

!!!141826.java!!!	createSlCons(inout positionList : List<Position>) : void

        List<LatLon> latLonList = new ArrayList<>();
        positionList.forEach((p) -> {
            latLonList.add(new LatLon(p.getLatitude(), p.getLongitude()));
        });
        Geometry geo = WwjJTS.filter(WwjJTS.PolygonToGeometry(polyEnveloppe), latLonList);
        List<Position> ptsFiltered = null;
        if (!geo.toString().contains("EMPTY") && !geo.toString().contains("MULTIPOLYGON")) {
            ptsFiltered = WwjJTS.wktPolygonToPositionList(geo.toString());
        }
        if (ptsFiltered != null) {
            if (!ptsFiltered.isEmpty()) {
                result
                        += " <Shape>\n"
                        + "<Appearance>\n"
                        + " <Material diffuseColor='1.0 1. 1.0'/> \n"
                        + "</Appearance>\n"
                        + "<Extrusion convex='true' \n"
                        + " crossSection='";
//positionList

                ptsFiltered.stream().map((p) -> WwjGeodesy.getXYM(orig, p)).forEachOrdered((xy) -> {
                    double x = tileSide - xy.getX() * scaleLonFactor;
                    double y = -tileSide + xy.getY() * scaleLatFactor;
                    result += x + " " + y + (",");
                });
                result += "'\n "
                        + "solid='false' \n"
                        + "spine='0 0 0 0 " + height + " 0'/>\n"
                        + "</Shape>\n";
            }
        }
!!!141954.java!!!	compute() : String
        return result;
