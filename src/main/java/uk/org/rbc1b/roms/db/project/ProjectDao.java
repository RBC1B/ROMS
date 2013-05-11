/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
     * Look up the ordered map of project stages.
     *
     * @return project stag, mapped by id
     */
    @Transactional(readOnly = true)
    Map<Integer, ProjectStage> findProjectStages();
}
