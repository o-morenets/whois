package ru.whois.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TemporaryModel {

  Long id;
  Long oid;
  String name;
  Long tld;
  String registrant;
  String c_admin;
  String c_tech;
  String c_bill;
  Date expired;
}
