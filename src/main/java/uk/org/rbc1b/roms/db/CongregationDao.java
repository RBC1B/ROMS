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
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up the congregation object instances.
 */
public interface CongregationDao {

    /**
     * Get a list of congregations.
     * @return congregations
     */
    @PreAuthorize("hasPermission('CONG','READ')")
    @Transactional(readOnly = true)
    List<Congregation> findAllCongregations();

    /**
     * Look up the congregation by the primary key.
     * @param congregationId congregation id
     * @return Congregation, or null if no matching instance
     */
    @PreAuthorize("hasPermission('CONG','READ')")
    @Transactional(readOnly = true)
    Congregation findCongregation(Integer congregationId);

    /**
     * Find the list of congregations matching the partial name.
     * @param name name
     * @return congregations
     */
    @PreAuthorize("hasPermission('CONG','READ')")
    @Transactional(readOnly = true)
    List<Congregation> findCongregations(String name);

    /**
     * Saves a congregation.
     * @param congregation to save
     */
    @PreAuthorize("hasPermission('CONG','EDIT')")
    @Transactional
    void updateCongregation(Congregation congregation);

    /**
     * Creates a new congregation.
     * @param congregation to create
     */
    @PreAuthorize("hasPermission('CONG','ADD')")
    @Transactional
    void createCongregation(Congregation congregation);

}
