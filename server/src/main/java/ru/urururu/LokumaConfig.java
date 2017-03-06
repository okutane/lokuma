package ru.urururu;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
@Configuration
public class LokumaConfig {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getProperty("CLEARDB_DATABASE_URL", System.getenv("CLEARDB_DATABASE_URL")));

        String[] dbUriUserInfo = dbUri.getUserInfo().split(":");
        String username = dbUriUserInfo[0];
        String password = dbUriUserInfo.length == 2 ? dbUriUserInfo[1] : null;
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
