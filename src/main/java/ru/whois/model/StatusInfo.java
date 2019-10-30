package ru.whois.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusInfo {

    private String created;
    private String updated;
    private String status;
    private String registarcr;
    private String registrar;
}
