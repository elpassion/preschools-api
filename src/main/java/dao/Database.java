package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * Created by aserafin on 26/11/15.
 */
public class Database {

    private static Sql2o sql2o;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        config.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
        config.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
        config.setMaximumPoolSize(10);

        HikariDataSource source = new HikariDataSource(config);

        sql2o = new Sql2o(source);
    }

    public static Connection getConnection() {
        return sql2o.open();
    }
}
