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

/**
 * Provides sorting capability to Project Stages.
 *
 * @author David Maggio
 */
public interface ProjectStageSortable extends Serializable {

    /**
     * @return id of a sortable project stage type
     */
    Integer getProjectStageSortableId();

    /**
     * Identifies the type of Project Stage that can be ordered. Avoids the need
     * to provide multiple order tables for ProjectStage derivatives.
     */
    public static enum ProjectStageOrderType {

        PROJECT_STAGE(1), PROJECT_STAGE_ACTIVITY(2), PROJECT_STAGE_ACTIVITY_TASK(3);
        private final int value;

        private ProjectStageOrderType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
