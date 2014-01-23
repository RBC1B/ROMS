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
     *
     * @param projectId project id
     * @return project, or null if not found
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    Project findProject(Integer projectId);

    /**
     * Look up the project details by exact match name.
     *
     * @param name project name
     * @return project
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    Project findProject(String name);

    /**
     * Creates a new project, with the underlying default stages and activities.
     *
     * @param project to create
     */
    @PreAuthorize("hasPermission('PROJECT','ADD')")
    @Transactional
    void createProject(Project project);

    /**
     * Look up the stage types mapped to the project type.
     *
     * @param projectTypeId project type
     * @return list of stage types
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<ProjectTypeStageType> findProjectTypeStageTypes(Integer projectTypeId);

    /**
     * Look up the activity types mapped to a stage type.
     *
     * @param stageTypeId stage type
     * @return list of activity types
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<ProjectStageTypeActivityType> findProjectStageTypeActivityType(Integer stageTypeId);

    /**
     * Look up the list of stages associated with the project.
     * <p>This also looks up all associated activities, tasks and events
     *
     * @param projectId project id
     * @return stages
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<ProjectStage> findProjectStages(Integer projectId);

    /**
     * Look up the list of stage activities associated with the stage.
     *
     * @param projectStageId project stage id
     * @return stages
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<ProjectStageActivity> findProjectStageActivities(Integer projectStageId);

    /**
     * Look up the list of stage activity tasks associated with the stage
     * activity.
     *
     * @param projectStageActivityId project stage activity id
     * @return stages
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    List<ProjectStageActivityTask> findProjectStageActivityTasks(Integer projectStageActivityId);

    /**
     * Look up the project stage.
     * <p>This also looks up all associated activities, tasks and events
     *
     * @param projectStageId project stage id
     * @return stages
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    ProjectStage findProjectStage(Integer projectStageId);

    /**
     * Look up the project stage activity.
     * <p>This also looks up all associated tasks and events
     *
     * @param projectStageActivityId project stage activity id
     * @return stages
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    ProjectStageActivity findProjectStageActivity(Integer projectStageActivityId);

    /**
     * Look up the ordered map of project stage types.
     *
     * @return project stage, mapped by id
     */
    @Transactional(readOnly = true)
    Map<Integer, ProjectStageType> findProjectStageTypes();

    /**
     * Look up the ordered map of project stage activity types.
     *
     * @return project stage activity type, mapped by id
     */
    @Transactional(readOnly = true)
    Map<Integer, ProjectStageActivityType> findProjectStageActivityTypes();

    /**
     * Update the project stage order.
     *
     * @param projectId project id to update the order for
     * @param projectStageOrderTypeId project stage order type id
     * @param stageIds stage ids
     */
    @Transactional
    void updateProjectStageOrder(Integer projectId, Integer projectStageOrderTypeId, List<Integer> stageIds);

    /**
     * Look up the stage activity task.
     *
     * @param taskId project stage activity task id
     * @return task
     */
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @Transactional(readOnly = true)
    ProjectStageActivityTask findTask(Integer taskId);

    /**
     * Creates a new project task.
     *
     * @param task to create
     */
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    @Transactional
    void createTask(ProjectStageActivityTask task);

    /**
     * Updatrs a project task.
     *
     * @param task to update
     */
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    @Transactional
    void updateTask(ProjectStageActivityTask task);
}
