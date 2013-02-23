/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.model;

/**
 * Generic model that provides a name, id and link to an entity - person,
 * congregation, etc.
 *
 * @author oliver
 */
public class EntityModel {

    private String uri;
    private String name;
    private Integer id;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
