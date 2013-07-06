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
package uk.org.rbc1b.roms.db.project;

import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * Add/edit/delete/update the core project data.
 *
 * @author oliver
 */
public interface ProjectDao {

    /**
     * Look up the list of projects.
     *
     * @return list of projects
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<Project> findProjects();

    /**
     * Look up the project details.
     * @param projectId project id
     * @return list of projects
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    Project findProject(Integer projectId);

    /**
     * Look up the ordered map of project stages.
     *
     * @return project stag, mapped by id
     */
    @Transactional(readOnly = true)
    Map<Integer, ProjectStage> findProjectStages();
}
