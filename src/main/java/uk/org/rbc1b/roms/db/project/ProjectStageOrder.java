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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.UpdateAuditable;

/**
 * Store the ordering of the project stages. This is user for a linked list
 * implementation.
 */
@Audited
public class ProjectStageOrder implements UpdateAuditable, Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectStageOrderId;
    private Integer projectStageOrderTypeId;
    private Integer projectId;
    private Integer projectStageSortableId;
    private Integer previousProjectStageSortableId;
    private Integer nextProjectStageSortableId;
    private Date updateTime;
    private Integer updatedBy;

    /**
     * Sort the list of stages using the list of stage orders.
     *
     * @param stages stages to sort
     * @param stageOrders stage orders, defining the stages
     */
    public static void sortProjectStages(List<? extends ProjectStageSortable> stages, List<ProjectStageOrder> stageOrders) {

        if (stages.isEmpty() || stageOrders.isEmpty()) {
            return;
        }

        ProjectStageComparator comparator = new ProjectStageComparator(stageOrders);
        Collections.sort(stages, comparator);
    }

    /**
     * From the list of stage ids, create the list of project stage orders with
     * the previous and next stage ids defined.
     *
     * @param projectId project id
     * @param projectStageOrderTypeId project stage order type id
     * @param stageIds stage ids
     * @return list of project stage orders
     */
    public static List<ProjectStageOrder> createProjectStageOrders(Integer projectId, Integer projectStageOrderTypeId, List<Integer> stageIds) {
        List<ProjectStageOrder> projectStageOrders = new ArrayList<ProjectStageOrder>(stageIds.size());
        Integer previousProjectStageId = null;

        // looping forwards, create the orders with the previous stage id populated
        for (Integer stageId : stageIds) {

            ProjectStageOrder order = new ProjectStageOrder();
            order.setProjectId(projectId);
            order.setProjectStageOrderTypeId(projectStageOrderTypeId);
            order.setProjectStageSortableId(stageId);
            order.setPreviousProjectStageSortableId(previousProjectStageId);

            projectStageOrders.add(order);

            previousProjectStageId = stageId;
        }

        // loop backwards, populating the next stage ids
        Integer nextProjectStageId = null;
        for (ListIterator<ProjectStageOrder> i = projectStageOrders.listIterator(projectStageOrders.size()); i
                .hasPrevious();) {
            ProjectStageOrder projectStageOrder = i.previous();
            projectStageOrder.setNextProjectStageSortableId(nextProjectStageId);

            nextProjectStageId = projectStageOrder.getProjectStageSortableId();
        }

        return projectStageOrders;
    }

    public Integer getProjectStageOrderId() {
        return projectStageOrderId;
    }

    public void setProjectStageOrderId(Integer projectStageOrderId) {
        this.projectStageOrderId = projectStageOrderId;
    }

    public Integer getProjectStageOrderTypeId() {
        return projectStageOrderTypeId;
    }

    public void setProjectStageOrderTypeId(Integer projectStageOrderTypeId) {
        this.projectStageOrderTypeId = projectStageOrderTypeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectStageSortableId() {
        return projectStageSortableId;
    }

    public void setProjectStageSortableId(Integer projectStageSortableId) {
        this.projectStageSortableId = projectStageSortableId;
    }

    public Integer getPreviousProjectStageSortableId() {
        return previousProjectStageSortableId;
    }

    public void setPreviousProjectStageSortableId(Integer previousProjectStageSortableId) {
        this.previousProjectStageSortableId = previousProjectStageSortableId;
    }

    public Integer getNextProjectStageSortableId() {
        return nextProjectStageSortableId;
    }

    public void setNextProjectStageSortableId(Integer nextProjectStageSortableId) {
        this.nextProjectStageSortableId = nextProjectStageSortableId;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "ProjectStageOrder#" + projectStageOrderId + ":" + previousProjectStageSortableId + "->" + projectStageSortableId + "->"
                + nextProjectStageSortableId;
    }

    private static class ProjectStageComparator implements Comparator<ProjectStageSortable>, Serializable {

        private static final long serialVersionUID = 1L;
        private final List<Integer> projectStageIndexes;

        /**
         * Create the comparator, initialising the stage order indexes
         *
         * @param stageOrders stage orders
         */
        public ProjectStageComparator(List<ProjectStageOrder> stageOrders) {

            Map<Integer, ProjectStageOrder> sortOrderStageMap = new HashMap<Integer, ProjectStageOrder>();

            ProjectStageOrder nextOrder = null;
            for (ProjectStageOrder stageOrder : stageOrders) {
                sortOrderStageMap.put(stageOrder.getProjectStageSortableId(), stageOrder);
                if (stageOrder.getPreviousProjectStageSortableId() == null) {
                    nextOrder = stageOrder;
                }
            }

            if (nextOrder == null) {
                throw new IllegalStateException("Failed to find the first project stage order form orders:"
                        + stageOrders);
            }

            List<ProjectStageOrder> sortedOrders = new ArrayList<ProjectStageOrder>();

            while (nextOrder != null) {
                sortedOrders.add(nextOrder);
                nextOrder = sortOrderStageMap.get(nextOrder.getNextProjectStageSortableId());
            }

            projectStageIndexes = new ArrayList<Integer>();
            for (ProjectStageOrder stageOrder : sortedOrders) {
                projectStageIndexes.add(stageOrder.projectStageSortableId);
            }

        }

        @Override
        public int compare(ProjectStageSortable stage0, ProjectStageSortable stage1) {
            Integer index0 = ObjectUtils.firstNonNull(projectStageIndexes.indexOf(stage0.getProjectStageSortableId()),
                    Integer.MAX_VALUE);
            Integer index1 = ObjectUtils.firstNonNull(projectStageIndexes.indexOf(stage1.getProjectStageSortableId()),
                    Integer.MAX_VALUE);

            return index0.compareTo(index1);
        }
    }
}
