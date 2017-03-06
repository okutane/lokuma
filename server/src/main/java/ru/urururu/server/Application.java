package ru.urururu.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
@Entity
public class Application {
    @Id
    @GeneratedValue
    long id;

    String token;

    String path;
}
