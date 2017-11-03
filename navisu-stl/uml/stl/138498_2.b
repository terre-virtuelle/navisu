class DisplayBathymetryController
!!!164354.java!!!	DisplayBathymetryController(inout component : DisplayBathymetryImpl, inout bathymetryDBServices : BathymetryDBServices, inout guiAgentServices : GuiAgentServices, inout displayServices : DisplayServices, inout delaunayServices : DelaunayServices, inout jtsServices : JTSServices, inout layer : RenderableLayer)
        this.component = component;
        this.bathymetryDBServices = bathymetryDBServices;
        this.guiAgentServices = guiAgentServices;
        this.displayServices = displayServices;
        this.delaunayServices = delaunayServices;
        this.jtsServices = jtsServices;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
!!!164482.java!!!	getInstance(inout component : DisplayBathymetryImpl, inout bathymetryDBServices : BathymetryDBServices, inout guiAgentServices : GuiAgentServices, inout displayServices : DisplayServices, inout delaunayServices : DelaunayServices, inout jtsServices : JTSServices, inout layer : RenderableLayer) : DisplayBathymetryController
        if (INSTANCE == null) {
            INSTANCE = new DisplayBathymetryController(component,
                    bathymetryDBServices, guiAgentServices,
                    displayServices, delaunayServices,
                    jtsServices,
                    layer);
        }
        return INSTANCE;
!!!164610.java!!!	displayAllSounding(in minLat : double, in minLon : double, in maxLat : double, in maxLon : double, in nbLat : int, in nbLon : int) : void

        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {
            points3d = bathymetryDBServices.retrieveIn(minLat, minLon, maxLat, maxLon);
            double latM = 90.0;
            double lonM = 0.0;
            for (Point3D p : points3d) {
                if (latM >= p.getLat()) {
                    latM = p.getLat();
                }
                if (lonM >= p.getLon()) {
                    lonM = p.getLon();
                }
            }

        } // plusieurs jobs
                ,
                 (progressHandle) -> {
                    //Rechercher le max de bathy, z = max - elevation, pour le positionnement
                    maxElevation = 0.0;
                    points3d.stream().filter((pt) -> (maxElevation < pt.getElevation())).forEach((pt) -> {
                        maxElevation = pt.getElevation();
                    });
                    System.out.println("maxElevation : " + maxElevation);

                    //Display plane 0m over sea
                    // displayServices.displayPlane(minLat, minLon, maxLat, maxLon, 100, Material.BLUE, layer);
                    //Display plane maxElevation*10 over sea
                    //  displayServices.displayPlane(minLat, minLon, maxLat, maxLon, maxElevation * 10, Material.GREEN, layer);
                    //Create Delaunay triangulation with bathymetry data
                    List<Triangle_dt> triangles = delaunayServices.createDelaunay(points3d, maxElevation);
                    //Suppress large edges
                    List<Triangle_dt> triangles1 = delaunayServices.filterLargeEdges(triangles, THRESHOLD);
                    displayServices.displayDelaunay(triangles1, maxElevation, 10.0, Material.GREEN, layer);

                    //Create concaveHull from points with bathy information
                    //  concaveHull = jtsServices.getConcaveHull(points3d, THRESHOLD);
                    // displayServices.displayConcaveHull(concaveHull, maxElevation, 10.0, Material.RED, layer);
                    //Create a grid of points for triangulate sea level plane 
                    // Point3D[][] seaPlane = delaunayServices.toGrid(minLat, minLon, 100.0, 100.0, nbLat, nbLon, maxElevation);
                    //Modifie the z whith bathyletry data
                    //    seaPlane = bathymetryDBServices.mergeData(seaPlane, nbLat, nbLon, triangles1);
                    //   List<Triangle_dt> triangles2 = delaunayServices.createDelaunay(seaPlane, nbLat, nbLon, 0.0);
                    //     displayServices.displayDelaunay(triangles2, maxElevation, 10.0, Material.YELLOW, layer);
                    wwd.redrawNow();
                });

!!!164738.java!!!	displaySounding(in lat : double, in lon : double, in depth : double, inout l : RenderableLayer) : void
        BasicShapeAttributes basicShapeAttributes = new BasicShapeAttributes();
        basicShapeAttributes.setOutlineOpacity(1.0);
        SurfaceSquare surface
                = new SurfaceSquare(new LatLon(Angle.fromDegrees(lat), Angle.fromDegrees(lon)), depth);
        surface.setAttributes(basicShapeAttributes);
        String label = "Lat : " + nf4.format(lat) + "°\n"
                + "Lon : " + nf4.format(lon) + "°\n"
                + "Depth : " + nf1.format(depth) + "m";
        surface.setValue(AVKey.DISPLAY_NAME, label);

        l.addRenderable(surface);
!!!164866.java!!!	displaySounding(inout points : List<Point3D>, inout l : RenderableLayer) : void

        points.stream().forEach((pt) -> {
            displaySounding(pt.getLat(),
                    pt.getLon(),
                    pt.getElevation(), l);
        });

!!!164994.java!!!	displayDelaunaySounding(inout points : List<Point3D>, inout layer : RenderableLayer, in maxElevation : double) : void
        guiAgentServices.getJobsManager().newJob("displayAllSounding", (progressHandle) -> {
            //Create Delaunay triangulation with bathymetry data
            List<Triangle_dt> triangles = delaunayServices.createDelaunay(points, maxElevation);
            //Suppress large edges
            List<Triangle_dt> triangles1 = delaunayServices.filterLargeEdges(triangles, THRESHOLD);
            displayServices.displayDelaunay(triangles1, maxElevation, 10.0, Material.GREEN, layer);
            wwd.redrawNow();
        });
