/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.reference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
    @Cacheable("reference.appointment")
    public Map<Integer, String> findAppointmentValues() {
        return findReferenceValues("SELECT AppointmentId AS id, Description AS value "
                + "FROM Appointment ORDER BY AppointmentId");
    }

    @Override
    @Cacheable("reference.maritalStatus")
    public Map<Integer, String> findMaritalStatusValues() {
        return findReferenceValues("SELECT MaritalStatusId AS id, Description AS value "
                + "FROM MaritalStatus ORDER BY MaritalStatusId");
    }

    @Override
    @Cacheable("reference.rbcStatus")
    public Map<Integer, String> findRBCStatusValues() {
        return findReferenceValues("SELECT RbcStatusId AS id, Description AS value "
                + "FROM RbcStatus ORDER BY RbcStatusId");
    }

    @Override
    @Cacheable("reference.interviewStatus")
    public Map<Integer, String> findInterviewStatusValues() {
        return findReferenceValues("SELECT InterviewStatusId AS id, Description AS value "
                + "FROM InterviewStatus ORDER BY InterviewStatusId");
    }

    @Override
    @Cacheable("reference.fulltime")
    public Map<Integer, String> findFulltimeValues() {
        return findReferenceValues("SELECT FulltimeId AS id, Description AS value "
                + "FROM Fulltime ORDER BY FulltimeId");
    }

    @Override
    @Cacheable("reference.relationship")
    public Map<Integer, String> findRelationshipValues() {
        return findReferenceValues("SELECT RelationshipId AS id, Description AS value "
                + "FROM Relationship ORDER BY RelationshipId");
    }

    @Override
    @Cacheable("reference.tradeNumber")
    public Map<Integer, String> findTradeNumbers() {
        return findReferenceValues("SELECT TradeNumberId AS id, Description AS value "
                + "FROM TradeNumber ORDER BY TradeNumberId");
    }


    @Override
    @Cacheable("reference.ownershipType")
    public Map<Integer, String> findOwnershipTypeValues() {
        return findReferenceValues("SELECT OwnershipTypeId AS id, Name AS value "
                + "FROM OwnershipType ORDER BY OwnershipTypeId");
    }

    @Override
    @Cacheable("reference.congregationRole")
    public Map<Integer, String> findCongregationRoleValues() {
        return findReferenceValues("SELECT CongregationRoleId AS id, Description AS value "
                + "FROM CongregationRole ORDER BY CongregationRoleId");
    }

    @Override
    @Cacheable("reference.assignmentRole")
    public Map<Integer, String> findAssignmentRoleValues() {
        return findReferenceValues("SELECT RoleId AS id, Description AS value "
                + "FROM Role ORDER BY RoleId");
    }

    @Override
    @Cacheable("reference.projectType")
    public Map<Integer, String> findProjectTypeValues() {
        return findReferenceValues("SELECT ProjectTypeId AS id, Description AS value "
                + "FROM ProjectType ORDER BY ProjectTypeId");

    }

    @Override
    @Cacheable("reference.projectStatus")
    public Map<Integer, String> findProjectStatusValues() {
        return findReferenceValues("SELECT ProjectStatusId AS id, Description AS value "
                + "FROM ProjectStatus ORDER BY ProjectStatusId");
    }

    private Map<Integer, String> findReferenceValues(String sql) {

        final Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();

        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.put(rs.getInt("id"), rs.getString("value"));
            }
        });
        return resultMap;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
