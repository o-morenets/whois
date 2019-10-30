package ru.whois.dto;

import java.util.List;
import lombok.Data;
import ru.whois.model.Agent;
import ru.whois.model.DomainInfo;
import ru.whois.model.NameServerInfo;

@Data
public class WhoisResponse {

  private DomainInfo info;
  private Agent agent;
  private List<NameServerInfo> nameServerInfos;

  public WhoisResponse(DomainInfo info, Agent agent, List<NameServerInfo> nameServerInfos) {
    this.info = info;
    this.agent = agent;
    this.nameServerInfos = nameServerInfos;
  }	
}
