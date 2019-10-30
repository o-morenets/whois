package ru.whois.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.whois.model.HostInfo;

@Repository
public class HostRepository {
    private static Logger logger = Logger.getLogger(DomainRepository.class.getName());

    @Autowired
    private JdbcTemplate template;

    private String findHostInfo = "select *\n" +
            "from r_domain d\n" +
            "join r_domain_ns dns on (dns.domain = d.id)\n" +
            "join r_host h on (h.id = dns.ns)\n" +
            "join r_host_ip hip on (hip.host = h.id)\n" +
            "where d.name = ?;";

    public List<HostInfo> findHostInfo(String domainName) {
        logger.log(Level.INFO, "searching by host: ", domainName);
        try {
            List<HostInfo> hosts = template.query(findHostInfo, new Object[]{domainName},
                    new HostRowMapper());
            logger.log(Level.INFO, "found: ", hosts);

            return hosts;

        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.INFO, "not found by contactInfo: " + domainName);

            return null;
        }
    }
}

class HostRowMapper implements RowMapper<HostInfo> {

    @Override
    public HostInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new HostInfo(
                rs.getString("oid"),
                rs.getString("name"),
                rs.getString("domain"),
                rs.getString("ipaddr"),
                rs.getString("ipver"));
    }
}
