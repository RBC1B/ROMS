/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.util.List;

/**
 *
 * @author ramindursingh
 */
public interface PersonalDAO {
    void save(Personal p);
    List<Personal> getAll();
    List<Personal> getByIdLazy(Long id);
}
