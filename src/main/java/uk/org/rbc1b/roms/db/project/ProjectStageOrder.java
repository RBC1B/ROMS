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
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;

/**
 * Store the ordering of the project stages. This is user for a linked list implementation.
 */
public class ProjectStageOrder extends DefaultUpdateAuditable {
    private static final long serialVersionUID = 1L;
    private Integer projectStageOrderId;
    private Integer projectId;
    private Integer projectStageId;
    private Integer previousProjectStageId;
    private Integer nextProjectStageId;

    /**
     * Sort the list of stages using the list of stage orders.
     * @param stages stages to sort
     * @param stageOrders stage orders, defining the stages
     */
    public static void sortProjectStages(List<ProjectStage> stages, List<ProjectStageOrder> stageOrders) {
        ProjectStageComparator comparator = new ProjectStageComparator(stageOrders);
        Collections.sort(stages, comparator);
    }

    /**
     * From the list of stage ids, create the list of project stage orders with the previous and next stage ids defined.
     * @param projectId project id
     * @param stageIds stage ids
     * @return list of project stage orders
     */
    public static List<ProjectStageOrder> createProjectStageOrders(Integer projectId, List<Integer> stageIds) {
        List<ProjectStageOrder> projectStageOrders = new ArrayList<ProjectStageOrder>(stageIds.size());
        Integer previousProjectStageId = null;

        // looping forwards, create the orders with the previous stage id populated
        for (Integer stageId : stageIds) {

            ProjectStageOrder order = new ProjectStageOrder();
            order.setProjectId(projectId);
            order.setProjectStageId(stageId);
            order.setPreviousProjectStageId(previousProjectStageId);

            projectStageOrders.add(order);

            previousProjectStageId = stageId;
        }

        // loop backwards, populating the next stage ids
        Integer nextProjectStageId = null;
        for (ListIterator<ProjectStageOrder> i = projectStageOrders.listIterator(projectStageOrders.size()); i
                .hasPrevious();) {
            ProjectStageOrder projectStageOrder = i.previous();
            projectStageOrder.setNextProjectStageId(nextProjectStageId);

            nextProjectStageId = projectStageOrder.getProjectStageId();
        }

        return projectStageOrders;
    }

    public Integer getProjectStageOrderId() {
        return projectStageOrderId;
    }

    public void setProjectStageOrderId(Integer projectStageOrderId) {
        this.projectStageOrderId = projectStageOrderId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(Integer projectStageId) {
        this.projectStageId = projectStageId;
    }

    public Integer getPreviousProjectStageId() {
        return previousProjectStageId;
    }

    public void setPreviousProjectStageId(Integer previousProjectStageId) {
        this.previousProjectStageId = previousProjectStageId;
    }

    public Integer getNextProjectStageId() {
        return nextProjectStageId;
    }

    public void setNextProjectStageId(Integer nextProjectStageId) {
        this.nextProjectStageId = nextProjectStageId;
    }

    @Override
    public String toString() {
        return "ProjectStageOrder#" + projectStageOrderId + ":" + previousProjectStageId + "->" + projectStageId + "->"
                + nextProjectStageId;
    }

    private static class ProjectStageComparator implements Comparator<ProjectStage>, Serializable {
        private static final long serialVersionUID = 1L;
        private final List<Integer> projectStageIndexes;

        /**
         * Create the comparator, initialising the stage order indexes
         * @param stageOrders stage orders
         */
        public ProjectStageComparator(List<ProjectStageOrder> stageOrders) {

            Map<Integer, ProjectStageOrder> sortOrderStageMap = new HashMap<Integer, ProjectStageOrder>();

            ProjectStageOrder nextOrder = null;
            for (ProjectStageOrder stageOrder : stageOrders) {
                sortOrderStageMap.put(stageOrder.getProjectStageId(), stageOrder);
                if (stageOrder.getPreviousProjectStageId() == null) {
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
                nextOrder = sortOrderStageMap.get(nextOrder.getNextProjectStageId());
            }

            projectStageIndexes = new ArrayList<Integer>();
            for (ProjectStageOrder stageOrder : sortedOrders) {
                projectStageIndexes.add(stageOrder.projectStageId);
            }

        }

        @Override
        public int compare(ProjectStage stage0, ProjectStage stage1) {
            Integer index0 = ObjectUtils.firstNonNull(projectStageIndexes.indexOf(stage0.getProjectStageId()),
                    Integer.MAX_VALUE);
            Integer index1 = ObjectUtils.firstNonNull(projectStageIndexes.indexOf(stage1.getProjectStageId()),
                    Integer.MAX_VALUE);

            return index0.compareTo(index1);
        }
    }

}
