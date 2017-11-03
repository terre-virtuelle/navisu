class BathymetryDBController
!!!158978.java!!!	BathymetryDBController(inout component : BathymetryDBImpl, inout databaseServices : DatabaseServices, inout guiAgentServices : GuiAgentServices, inout bathymetryEventProducerServices : BathymetryEventProducerServices, in limit : double, inout layer : RenderableLayer)
        this.component = component;
        this.databaseServices = databaseServices;
        this.guiAgentServices = guiAgentServices;
        this.bathymetryEventProducerServices = bathymetryEventProducerServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();

        wwd.addPositionListener((PositionEvent event) -> {
            Position pos = event.getPosition();
            try {
                if (pos != null && connection != null && !connection.isClosed() && pos.getAltitude() < 20.0) {
                    //  points = bathymetryDBImpl.retrieve(pos.getLatitude().getDegrees(), pos.getLongitude().getDegrees());
                }
            } catch (SQLException ex) {
                Logger.getLogger(BathymetryDBController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        });
!!!159106.java!!!	getInstance(inout component : BathymetryDBImpl, inout databaseServices : DatabaseServices, inout guiAgentServices : GuiAgentServices, inout bathymetryEventProducerServices : BathymetryEventProducerServices, in limit : double, inout layer : RenderableLayer) : BathymetryDBController
        if (INSTANCE == null) {
            INSTANCE = new BathymetryDBController(component,
                    databaseServices, guiAgentServices, bathymetryEventProducerServices,
                    limit, layer);
        }
        return INSTANCE;
!!!159234.java!!!	connect(in dbName : String, in hostName : String, in protocol : String, in port : String, in driverName : String, in userName : String, in passwd : String, in dataFileName : String) : Connection
        this.dataFileName = dataFileName;
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        System.out.println(dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        String sql = "INSERT INTO " + "bathy" + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        //  create(dataFileName);
        createIndex();
        return connection;
!!!159362.java!!!	connect(in dbName : String, in hostName : String, in protocol : String, in port : String, in driverName : String, in userName : String, in passwd : String) : Connection
        System.out.println(dbName + " " + hostName + " " + protocol + " " + port + " " + driverName + " " + userName + " " + passwd);
        this.connection = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return connection;
!!!159490.java!!!	create(in filename : String) : void
        String sql = "INSERT INTO " + "bathy" + " (coord, elevation) "
                + "VALUES ( ST_SetSRID(ST_MakePoint(?, ?), 4326), ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        guiAgentServices.getJobsManager().newJob("create", (progressHandle) -> {
            String query = "DROP TABLE IF EXISTS  bathy; "
                    + "CREATE TABLE bathy("
                    + "gid SERIAL PRIMARY KEY,"
                    + "coord GEOMETRY(POINT, 4326), "
                    + "elevation FLOAT); ";
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }

            points3df = readFromFile(filename);
            insert(points3df);
            // createIndex();
        });
!!!159618.java!!!	readFromFile(in filename : String) : List<Point3Df>
        List<Point3Df> tmp = new ArrayList<>();
        try {
            tmp = Files.lines(new File(filename).toPath())
                    .map(line -> line.trim())
                    // .map(line -> line.split("\t"))
                    .map(line -> line.split(" "))
                    .map(tab -> new Point3Df(Float.parseFloat(tab[0]),
                    Float.parseFloat(tab[1]),
                    Float.parseFloat(tab[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp;
!!!159746.java!!!	insert(inout points : List<Point3Df>) : void
        points.stream().forEach((p) -> {
            try {
                preparedStatement.setDouble(1, p.getLon());
                preparedStatement.setDouble(2, p.getLat());
                preparedStatement.setDouble(3, p.getElevation());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        });
!!!159874.java!!!	createIndex() : void
        guiAgentServices.getJobsManager().newJob("createIndex", (progressHandle) -> {
            try {
                connection.createStatement().executeUpdate("CREATE INDEX bathyindex ON bathy USING GIST (coord)");
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        });
!!!160002.java!!!	retrieveAll() : List<Point3D>
        List<Point3D> tmp1 = new ArrayList<>();
        //  guiAgentServices.getJobsManager().newJob("retrieveAll", (progressHandle) -> {
        PGgeometry geom;
        double depth;
        try {
            ResultSet r = connection.createStatement().executeQuery("SELECT  coord, elevation FROM bathy");
            while (r.next()) {
                geom = (PGgeometry) r.getObject(1);
                depth = r.getFloat(2);
                if (depth >= MIN_DEPTH) {
                    Point3D pt = new Point3D(geom.getGeometry().getFirstPoint().getX(),
                            geom.getGeometry().getFirstPoint().getY(),
                            depth);
                    tmp1.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        // });
        return tmp1;
!!!160130.java!!!	retrieveIn(in latMin : double, in lonMin : double, in latMax : double, in lonMax : double) : List<Point3D>
        List<Point3D> tmp1 = new ArrayList<>();
        PGgeometry geom;
        double depth;
        ResultSet r;

        try {
            r = connection.createStatement().executeQuery(
                    "SELECT *"
                    + "FROM bathy "
                    + "WHERE coord @ ST_MakeEnvelope ("
                    + latMin + ", " + lonMin + ", "
                    + latMax + ", " + lonMax
                    + ", 4326) ");

            while (r.next()) {
                geom = (PGgeometry) r.getObject(2);
                depth = r.getFloat(3);
                if (depth >= MIN_DEPTH) {
                    Point3D pt = new Point3D(geom.getGeometry().getFirstPoint().getX(),
                            geom.getGeometry().getFirstPoint().getY(),
                            depth);
                    tmp1.add(pt);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return tmp1;
!!!160258.java!!!	retrieveAround(in lat : double, in lon : double, in limit : double) : List<Point3D>
        PGgeometry geom;
        ResultSet r0;
        ResultSet r1;
        points3d.clear();
        layer.removeAllRenderables();
        try {
            r0 = connection.createStatement().executeQuery(
                    "SELECT coord,elevation "
                    + "FROM bathy "
                    + "ORDER BY coord <-> ST_SetSRID("
                    + "ST_MakePoint(" + Double.toString(lon) + ", " + Double.toString(lat) + "), 4326) "
                    + "LIMIT " + limit);
            while (r0.next()) {
                geom = (PGgeometry) r0.getObject(1);
                longitude = geom.getGeometry().getFirstPoint().getX();
                latitude = geom.getGeometry().getFirstPoint().getY();
                r1 = connection.createStatement().executeQuery(
                        "SELECT ST_DISTANCE("
                        + "ST_SetSRID(ST_MakePoint(" + longitude
                        + ", " + latitude + "), 4326)::geography,"
                        + "ST_SetSRID(ST_MakePoint(" + Double.toString(lon)
                        + ", " + Double.toString(lat) + "), 4326)::geography"
                        + ");");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        bathymetryEventProducerServices.setBathymetry(new Bathymetry(points3d));
        return points3d;
!!!160386.java!!!	mergeData(inout orgData : Point3D, in nbLat : int, in nbLon : int, inout triangles : List<Triangle_dt>) : Point3D
        Point3D[][] tmp = new Point3D[nbLat][nbLon];
        for (int k = 0; k < nbLat; k++) {
            System.arraycopy(orgData[k], 0, tmp[k], 0, nbLon);
        }
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                //Select one point
                Point3D p = tmp[k][l];
                Point_dt pp = new Point_dt(p.getLat(), p.getLon(), p.getElevation());
                for (Triangle_dt tt : triangles) {
                    // Research  the nearest point of this triangle
                    if (tt.contains(pp)) {
                        distA = tt.A.distance(pp);
                        distB = tt.B.distance(pp);
                        distC = tt.C.distance(pp);
                        distMin = distA;
                        pMin = tt.A;
                        if (distMin > distB) {
                            distMin = distB;
                            pMin = tt.B;
                        }
                        if (distMin > distC) {
                            distMin = distC;
                            pMin = tt.C;
                        }
                        tmp[k][l].setElevation(pMin.z);
                    }
                }
            }

        }
        return tmp;
!!!160514.java!!!	getConnection() : Connection
        return connection;
!!!160642.java!!!	writePointList(inout points : List<Point3D>, inout pathname : Path, inout latLon : boolean) : void
        List<String> lines = new ArrayList<>();
        if (points != null) {
            if (latLon == true) {
                points.forEach((p) -> {
                    lines.add(p.getLon() + " " + p.getLat() + " " + p.getElevation());
                });
            } else {
                points.forEach((p) -> {
                    lines.add(p.getLat() + " " + p.getLon() + " " + p.getElevation());
                });
            }
            try {
                Files.write(pathname, lines, charset, StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(DisplayBathymetryController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
