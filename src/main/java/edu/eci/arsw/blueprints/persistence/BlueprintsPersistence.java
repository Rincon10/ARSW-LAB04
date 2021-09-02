/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.impl.Tuple;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     *
     * @param author blueprint's author
     * @return the blueprints made for the author searched
     * @throws BlueprintPersistenceException if there aren't any blueprints
     */
    public Set<Blueprint> getBlueprintByAuthor (String author) throws BlueprintPersistenceException;

    /**
     *
     * @return all the blueprints created
     * @throws BlueprintPersistenceException
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException;


}
