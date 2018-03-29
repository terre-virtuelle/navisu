/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.relational.impl;

import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.database.relational.Database;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import org.capcaval.c3.component.ComponentState;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class DatabaseImpl
        implements Database, DatabaseServices, ComponentState {

    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private EntityManager entityManager = null;
    private final String PERSISTENCE_UNIT = "navisuPU";
    private final String DB_HOME = ".navisu/databases/";
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected Properties properties;
    private String userHome;
    private final String NAVISU_DB = "NaVisuDB";
    private String userDirPath;
    private int j = 0;
    private Set<String> tableSet;
    FileOutputStream loadFileLog = null;
    FileOutputStream errorFileLog = null;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Connection connect(String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd) {
        try {
            Class.forName(driverName);
            String url = protocol + hostName + ":" + port + "/" + dbName;
            System.out.println("url : " + url);
            connection = DriverManager.getConnection(url, userName, passwd);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return connection;
    }

    /**
     * Cas particulier de Derby
     *
     * @param dbName
     * @param passwd
     * @return
     */
    @Override
    public Connection connectDerby(String dbName, String user, String passwd) {
        //Le mot de passe doit faire plus de huit caracteres
        String url = "jdbc:derby:" + dbName
                + ";create=true;"
                + "dataEncryption=true"
                + ";user=" + user
                + ";bootPassword=" + passwd;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return connection;
    }

    /**
     *
     *
     * @param hostName
     * @param protocol
     * @param passwd
     * @param port
     * @param driverName
     * @param userName
     * @return
     */
    @Override
    public Connection connect(String hostName, String protocol, String port, String driverName, String userName, String passwd) {
        try {
            Class.forName(driverName);
            String url = protocol + hostName + ":" + port + "/";
            System.out.println("url " + url);
            connection = DriverManager.getConnection(url, userName, passwd);
            System.out.println("connection " + connection);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return connection;
    }

    @Override
    public void execute(String stmt) {
        try {
            this.statement.execute(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public ResultSet executeQuery(String query) {
        ResultSet r = null;
        try {
            r = this.statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return r;
    }

    @Override
    public PreparedStatement prepare(String statement) {
        try {
            preparedStatement = connection.prepareStatement(statement);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return preparedStatement;
    }

    @Override
    public void createIndex(String indexName, String tableName, String columnName) {
        try {
            statement.execute("CREATE INDEX " + indexName + " ON " + tableName + " USING GIST (" + columnName + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void drop(String tableName) {
        try {
            statement.execute("DROP TABLE " + tableName);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        try {
            Files.deleteIfExists(Paths.get(userHome + "/" + DB_HOME + NAVISU_DB + "/dbex.lck"));
            Files.deleteIfExists(Paths.get(userHome + "/" + DB_HOME + NAVISU_DB + "/db.lck"));
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public boolean isConnect() {
        return connection != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public EntityManager getEntityManager() {

        if (entityManager == null) {
            userHome = System.getProperty("user.home");
            Map propertiesMap = new HashMap();

            // Ensure RESOURCE_LOCAL transactions is used.
            propertiesMap.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

            // Configure the internal EclipseLink connection pool
            propertiesMap.put(JDBC_DRIVER, "org.apache.derby.jdbc.EmbeddedDriver");
            propertiesMap.put(JDBC_URL, "jdbc:derby:" + userHome + "/" + DB_HOME + NAVISU_DB + ";create=true");
            propertiesMap.put(JDBC_USER, "navisu");
            propertiesMap.put(JDBC_PASSWORD, "navisu");

            // Developpement conf
            //propertiesMap.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
            // Production conf
            propertiesMap.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_ONLY);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, propertiesMap);
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public String shapeFileToSql(String path, String shpDir, String epsg) {
        userDirPath = System.getProperty("user.dir");
        try {
            Files.createDirectory(Paths.get(userDirPath + "/data/sql/"));
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        Map<String, String> environment = new HashMap<>(System.getenv());
        String options = System.getProperty("user.dir") + "/gdal/data";
        environment.put("GDAL_DATA", options);
        String cmd = startCmd(path, "/shp2pgsql");

        //Search all different tables
        tableSet = new HashSet<>();
        List<Path> refPathList = new ArrayList<>();
        try (Stream<Path> filePathStream = Files.walk(Paths.get("data/shp"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String[] nameTab = filePath.getFileName().toString().split(Pattern.quote("."));
                    if (nameTab[1].equals("shp")) {
                        if (tableSet.add(nameTab[0])) {
                            refPathList.add(filePath);
                        }
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        //Option -p, put shp2pgsql in create mode, only create table
        //The refPathList contains the set of all tables.
        Stream<Path> filePathStream = refPathList.stream();
        filePathStream.forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                String[] nameTab = filePath.getFileName().toString().split(Pattern.quote("."));
                if (nameTab[1].equals("shp")) {
                    try {
                        Proc.BUILDER.create()
                                .setCmd(cmd)
                                .addArg("-p -I -i -s " + epsg)
                                .addArg(userDirPath + "/" + filePath)
                                .addArg(nameTab[0])
                                .setOut(new FileOutputStream(userDirPath + "/data/sql/" + nameTab[0] + ".sql", true))
                                .setErr(System.err)
                                .exec(environment);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                    }
                }
            }
        });

        //Alter varchar(80) to varchar in each tables
        try {
            filePathStream = Files.walk(Paths.get("data/sql"));
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String[] nameTab = filePath.getFileName().toString().split(Pattern.quote("."));
                    if (nameTab[1].equals("sql")) {
                        try {
                            try (Stream<String> lines = Files.lines(filePath)) {
                                String content = new String(Files.readAllBytes(filePath));
                                content = content.replaceAll("varchar\\(80\\)", "varchar");
                                Files.write(filePath, content.getBytes());
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                        }
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        //Option -a : appends shape file into current table
        try {
            filePathStream = Files.walk(Paths.get("data/shp"));
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String[] nameTab = filePath.getFileName().toString().split(Pattern.quote("."));
                    if (nameTab[1].equals("shp")) {
                        try {
                            Proc.BUILDER.create()
                                    .setCmd(cmd)
                                    .addArg("-a -I -i -s " + epsg)
                                    .addArg(userDirPath + "/" + filePath)
                                    .addArg(nameTab[0])
                                    .setOut(new FileOutputStream(userDirPath + "/data/sql/" + nameTab[0] + ".sql", true))
                                    .setErr(System.err)
                                    .exec(environment);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                        }
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return userDirPath + "/data/sql";
    }

    @Override
    public void sqlToSpatialDB(String databaseName, String user, String passwd, String dir, String cmd) {
        userDirPath = System.getProperty("user.dir");
        Map<String, String> environment = new HashMap<>(System.getenv());
        environment.put("PGPASSWORD", "admin");
        try {
            loadFileLog = new FileOutputStream("load.log", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        try (Stream<Path> filePathStream = Files.walk(Paths.get("data/sql"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String[] nameTab = filePath.getFileName().toString().split(Pattern.quote("."));
                    if (nameTab[1].equals("sql")) {
                        try {
                            Proc.BUILDER.create()
                                    .setCmd(cmd)
                                    .addArg("-U admin -h localhost -d " + databaseName + " ").addArg("-f ")
                                    .addArg(userDirPath + "/" + filePath)
                                    .setOut(loadFileLog)
                                    .exec(environment);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                        }
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public String spatialDBToShapefile(String table, String attributes,
            String databaseName, String user, String passwd,
            double latMin, double lonMin, double latMax, double lonMax) {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
            userDirPath = System.getProperty("user.dir");
            String path = properties.getProperty("gdalPath");
            String cmd = "/ogr2ogr";
            cmd = startCmd(path, cmd);
            if (path == null) {
                //alarm
            }
            String command = createCmdSh(table, attributes, cmd, databaseName, user, passwd,
                    latMin, lonMin, latMax, lonMax);

            Proc.BUILDER.create()
                    .setCmd(userDirPath + "/" + command)
                    .exec();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return userDirPath + "/tmp/" + table + ".shp";
    }

    private String startCmd(String path, String command) {
        String cmd = null;
        if (OS.isWindows()) {
            cmd = path + command;
        } else if (OS.isLinux()) {
            cmd = path + command;
        } else if (OS.isMac()) {
            cmd = path + command;
        } else {
            System.out.println("OS not found");
        }
        return cmd;
    }

    private String createCmdSh(String table, String attributes,
            String cmd,
            String databaseName, String user, String passwd,
            double latMin, double lonMin, double latMax, double lonMax) {

        String command = cmd
                + " -f \"ESRI Shapefile\" tmp/"
                + "tmp.shp PG:\"host=localhost "
                + "user=" + user
                + " password=" + passwd
                + " dbname=" + databaseName
                + "\" -sql \"SELECT " + attributes + " FROM " + table
                + " WHERE geom && ST_MakeEnvelope("
                + lonMin + "," + latMin + "," + lonMax + "," + latMax + ")\"";

        command += "\n";

        command += cmd
                + " -clipdst "
                + Double.toString(lonMin) + " " + Double.toString(latMin) + " "
                + Double.toString(lonMax) + " " + Double.toString(latMax) + " "
                + "tmp/" + table + ".shp tmp/" + "tmp.shp";

        String cmdFile = "tmp/cmd.sh";
        try {
            Files.write(Paths.get(cmdFile), command.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        chmodCmd(cmdFile);
        return cmdFile;
    }

    private String chmodCmd(String cmdFile) {
        String cmd = null;
        if (OS.isWindows()) {
            LinkedOSLibrary linkedLibrary
                    = (LinkedOSLibrary) Native.loadLibrary("c", LinkedOSLibrary.class);
            linkedLibrary.chmod(cmdFile, 0777);
        } else if (OS.isLinux()) {
            File file = new File(cmdFile);
            file.setReadable(true,false);
            file.setWritable(true,false);
            file.setExecutable(true,false);
        } else {
            System.out.println("OS not found");
        }
        return cmd;
    }
}

interface LinkedOSLibrary extends Library {

    public int chmod(String path, int mode);
}
