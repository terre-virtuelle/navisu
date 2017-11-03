class StlComponentController
!!!132354.java!!!	StlComponentController(inout component : StlComponentImpl, inout guiAgentServices : GuiAgentServices, inout layerTreeServices : LayerTreeServices, inout layersManagerServices : LayersManagerServices, inout instrumentDriverManagerServices : InstrumentDriverManagerServices, inout geodesyServices : GeodesyServices, inout bathymetryDBServices : BathymetryDBServices, inout displayBathymetryServices : DisplayBathymetryServices, inout s57StlChartComponentController : StlChartController, in GROUP : String, in NAME : String, inout wwd : WorldWindow)
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.geodesyServices = geodesyServices;
        this.bathymetryDBServices = bathymetryDBServices;
        this.displayBathymetryServices = displayBathymetryServices;
        this.s57StlChartComponentController = s57StlChartComponentController;
        this.wwd = wwd;
        layer = layersManagerServices.getLayer(GROUP, NAME);
        layerBathy = layersManagerServices.getLayer(GROUP, NAME + "Bathy");
        this.selector = new SectorSelector(wwd);
        this.selector.setInteriorColor(new Color(1f, 1f, 1f, 0.1f));
        this.selector.setBorderColor(new Color(1f, 0f, 0f, 0.5f));
        this.selector.setBorderWidth(3);
        //  Pour le futur, la couche OSM
        // Layer buildings = new OSMBuildingsStlLayer();
        //  layerTreeServices.addGeoLayer("Buildings", buildings);
        // wwd.getModel().getLayers().add(buildings);
!!!132482.java!!!	showGUI(inout polygon : KMLSurfacePolygonImpl) : void

        if (firstShow == true) {
            this.kmlPolygon = polygon;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(StlComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            configGroup.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
            Platform.runLater(() -> {
                guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
                guiAgentServices.getRoot().getChildren().add(this);
                firstShow = false;
            });
        }
        displayChartBoundaries(polygon);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            keyCode = event.getCode();
        });
!!!132610.java!!!	initialize(inout location : URL, inout resources : ResourceBundle) : void
        countTilesCB.setItems(FXCollections.observableArrayList(1, 4, 9, 16, 25));
        countTilesCB.setValue(1);
        choiceCB.setItems(FXCollections.observableArrayList("MNTElevation&Carto",
                "MNTElevation",
                "Carto",
                "BathyMNT",
                "BathyDEPARE"));
        choiceCB.setValue("MNTElevation&Carto");
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        nameTF.setText(OUT_FILE);
        nameTF.setOnAction((ActionEvent event) -> {
            outFile = nameTF.getText();
            String[] outTab = outFile.split("\\.");
            outFile = outTab[0];
            nameTF.setText(outTab[0]);
        });
        magnificationTF.setOnAction((ActionEvent event) -> {
            try {
                magnification = Double.valueOf(magnificationTF.getText());
            } catch (NumberFormatException e) {
                magnification = 1.0;
            }
        });
        miRB.setToggleGroup(interactiveSquareGroup);
        tilesRB.setToggleGroup(interactiveSquareGroup);
        miRB.setDisable(true);
        tilesRB.setSelected(true);
        latLonAllRB.setToggleGroup(latLonGroup);
        latLonAllRB.setSelected(false);
        latLonAllRB.setDisable(true);
        latRB.setToggleGroup(latLonGroup);
        latRB.setSelected(true);
        lonRB.setToggleGroup(latLonGroup);
        lonRB.setSelected(false);
        eastRB.setToggleGroup(eastWestGroup);
        eastRB.setSelected(true);
        westRB.setToggleGroup(eastWestGroup);
        westRB.setSelected(false);
        northRB.setToggleGroup(northSouthGroup);
        northRB.setSelected(true);
        southRB.setToggleGroup(northSouthGroup);
        southRB.setSelected(false);
        countTilesCB.setOnAction((ActionEvent event) -> {
            tilesCount = countTilesCB.getValue();
            line = column = (int) Math.sqrt(tilesCount);
            wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
        });
        latLonAllRB.setOnAction((ActionEvent event) -> {
            System.out.println("Not yet implemented ");
        });
        latRB.setOnAction((ActionEvent event) -> {
            if (eastRB.isSelected()) {
                squareLatEast();
            } else {
                squareLatWest();
            }
        });
        lonRB.setOnAction((ActionEvent event) -> {
            if (northRB.isSelected()) {
                squareLonNorth();
            } else {
                squareLonSouth();
            }
        });
        eastRB.setOnAction((ActionEvent event) -> {
            if (latRB.isSelected()) {
                squareLatEast();
            }
        });
        westRB.setOnAction((ActionEvent event) -> {
            if (latRB.isSelected()) {
                squareLatWest();
            }
        });
        northRB.setOnAction((ActionEvent event) -> {
            if (lonRB.isSelected()) {
                squareLonNorth();
            }
        });
        southRB.setOnAction((ActionEvent event) -> {
            if (lonRB.isSelected()) {
                squareLonSouth();
            }
        });

        spaceXTF.setOnAction((ActionEvent event) -> {
            try {
                earthSpaceX = Double.parseDouble(spaceXTF.getText());
                spaceXTF.setText(Double.toString(earthSpaceX));

            } catch (NumberFormatException e) {
                earthSpaceX = DEFAULT_SIDE / 2;
                spaceXTF.setText(Double.toString(earthSpaceX));
            }

        });
        spaceYTF.setOnAction((ActionEvent event) -> {

            try {
                earthSpaceY = Double.parseDouble(spaceYTF.getText());
                spaceYTF.setText(Double.toString(earthSpaceY));

            } catch (NumberFormatException e) {
                earthSpaceY = DEFAULT_SIDE / 2;
                spaceYTF.setText(Double.toString(earthSpaceY));
            }

        });
        sideXTF.setOnAction((ActionEvent event) -> {

            try {
                tileSideX = Double.parseDouble(sideXTF.getText());
                sideXTF.setText(Double.toString(tileSideX));

            } catch (NumberFormatException e) {
                tileSideX = DEFAULT_SIDE;
                sideXTF.setText(Double.toString(tileSideX));
            }

        });
        sideYTF.setOnAction((ActionEvent event) -> {

            try {
                tileSideY = Double.parseDouble(sideYTF.getText());
                sideYTF.setText(Double.toString(tileSideY));

            } catch (NumberFormatException e) {
                tileSideY = DEFAULT_SIDE;
                sideYTF.setText(Double.toString(tileSideY));
            }

        });
        generationButton.setOnMouseClicked((MouseEvent event) -> {
            title = titleTF.getText();
            tilesCount = countTilesCB.getValue();
            line = column = (int) Math.sqrt(tilesCount);
            earthSpaceX = Double.parseDouble(spaceXTF.getText());
            spaceXTF.setText(Double.toString(earthSpaceX));
            earthSpaceY = Double.parseDouble(spaceYTF.getText());
            spaceYTF.setText(Double.toString(earthSpaceY));
            try {
                magnification = Double.valueOf(magnificationTF.getText());
            } catch (NumberFormatException e) {
                magnification = 1.0;
            }
            try {
                buoyageScale = Double.valueOf(buoyScaleTF.getText());
            } catch (NumberFormatException e) {
                buoyageScale = 1.0;
            }
            boolean base = baseCB.isSelected();
            //  wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
            guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
                wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
                // forEach tile
                Date date = new Date();
                String dateString = new SimpleDateFormat("dd:MMM:yyyy").format(date);

                for (int i = 0; i < tilesCount; i++) {
                    Geometry geom = initParameters(wwjTiles.get(i).getBoundaries());
                    if (choiceCB.getSelectionModel().getSelectedItem().equals("BathyDEPARE")) {
                        outFile = nameTF.getText() + "_" + dateString + "_" + i + 1 + ":" + tilesCount + ".csv";
                        outPathname = Paths.get(OUT_DIR, outFile);
                        BathyDepareStlController bathyDepareStlController
                                = new BathyDepareStlController(bathymetryDBServices,
                                        displayBathymetryServices,
                                        guiAgentServices,
                                        layer,
                                        squarePolygonEnvelope);
                        //Max de profondeur pour l'ensemble des tuiles
                        double maxElevation = bathyDepareStlController.getMaxElevation();
                        bathyDepareStlController.writePointList(positions, outPathname, false);
                        if (previewRB.isSelected()) {
                            bathyDepareStlController.displayDelaunaySounding(positions, layerBathy, maxElevation);
                        }
                    } else {
                        //if sur bathy ou mnt
                        outFile = nameTF.getText() + "_" + i + ".x3d";
                        outPathname = Paths.get(OUT_DIR, outFile);

                        new StlPreWriterController(outPathname, title,
                                tilesCount, i,
                                positions,
                                tileSideX, tileSideY,
                                earthSpaceX, earthSpaceY,
                                bottom, magnification,0.0).compute();
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation")
                                || choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation&Carto")) {
                            new ElevationStlController(outPathname, title,
                                    tilesCount, i,
                                    geodesyServices,
                                    positions,
                                    tileSideX, tileSideY,
                                    earthSpaceX, earthSpaceY,
                                    bottom, magnification,0.0
                            ).compute();
                        }
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("Carto")
                                || choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation&Carto")) {
                            s57StlChartComponentController.compute(outPathname,
                                    tilesCount,
                                    i,
                                    magnification,
                                    tileSideX, tileSideY,
                                    earthSpaceX, earthSpaceY,
                                    bottom,
                                    polygonEnvelope,
                                    geometryEnvelope);
                        }
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("BathyMNT")) {

                        }

                        new StlPostWriterController(outPathname).compute();
                    }
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                    wwjTiles.get(i).setAttributes(makeHighlightAttributes());
                    wwd.redrawNow();
                }
            });
        });
!!!132738.java!!!	displayChartBoundaries(inout polygon : KMLSurfacePolygonImpl) : void
        String result = WwjJTS.locationsToWKT(polygon.getLocations());
        WKTReader wkt = new WKTReader();
        Geometry geom = null;
        try {
            geom = wkt.read(result);
        } catch (ParseException ex) {
            Logger.getLogger(StlComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (geom != null) {
            polygonEnvelope = WwjJTS.wktPolygonToPolygon(geom.getEnvelope());
            envelopeList = polygonEnvelope.getBoundaries().get(0);
            lonRange = geodesyServices.getDistanceM(envelopeList.get(1), envelopeList.get(0));
            latRange = geodesyServices.getDistanceM(envelopeList.get(2), envelopeList.get(1));
            squareEnvelopeList = new ArrayList<>();
            envelopeList.forEach((p) -> {
                squareEnvelopeList.add(p);
            });
        }
        squareLatEast();
!!!132866.java!!!	squareLatEast() : void
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(1), 270, latRange);//270=West
        squareEnvelopeList.remove(0);
        squareEnvelopeList.add(0, newPosition);
        Position newP3 = new Position(squareEnvelopeList.get(3).getLatitude(),
                newPosition.getLongitude(), 100.0);
        squareEnvelopeList.remove(3);
        squareEnvelopeList.add(3, newP3);
        squareEnvelopeList.remove(4);
        squareEnvelopeList.add(4, newPosition);
        postNewPolygon();
!!!132994.java!!!	squareLatWest() : void
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(0), 90, latRange);//90=East
        squareEnvelopeList.remove(1);
        squareEnvelopeList.add(1, newPosition);
        Position newP = new Position(squareEnvelopeList.get(3).getLatitude(),
                newPosition.getLongitude(), 100.0);
        squareEnvelopeList.remove(2);
        squareEnvelopeList.add(2, newP);
        postNewPolygon();
!!!133122.java!!!	squareLonNorth() : void
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(1), 360, lonRange);//0=North
        squareEnvelopeList.remove(2);
        squareEnvelopeList.add(2, newPosition);
        Position newP3 = new Position(newPosition.getLatitude(),
                squareEnvelopeList.get(0).getLongitude(), 100.0);
        squareEnvelopeList.remove(3);
        squareEnvelopeList.add(3, newP3);
        postNewPolygon();
!!!133250.java!!!	squareLonSouth() : void
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(2), 180, lonRange);//180=south
        squareEnvelopeList.remove(1);
        squareEnvelopeList.add(1, newPosition);
        Position newP = new Position(newPosition.getLatitude(),
                squareEnvelopeList.get(0).getLongitude(), 100.0);
        squareEnvelopeList.remove(0);
        squareEnvelopeList.add(0, newP);
        squareEnvelopeList.remove(4);
        squareEnvelopeList.add(4, newP);
        postNewPolygon();
!!!133378.java!!!	preNewPolygon() : void
        squareEnvelopeList = new ArrayList<>();
        envelopeList.forEach((p) -> {
            squareEnvelopeList.add(p);
        });
!!!133506.java!!!	postNewPolygon() : void
        if (squarePolygonEnvelope != null) {
            layer.removeRenderable(squarePolygonEnvelope);
        }
        squarePolygonEnvelope = new Polygon(squareEnvelopeList);
        squarePolygonEnvelope.setAttributes(makeAttributes());
        layer.addRenderable(squarePolygonEnvelope);
        wwd.redrawNow();
!!!133634.java!!!	makeAttributes() : ShapeAttributes
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(Material.GREEN);
        normalAttributes.setOutlineOpacity(0.2);
        normalAttributes.setInteriorOpacity(0.2);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(true);
        return normalAttributes;
!!!133762.java!!!	makeHighlightAttributes() : ShapeAttributes
        ShapeAttributes highlightAttributes = new BasicShapeAttributes();
        highlightAttributes.setOutlineMaterial(Material.RED);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setInteriorMaterial(Material.RED);
        highlightAttributes.setInteriorOpacity(1);
        return highlightAttributes;
!!!133890.java!!!	displayTiles(inout polyEnveloppe : Polygon, in line : int, in col : int) : List<Polygon>
        layer.removeAllRenderables();
        Iterable<? extends LatLon> bounds = polyEnveloppe.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }
        latRange = listLatLon.get(0).getLatitude().getDegrees() - listLatLon.get(2).getLatitude().getDegrees();
        lonRange = listLatLon.get(2).getLongitude().getDegrees() - listLatLon.get(0).getLongitude().getDegrees();

        latRange /= line;
        lonRange /= col;
        double orgLat = listLatLon.get(0).getLatitude().getDegrees();
        double orgLon = listLatLon.get(0).getLongitude().getDegrees();

        tiles = new ArrayList<>();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                List<Position> positions0 = new ArrayList<>();
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                Polygon polygon1 = new Polygon(positions0);

                ShapeAttributes normalAttributes = new BasicShapeAttributes();
                normalAttributes.setInteriorMaterial(Material.RED);
                normalAttributes.setOutlineOpacity(0.5);
                normalAttributes.setInteriorOpacity(0.2);
                normalAttributes.setOutlineMaterial(Material.RED);
                normalAttributes.setOutlineWidth(2);
                normalAttributes.setDrawOutline(true);
                normalAttributes.setDrawInterior(true);
                normalAttributes.setEnableLighting(true);

                ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
                highlightAttributes.setOutlineMaterial(Material.RED);
                highlightAttributes.setOutlineOpacity(1);
                highlightAttributes.setInteriorMaterial(Material.RED);
                highlightAttributes.setInteriorOpacity(0.2);

                polygon1.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
                polygon1.setAttributes(normalAttributes);
                polygon1.setHighlightAttributes(highlightAttributes);
                tiles.add(polygon1);
            }
            layer.addRenderables(tiles);
            wwd.redrawNow();
        }
        return tiles;
!!!134018.java!!!	initParameters(inout pos : List<List<? extends Position>>) : Geometry
        Geometry geometry1 = null;
        positions = pos.get(0);
        latRangeMetric = geodesyServices.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));
        lonRangeMetric = geodesyServices.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        //   scaleLatFactor = (earthSpaceY) / latRangeMetric;
        //   scaleLonFactor = (earthSpaceX) / lonRangeMetric;
        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader0 = new WKTReader();

        if (wkt != null) {
            try {
                geometry1 = wktReader0.read(wkt);// l'enveloppe au sens de JTS, pour ne faire clacul q'une fois

                Geometry geometry = wktReader0.read(wkt);
                CoordinateList list = new CoordinateList(geometry.getCoordinates());
                list.closeRing();
                GeometryFactory geometryFactory = new GeometryFactory();
                LinearRing ring = geometryFactory.createLinearRing(list.toCoordinateArray());
                geometryEnvelope = geometryFactory.createPolygon(ring, null);

            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(StlComponentController.class
                        .getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return geometry1;
