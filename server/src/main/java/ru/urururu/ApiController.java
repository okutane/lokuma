package ru.urururu;

import org.springframework.web.bind.annotation.*;
import ru.urururu.lokuma.reporter.LokumaReport;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
@RestController
public class ApiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/publish-report")
    public void publishReport(@RequestHeader(value = "X-Token") String token, @RequestBody LokumaReport report) {

    }
}
