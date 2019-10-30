package ru.whois.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.whois.model.*;

@Repository
public class DomainRepository {
    private static Logger logger = Logger.getLogger(DomainRepository.class.getName());

    @Autowired
    private JdbcTemplate template;

    private String findDomainInfo =
            "select d.name domainname, c.name, c.org, c.street, c.city, c.sp state, c.pc postalcode, c.cc country\n" +
            "from r_domain d\n" +
            "join r_contact c on (c.handle = d.registrant)\n" +
            "where d.name = ?;";

    public DomainInfo findDomainInfo(String domainName) {
        logger.log(Level.INFO, "searching by domain: " + domainName);
        try {
            DomainInfo domainInfo = template.queryForObject(findDomainInfo, new Object[]{domainName},
                    new DomainRowMapper());
            logger.log(Level.INFO, "found: " + domainInfo);

            return domainInfo;

        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.INFO, "not found by domain: " + domainName);

            return null;
        }
    }
}

class DomainRowMapper implements RowMapper<DomainInfo> {

    @Override
    public DomainInfo mapRow(ResultSet rs, int i) throws SQLException {
        return new DomainInfo(
                rs.getString("domainname"),
                rs.getString("name"),
                rs.getString("org"),
                rs.getString("street"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postalcode"),
                rs.getString("country")
        );
    }
}