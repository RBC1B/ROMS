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

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Test the {@link ProjectStageOrder}
 */
public class ProjectStageOrderTest {

    @Test
    public void testOrder() {

        List<ProjectStage> stages = new ArrayList<ProjectStage>();
        stages.add(createStage(100)); // 2nd
        stages.add(createStage(101)); // 4th
        stages.add(createStage(102)); // 1st
        stages.add(createStage(103)); // 5th
        stages.add(createStage(104)); // 3rd

        List<ProjectStageOrder> stageOrders = new ArrayList<ProjectStageOrder>();
        stageOrders.add(createOrder(null, 102, 100));
        stageOrders.add(createOrder(102, 100, 104));
        stageOrders.add(createOrder(100, 104, 101));
        stageOrders.add(createOrder(104, 101, 103));
        stageOrders.add(createOrder(101, 103, null));

        ProjectStageOrder.sortProjectStages(stages, stageOrders);

        assertEquals("[ProjectStage#102, ProjectStage#100, ProjectStage#104, ProjectStage#101, ProjectStage#103]",
                stages.toString());
    }

    private ProjectStage createStage(Integer id) {
        ProjectStage stage = new ProjectStage();
        stage.setProjectStageId(id);

        return stage;
    }

    private ProjectStageOrder createOrder(Integer prev, Integer current, Integer next) {
        ProjectStageOrder order = new ProjectStageOrder();
        order.setPreviousProjectStageId(prev);
        order.setProjectStageId(current);
        order.setNextProjectStageId(next);

        return order;
    }

}
