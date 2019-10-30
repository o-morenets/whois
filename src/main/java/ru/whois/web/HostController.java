package ru.whois.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.whois.model.HostInfo;
import ru.whois.repository.HostRepository;

@RestController
@RequestMapping("api/whois")
public class HostController {

  @Autowired
  HostRepository repository;
  

   @PostMapping("/gethostinfo")
  public List<HostInfo> findContactInfo(@RequestBody Request request) {
  List<HostInfo> hosts = repository.findHostInfo(request.domain);
return hosts;
  }

}
