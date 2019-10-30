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

import ru.whois.model.*;
import ru.whois.web.Request;

@Repository
public class DomainRepository {

    private static Logger logger = Logger.getLogger(DomainRepository.class.getName());

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private StatusRepository statusRepository;

    public String findWhoIsInfo(Request request) {
        logger.log(Level.INFO, "searching by domain: " + request);
        try {
            ContactInfo contactInfo = contactsRepository.findContactInfo(request.getDomain());
            List<HostInfo> hostInfo = hostRepository.findHostInfo(request.getDomain());
            List<StatusInfo> statusInfo = statusRepository.findStatusInfo(request.getDomain());

            WhoIsInfo whoIsInfo = new WhoIsInfo(contactInfo, hostInfo, statusInfo);
            logger.log(Level.INFO, "found: " + whoIsInfo);

            return whoIsInfo.toString();

        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.INFO, "not found by domain: " + request);

            return null;
        }
    }
}

class DomainRowMapper implements RowMapper<TemporaryModel> {

    @Override
    public TemporaryModel mapRow(ResultSet rs, int i) throws SQLException {
        return new TemporaryModel(
                rs.getLong("id"),
                rs.getLong("oid"),
                rs.getString("name"),
                rs.getLong("tld"),
                rs.getString("registrant"),
                rs.getString("c_admin"),
                rs.getString("c_tech"),
                rs.getString("c_bill"),
                rs.getDate("expired")
        );
    }
}