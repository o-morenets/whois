package ru.whois.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.whois.model.ContactInfo;
import ru.whois.repository.ContactsRepository;

@RestController
@RequestMapping("api/whois")
public class ContactWhoController {

  @Autowired
  ContactsRepository repository;
  

  @PostMapping("/contact")
  public ContactInfo findContactInfo(@RequestBody Request request) {
    ContactInfo findContactInfo = repository.findContactInfo(request.getDomain());
	return findContactInfo;
  }

}
