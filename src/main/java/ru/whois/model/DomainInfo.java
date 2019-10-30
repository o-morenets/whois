package ru.whois.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomainInfo {

    String domainName;
    String name;
    String organizationName;
    String streetAddress;
    String city;
    String state;
    String postalCode;
    String country;
}