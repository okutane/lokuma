package ru.urururu.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApiController apiController;

    @Before
    public void setUp() {
        Application app = new Application();
        app.path = "gh/test/test";

        applicationRepository.save(app);
    }

    @After
    public void tearDown() {
        applicationRepository.deleteAll();
    }

    @Test
    public void listApplications() throws Exception {
        assertEquals(Arrays.asList("gh/test/test"), apiController.listApplications());
    }

    @Test
    public void publishReport() throws Exception {

    }
}