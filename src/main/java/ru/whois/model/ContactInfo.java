package ru.whois.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactInfo {

    private String handle;
    private String name;
    private String voice;
    private String fax;
    private String email;
}
