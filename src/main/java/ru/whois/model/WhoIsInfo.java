package ru.whois.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class WhoIsInfo {
    ContactInfo contactInfo;
    List<HostInfo> hostInfoList;
    List<StatusInfo> statusInfoList;
}
