package ru.urururu.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.urururu.lokuma.reporter.LokumaReport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
@RestController
public class ApiController {
    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping("/api/list-applications")
    public List<String> listApplications() {
        return applicationRepository.findAll().stream().map(a -> a.path).collect(Collectors.toList());
    }

    @RequestMapping("/api/publish-report")
    public void publishReport(@RequestHeader(value = "X-Token") String token, @RequestBody LokumaReport report) {
        Application application = applicationRepository.findByToken(token);
    }
}
