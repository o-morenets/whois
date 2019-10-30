
package ru.whois.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HostInfo {

	String oid;
	String name;
	String domain;
	String ipaddr;
	String pver;

}
