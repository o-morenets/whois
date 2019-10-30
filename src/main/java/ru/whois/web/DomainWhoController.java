package ru.whois.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.whois.model.WhoIsInfo;
import ru.whois.repository.DomainRepository;

@RestController
@RequestMapping("api/whois")
public class DomainWhoController {

    @Autowired
    DomainRepository repository;

    @PostMapping
    public String whois(@RequestBody Request request) {
        return repository.findWhoIsInfo(request);
    }
}
