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

import java.util.Map;

/**
 * Dao used to look up reference information, primarily used in forms.
 * @author oliver.elder.esq
 */
public interface ReferenceDao {

    /**
     * @return ordered map of appointment values
     */
    Map<String, String> findAppointmentValues();

    /**
     * @return ordered map of marital status values
     */
    Map<String, String> findMaritalStatusValues();

    /**
     * @return ordered map of RBC status values
     */
    Map<String, String> findRBCStatusValues();

    /**
     * @return ordered map of interview status values
     */
    Map<String, String> findInterviewStatusValues();

    /**
     * @return ordered map of full time service values
     */
    Map<String, String> findFulltimeValues();

    /**
     * @return ordered map of (emergency contact) relationship values
     */
    Map<Integer, String> findRelationshipValues();

    /**
     * @return ordered map of trade number values
     */
    Map<Integer, String> findTradeNumbers();

    /**
     * @return ordered map of hall ownership type values
     */
    Map<String, String> findKingdomHallOwnershipTypeValues();

    /**
     * @return ordered map of congregation role type values
     */
    Map<String, String> findCongregationRoleValues();

    /**
     * @return ordered map of assignment role type values
     */
    Map<String, String> findAssignmentRoleValues();

    /**
     * @return ordered map of project type values
     */
    Map<Integer, String> findProjectTypeValues();

    /**
     * @return ordered map of project type values
     */
    Map<Integer, String> findProjectStatusValues();

    /**
     * @return ordered map of project stage event values
     */
    Map<Integer, String> findProjectStageEventTypeValues();

    /**
     * @return ordered map of project stage activity type values
     */
    Map<Integer, String> findProjectStageActivityEventTypeValues();

    /**
     * @return ordered map of project stage activity task type values
     */
    Map<Integer, String> findProjectStageActivityTaskEventTypeValues();

    /**
     * @return ordered map of rbc region values
     */
    Map<Integer, String> findRbcRegionValues();

    /**
     * @return ordered map of rbc region values
     */
    Map<Integer, String> findRbcSubRegionValues();
}
