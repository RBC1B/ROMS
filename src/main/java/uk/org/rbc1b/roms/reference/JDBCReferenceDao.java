/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.reference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * JBDC implementation of reference data dao.
 *
 * @author oliver.elder.esq
 */
@Repository
public class JDBCReferenceDao implements ReferenceDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("reference.maritalStatus")
    public List<Pair<Integer, String>> findMaritalStatusValues() {
        return this.jdbcTemplate.query(
                "SELECT MaritalStatusId AS id, Description AS value FROM MaritalStatus",
                new IntegerStringPairRowMapper());
    }

    @Override
    @Cacheable("reference.rbcStatus")
    public List<Pair<Integer, String>> findRBCStatusValues() {
        return this.jdbcTemplate.query(
                "SELECT RbcStatusId AS id, Description AS value FROM RbcStatus",
                new IntegerStringPairRowMapper());
    }

    @Override
    @Cacheable("reference.interviewStatus")
    public List<Pair<Integer, String>> findInterviewStatusValues() {
        return this.jdbcTemplate.query(
                "SELECT InterviewStatusId AS id, Description AS value FROM InterviewStatus",
                new IntegerStringPairRowMapper());
    }

    @Override
    @Cacheable("reference.fulltime")
    public List<Pair<Integer, String>> findFulltimeValues() {
        return this.jdbcTemplate.query(
                "SELECT FulltimeId AS id, Description AS value FROM FulltimeStatus",
                new IntegerStringPairRowMapper());
    }

    @Override
    @Cacheable("reference.relationship")
    public List<Pair<Integer, String>> findRelationshipValues() {
        return this.jdbcTemplate.query(
                "SELECT RelationshipId AS id, Description AS value FROM Relationship",
                new IntegerStringPairRowMapper());
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class IntegerStringPairRowMapper implements RowMapper<Pair<Integer, String>> {

        @Override
        public Pair<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ImmutablePair<Integer, String>(rs.getInt("id"), rs.getString("value"));
        }
    }
}
