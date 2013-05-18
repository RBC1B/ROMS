/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.reference;

import java.util.Map;

/**
 * Dao used to look up reference information, primarily used in forms.
 *
 * @author oliver.elder.esq
 */
public interface ReferenceDao {

    /**
     * @return ordered map of appointment values
     */
    Map<Integer, String> findAppointmentValues();

    /**
     * @return ordered map of marital status values
     */
    Map<Integer, String> findMaritalStatusValues();

    /**
     * @return ordered map of RBC status values
     */
    Map<Integer, String> findRBCStatusValues();

    /**
     * @return ordered map of interview status values
     */
    Map<Integer, String> findInterviewStatusValues();

    /**
     * @return ordered map of full time service values
     */
    Map<Integer, String> findFulltimeValues();

    /**
     * @return ordered map of (emergency contact) relationship values
     */
    Map<Integer, String> findRelationshipValues();

    /**
     * @return ordered map of hall ownership type values
     */
    Map<Integer, String> findOwnershipTypeValues();

    /**
     * @return ordered map of congregation role type values
     */
    Map<Integer, String> findCongregationRoleValues();

    /**
     * @return ordered map of assignment role type values
     */
    Map<Integer, String> findAssignmentRoleValues();

    /**
     * @return ordered map of project type values
     */
    Map<Integer, String> findProjectTypeValues();

    /**
     * @return ordered map of project type values
     */
    Map<Integer, String> findProjectStatusValues();
}
