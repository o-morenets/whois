/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.whois.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.whois.model.StatusInfo;
import ru.whois.repository.StatusRepository;

@RestController
@RequestMapping("api/whois")
public class StatusController {

 

  @Autowired
  StatusRepository repository;
  

   @PostMapping("/getstatusinfo")
  public List<StatusInfo> findStatusInfo(@RequestBody Request request) {
  List<StatusInfo> status = repository.findStatusInfo(request.domain);
return status;
  }

}

