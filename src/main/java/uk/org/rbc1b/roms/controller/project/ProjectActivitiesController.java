/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.controller.project;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectStageActivity;
import uk.org.rbc1b.roms.db.project.ProjectStageActivityTask;

/**
 * Manage the project activities.
 */
@Controller
@RequestMapping("/project-activities")
public class ProjectActivitiesController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    /**
     * Create the task.
     *
     * @param activityId containing activity
     * @param taskForm form data
     * @param uriBuilder uri builder
     * @return response entity, including the location header to identify the
     * added task
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * activity
     */
    @RequestMapping(value = "{activityId}/tasks", method = RequestMethod.POST)
    public ResponseEntity<Void> createTask(@PathVariable Integer activityId, @Valid ProjectTaskForm taskForm,
            UriComponentsBuilder uriBuilder) throws NoSuchRequestHandlingMethodException {

        ProjectStageActivity activity = projectDao.findProjectStageActivity(activityId);
        if (activity == null) {
            throw new NoSuchRequestHandlingMethodException("No project actvity with id [" + activityId + "]",
                    this.getClass());
        }

        ProjectStageActivityTask task = new ProjectStageActivityTask();
        task.setProjectStageActivity(activity);

        task.setAssignedUser(userDao.findUser(taskForm.getAssignedUserId()));
        task.setComments(taskForm.getComments());
        task.setCreatedTime(new Date());
        task.setName(taskForm.getName());

        projectDao.createTask(task);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("activityId", activityId);
        params.put("taskId", task.getProjectStageActivityTaskId());

        UriComponents uriComponents = uriBuilder.path("/project-activities/{activityId}/tasks/{taskId}")
                .buildAndExpand(params);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", uriComponents.toUriString());
        return new ResponseEntity<Void>(responseHeaders, HttpStatus.CREATED);

    }

}
