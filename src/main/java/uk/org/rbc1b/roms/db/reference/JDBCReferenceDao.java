/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.db.reference;

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
 * @author oliver.elder.esq
 */
@Repository
public class JDBCReferenceDao implements ReferenceDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("reference.appointment")
    public Map<String, String> findAppointmentValues() {
        return findReferenceCodeValues("SELECT AppointmentCode AS code, Name AS value "
                + "FROM Appointment ORDER BY AppointmentCode");
    }

    @Override
    @Cacheable("reference.maritalStatus")
    public Map<Integer, String> findMaritalStatusValues() {
        return findReferenceIdValues("SELECT MaritalStatusId AS id, Name AS value "
                + "FROM MaritalStatus ORDER BY MaritalStatusId");
    }

    @Override
    @Cacheable("reference.rbcStatus")
    public Map<String, String> findRBCStatusValues() {
        return findReferenceCodeValues("SELECT RbcStatusCode AS code, Name AS value "
                + "FROM RbcStatus ORDER BY RbcStatusCode");
    }

    @Override
    @Cacheable("reference.interviewStatus")
    public Map<String, String> findInterviewStatusValues() {
        return findReferenceCodeValues("SELECT InterviewStatusCode AS code, Name AS value "
                + "FROM InterviewStatus ORDER BY InterviewStatusCode");
    }

    @Override
    @Cacheable("reference.fulltime")
    public Map<Integer, String> findFulltimeValues() {
        return findReferenceIdValues("SELECT FulltimeId AS id, Name AS value " + "FROM Fulltime ORDER BY FulltimeId");
    }

    @Override
    @Cacheable("reference.relationship")
    public Map<Integer, String> findRelationshipValues() {
        return findReferenceIdValues("SELECT RelationshipId AS id, Name AS value "
                + "FROM Relationship ORDER BY RelationshipId");
    }

    @Override
    @Cacheable("reference.tradeNumber")
    public Map<Integer, String> findTradeNumbers() {
        return findReferenceIdValues("SELECT TradeNumberId AS id, Name AS value "
                + "FROM TradeNumber ORDER BY TradeNumberId");
    }

    @Override
    @Cacheable("reference.kingdomHallOwnershipType")
    public Map<String, String> findKingdomHallOwnershipTypeValues() {
        return findReferenceCodeValues("SELECT KingdomHallOwnershipTypeCode AS code, Name AS value "
                + "FROM KingdomHallOwnershipType ORDER BY KingdomHallOwnershipTypeCode");
    }

    @Override
    @Cacheable("reference.congregationRole")
    public Map<String, String> findCongregationRoleValues() {
        return findReferenceCodeValues("SELECT CongregationRoleCode AS code, Name AS value "
                + "FROM CongregationRole ORDER BY CongregationRoleCode");
    }

    @Override
    @Cacheable("reference.assignmentRole")
    public Map<Integer, String> findAssignmentRoleValues() {
        return findReferenceIdValues("SELECT AssignmentRoleId AS id, Name AS value "
                + "FROM AssignmentRole ORDER BY AssignmentRoleId");
    }

    @Override
    @Cacheable("reference.projectType")
    public Map<Integer, String> findProjectTypeValues() {
        return findReferenceIdValues("SELECT ProjectTypeId AS id, Name AS value "
                + "FROM ProjectType ORDER BY ProjectTypeId");

    }

    @Override
    @Cacheable("reference.projectStatus")
    public Map<Integer, String> findProjectStatusValues() {
        return findReferenceIdValues("SELECT ProjectStatusId AS id, Name AS value "
                + "FROM ProjectStatus ORDER BY ProjectStatusId");
    }

    @Override
    @Cacheable("reference.projectStageEventType")
    public Map<Integer, String> findProjectStageEventTypeValues() {
        return findReferenceIdValues("SELECT ProjectStageEventTypeId AS id, Name AS value "
                + "FROM ProjectStageEventType ORDER BY ProjectStageEventTypeId");
    }

    @Override
    @Cacheable("reference.projectStageActivityEventType")
    public Map<Integer, String> findProjectStageActivityEventTypeValues() {
        return findReferenceIdValues("SELECT ProjectStageActivityEventTypeId AS id, Name AS value "
                + "FROM ProjectStageActivityEventType ORDER BY ProjectStageActivityEventTypeId");
    }

    @Override
    @Cacheable("reference.projectStageActivityTaskEventType")
    public Map<Integer, String> findProjectStageActivityTaskEventTypeValues() {
        return findReferenceIdValues("SELECT ProjectStageActivityTaskEventTypeId AS id, Name AS value "
                + "FROM ProjectStageActivityTaskEventType ORDER BY ProjectStageActivityTaskEventTypeId");
    }

    @Override
    @Cacheable("reference.rbcRegion")
    public Map<Integer, String> findRbcRegionValues() {
        return findReferenceIdValues("SELECT RbcRegionId AS id, Name AS value " + "FROM RbcRegion ORDER BY RbcRegionId");
    }

    @Override
    @Cacheable("reference.rbcSubRegion")
    public Map<Integer, String> findRbcSubRegionValues() {
        return findReferenceIdValues("SELECT RbcSubRegionId AS id, Name AS value "
                + "FROM RbcSubRegion ORDER BY RbcSubRegionId");
    }

    private Map<Integer, String> findReferenceIdValues(String sql) {

        final Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();

        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.put(rs.getInt("id"), rs.getString("value"));
            }
        });
        return resultMap;
    }

    private Map<String, String> findReferenceCodeValues(String sql) {

        final Map<String, String> resultMap = new LinkedHashMap<String, String>();

        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.put(rs.getString("code"), rs.getString("value"));
            }
        });
        return resultMap;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
