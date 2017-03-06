package ru.urururu;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LokumaApplicationTests {
	@Autowired
	BasicDataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		dataSource.getConnection();
	}

}
