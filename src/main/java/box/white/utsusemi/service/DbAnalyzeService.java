package box.white.utsusemi.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import box.white.utsusemi.Constants;
import box.white.utsusemi.model.DatabaseModel;
import box.white.utsusemi.model.SchemaDataModel;
import box.white.utsusemi.model.UtsusemiConfigModel;

/**
 * @author seri
 *
 */
public class DbAnalyzeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UtsusemiConfigModel config;

    public DbAnalyzeService(UtsusemiConfigModel config) {
        this.config = config;
    }

    /**
     * @throws Exception
     */
    public void anaylze() throws Exception {
        logger.info("Starting schema analysis");

        long start = System.currentTimeMillis();
        long startDiagrammingDetails = start;
        long startSummarizing = start;

        // ディレクトリチェック（TODO:初期化に移動）と作成（TODO:HTMLに移動）
        File outputDir = new File(config.getOutputDirectory());
        if (!outputDir.isDirectory()) {
            if (!outputDir.mkdirs()) {
                throw new IOException("Failed to create directory '" + outputDir + "'");
            }
        }

//        ConfigureComponent configure = new ConfigureComponent();
        Properties properties = getDbProperties(config.getDatabaseType());

        Connection conn = getConnection();
        if (conn == null) {
            throw new Exception("Cannot connect database!");
        }

        DatabaseMetaData meta = conn.getMetaData();
        String dbName = config.getDbName();

        // DBからutsusemiで必要なデータを取得
        DatabaseModel dbModel = new DatabaseModel(config, conn, meta, properties);

        // HTMLの出力先作成
        new File(outputDir, "tables").mkdirs();
        new File(outputDir, "diagrams/summary").mkdirs();

        logger.info("Connected to " + meta.getDatabaseProductName() + " - " + meta.getDatabaseProductVersion());


    }

    /**
     * @param type
     * @return
     * @throws IOException
     */
    public Properties getDbProperties(String type) throws IOException {

        Properties properties = new Properties();
        try (Reader reader = new InputStreamReader(
                ClassLoader.getSystemResourceAsStream(
                        "dbproperties/" + type + ".properties"), Constants.ENCODING)) {
            properties.load(reader);
        }

        return properties;
    }

    /**
     * @return
     */
    protected SchemaDataModel getSchemaData() {
        return null;
    }

    /**
     * @param connectionUrl
     * @param driverClass
     * @param driverPath
     * @return
     * @throws Exception
     */
    private Connection getConnection() throws Exception {
//            String connectionUrl,
//            String driverClass,
//            String driverPath) throws Exception {

        Connection connection = null;

        // mysql/postgres/oracle/sqliteの場合は別の方法でコネクションを作成する
        if (Constants.INCLUDE_DRIVER_LIST.contains(config.getDatabaseType())) {
            connection = getReservedConnection();
        }
        else {
            // TODO:しばらく動作チェックしないのでコメントアウト（通れば必ずエラー）
//            connection = getUnreservedConnection(connectionUrl, driverClass, driverPath);
        }

        return connection;
    }

    /**
     * @param connectionURL
     * @return
     * @throws SQLException
     */
    private Connection getReservedConnection() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String databaseType = config.getDatabaseType();
        try {
            if ("mysql".equals(databaseType)) {
                Class.forName(Constants.JDBC_DRIVER_MYSQL);

                sb.append("jdbc:mysql://").append(config.getHost());
                String port = StringUtils.isBlank(config.getPort()) ? "3306" : config.getPort();
                sb.append(":").append(port).append("/");
                sb.append(config.getDbName()).append("?user=").append(config.getUser());
                sb.append("&password=").append(config.getPassword());
            } else if ("postgres".equals(databaseType)) {
                // TODO
            } else if ("ora".equals(databaseType) || "oracle".equals(databaseType)) {
                // TODO
            } else if ("sqlite".equals(databaseType)) {
                // TODO
            } else {
                throw new InternalError("Unexpected call! " + databaseType + " is not expected.");
            }
        } catch (ClassNotFoundException e) {
            throw new InternalError(e);
        }

        return DriverManager.getConnection(sb.toString());
    }

    /**
     * WIP
     * @param connectionUrl
     * @param driverClass
     * @param driverPath
     * @return
     * @throws Exception
     */
    private Connection getUnreservedConnection(
            String connectionUrl,
            String driverClass,
            String driverPath) throws Exception {

        List<URL> classpath = new ArrayList<>();
        List<File> invalidClasspathEntries = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(driverPath, File.pathSeparator);
        while (tokenizer.hasMoreTokens()) {
            File pathElement = new File(tokenizer.nextToken());
            if (pathElement.exists())
                classpath.add(pathElement.toURI().toURL());
            else
                invalidClasspathEntries.add(pathElement);
        }

        URLClassLoader loader = new URLClassLoader(classpath.toArray(new URL[classpath.size()]));
        Driver driver = null;
        try {
            driver = (Driver) Class.forName(driverClass, true, loader).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new Exception(e);
        }
        if (driver == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot load JDBC driver: ").append(driverClass);
            logger.error(sb.toString());

            throw new Exception(sb.toString());
        }

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", config.getUser());
        connectionProperties.put("password", config.getPassword());

        return driver.connect(connectionUrl, connectionProperties);
    }
}
