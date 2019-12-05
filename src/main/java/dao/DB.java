package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;

public class DB {
    private static

    URI dbUri;
    static Logger logger = LoggerFactory.getLogger(DB.class);
    public static Sql2o sql2o;

    static {
        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/ekub");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? "moringa" : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? "Access" : dbUri.getUserInfo().split(":")[1];

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);

        } catch (URISyntaxException e) {
            logger.error("Unable to connect to the database.");
        }

    }
}
