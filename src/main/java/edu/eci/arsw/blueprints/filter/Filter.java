package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;


/**
 * Interface that represents a filter
 * @author Leonardo Galeano
 * @author Iván Camilo Rincón Saavedra
 * @version 8/29/2021
 */
public interface Filter {
    public Blueprint bluePrintFilter(Blueprint blueprint);

    public Set<Blueprint> bluePrintsFilter(Set<Blueprint> blueprints);
}
