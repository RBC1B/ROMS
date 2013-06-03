/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class Category {

    private Integer categoryId;
    private String name;
    private String colour;
    private boolean appearOnBadge;

    /**
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * @return the appearOnBadge
     */
    public boolean isAppearOnBadge() {
        return appearOnBadge;
    }

    /**
     * @param appearOnBadge the appearOnBadge to set
     */
    public void setAppearOnBadge(boolean appearOnBadge) {
        this.appearOnBadge = appearOnBadge;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + getCategoryId() + '}';
    }
}
