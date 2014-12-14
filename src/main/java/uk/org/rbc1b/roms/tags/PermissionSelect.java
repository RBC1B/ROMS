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
package uk.org.rbc1b.roms.tags;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author ramindursingh
 */
public class PermissionSelect extends TagSupport {
    private static final long serialVersionUID = 1L;
    private final Map<Character, String> acl = new PermissionMap().getAcl();
    private String className;
    private String itemValue;
    private String selected;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            String selectOption = "<select class='" + className + "' name='" + getItemValue() + "'>";
            for (Entry<Character, String> entry : acl.entrySet()) {
                String key = entry.getKey().toString();
                String value = entry.getValue();
                if (getSelected().equalsIgnoreCase(key)) {
                    selectOption = selectOption + "<option value='" + key + "' selected>" + value + "</option>";
                } else {
                    selectOption = selectOption + "<option value='" + key + "'>" + value + "</option>";
                }
            }
            selectOption = selectOption + "</select>";
            out.print(selectOption);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * @return the itemValue
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * @param itemValue the itemValue to set
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * @return the selected
     */
    public String getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(String selected) {
        this.selected = selected;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
}
