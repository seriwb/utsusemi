package box.white.utsusemi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Constants {

    public static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    public static final String JDBC_DRIVER_POSTGRES = "org.postgresql.Driver";
    public static final String JDBC_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String JDBC_DRIVER_SQLITE = "org.sqlite.JDBC";

    public static final List<String> INCLUDE_DRIVER_LIST =
            Collections.unmodifiableList(
                    Arrays.asList(new String[]{"mysql", "ora", "oracle", "postgres", "sqlite"}));

    public static final String ENCODING = "UTF-8";
}
