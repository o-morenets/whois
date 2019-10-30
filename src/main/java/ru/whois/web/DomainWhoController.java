package ru.whois.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.whois.model.ContactInfo;
import ru.whois.model.DomainInfo;
import ru.whois.model.HostInfo;
import ru.whois.model.StatusInfo;
import ru.whois.repository.ContactsRepository;
import ru.whois.repository.DomainRepository;
import ru.whois.repository.HostRepository;
import ru.whois.repository.StatusRepository;

import java.util.List;

@RestController
@RequestMapping("api/whois")
public class DomainWhoController {

    @Autowired
    DomainRepository domainRepository;

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    HostRepository hostRepository;

    @Autowired
    StatusRepository statusRepository;

    @PostMapping
    public String whoIs(@RequestBody Request request) {
        DomainInfo domainInfo = domainRepository.findDomainInfo(request.getDomain());
        ContactInfo contactInfo = contactsRepository.findContactInfo(request.getDomain());
        List<HostInfo> hostInfo = hostRepository.findHostInfo(request.getDomain());
        List<StatusInfo> statusInfo = statusRepository.findStatusInfo(request.getDomain());

        return report(domainInfo, contactInfo, hostInfo, statusInfo);
    }

    private String report(DomainInfo domainInfo, ContactInfo contactInfo, List<HostInfo> hostInfo, List<StatusInfo> statusInfo) {
        return null;
    }
}
