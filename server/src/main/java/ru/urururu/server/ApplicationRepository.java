package ru.urururu.server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findByPath(String path);

    Application findByToken(String token);
}
