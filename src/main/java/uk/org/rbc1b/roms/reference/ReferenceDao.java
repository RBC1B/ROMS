/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.reference;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Dao used to look up reference information, primarily used in forms.
 * @author oliver.elder.esq
 */
public interface ReferenceDao {

    /**
     * @return list of marital status values
     */
    List<Pair<Integer, String>> findMaritalStatusValues();

}
