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

import ru.whois.model.StatusInfo;

@Repository
public class StatusRepository {
    private static Logger logger = Logger.getLogger(DomainRepository.class.getName());

    @Autowired
    private JdbcTemplate template;

    private String findStatusInfo = "select *\n" +
            "from r_domain d\n" +
            "join r_object o on (o.id = d.oid)\n" +
            "join r_object_status os on (os.objectid = o.id)\n" +
            "where d.name = ?;";

    public List<StatusInfo> findStatusInfo(String domainName) {
        logger.log(Level.INFO, "searching by contact: ", domainName);
        try {
            List<StatusInfo> status = template.query(findStatusInfo, new Object[]{domainName},
                    new StatusRowMapper());
            logger.log(Level.INFO, "found: ", status);
            return status;
        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.INFO, "not found by Status1Info: " + domainName);

            return null;
        }
    }
}

class StatusRowMapper implements RowMapper<StatusInfo> {

    @Override
    public StatusInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new StatusInfo(
                rs.getString("created"),
                rs.getString("updated"),
                rs.getString("registrarcr"),
                rs.getString("registrar"),
                rs.getString("status"));
    }
}
