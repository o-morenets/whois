package ru.whois.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.whois.model.*;
import ru.whois.repository.ContactsRepository;
import ru.whois.repository.DomainRepository;
import ru.whois.repository.HostRepository;
import ru.whois.repository.StatusRepository;

import java.util.List;

@RestController
@RequestMapping("api/whois")
public class DomainWhoIsController {

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
        System.out.println(request);
        DomainInfo domainInfo = domainRepository.findDomainInfo(request.getDomain());
        ContactInfo contactInfo = contactsRepository.findContactInfo(request.getDomain());
        List<HostInfo> hostInfo = hostRepository.findHostInfo(request.getDomain());
        List<StatusInfo> statusInfo = statusRepository.findStatusInfo(request.getDomain());

        return report(domainInfo, contactInfo, hostInfo, statusInfo);
    }

    private String report(DomainInfo domainInfo, ContactInfo contactInfo, List<HostInfo> hostInfo, List<StatusInfo> statusInfo) {
        StringBuffer sb = new StringBuffer();
        sb
                .append("Domain Name............: ").append(domainInfo.getDomainName()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Organization Using Domain Name").append(System.lineSeparator())
                .append("Name...................: ").append(domainInfo.getName()).append(System.lineSeparator())
                .append("Organization Name......: ").append(domainInfo.getOrganizationName()).append(System.lineSeparator())
                .append("Street Address.........: ").append(domainInfo.getStreetAddress()).append(System.lineSeparator())
                .append("City...................: ").append(domainInfo.getCity()).append(System.lineSeparator())
                .append("State..................: ").append(domainInfo.getState()).append(System.lineSeparator())
                .append("Postal Code............: ").append(domainInfo.getPostalCode()).append(System.lineSeparator())
                .append("Country................: ").append(domainInfo.getCountry()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Administrative Contact/Agent").append(System.lineSeparator())
                .append("Nick Handle............: ").append(contactInfo.getHandle()).append(System.lineSeparator())
                .append("Phone Number...........: ").append(contactInfo.getVoice()).append(System.lineSeparator())
                .append("Fax....................: ").append(contactInfo.getFax()).append(System.lineSeparator())
                .append("Email Address..........: ").append(contactInfo.getEmail()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Nameserver in listed order").append(System.lineSeparator())
                .append(System.lineSeparator());
        // TODO

        return sb.toString();
    }
}
