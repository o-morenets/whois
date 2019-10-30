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

import ru.whois.model.ContactInfo;

@Repository
public class ContactsRepository {
    private static Logger logger = Logger.getLogger(DomainRepository.class.getName());

    @Autowired
    private JdbcTemplate template;

    private String findContactInfo = "select * from r_contact where name = ?";

    public ContactInfo findContactInfo(String domainName) {
        logger.log(Level.INFO, "searching by contact:  {}", domainName);
        try {
            ContactInfo contactInfo = template.queryForObject(findContactInfo, new Object[]{domainName},
                    new ConatctRowMapper());
            logger.log(Level.INFO, "found: {}", contactInfo);

            return contactInfo;

        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.INFO, "not found by contactInfo: " + domainName);

            return null;
        }
    }
}

class ConatctRowMapper implements RowMapper<ContactInfo> {

    @Override
    public ContactInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new ContactInfo(
                rs.getString("handle"),
                rs.getString("name"),
                rs.getString("voice"),
                rs.getString("fax"),
                rs.getString("email"));
    }
}