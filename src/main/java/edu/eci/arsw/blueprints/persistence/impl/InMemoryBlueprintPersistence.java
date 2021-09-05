/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author hcadavid
 */

@Component
@Qualifier("InMemory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints = new HashMap<>();

    private void loadStubData(){
        //load stub data
        for ( int x =1;x<=4; x++ ){
            Point[] pts = new Point[]{new Point(140*x, 140*x),new Point(140*x, 140*x),new Point(115*x, 115*x)};
            int authorNum = ( x%2 == 0) ? 1:2;
            Blueprint bp = new Blueprint("_authorname_"+authorNum, "_bpname_ "+x,pts);
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
        Blueprint bp = new Blueprint("juan", "_bpname_ ",new Point[]{new Point(140, 14),new Point(14, 14),new Point(11, 15)});
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);

    }

    public InMemoryBlueprintPersistence() {
        loadStubData();
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        // Se lanza una excepcion en caso que el author o bprintname no exista
        Optional<Blueprint> optionalBlueprint = Optional.ofNullable(blueprints.get(new Tuple<>(author, bprintname)));
        optionalBlueprint.orElseThrow( () -> new BlueprintNotFoundException("No encontrado") );

        return optionalBlueprint.get();
    }

    @Override
    public Set<Blueprint> getBlueprintByAuthor(String author) throws BlueprintPersistenceException {
        Set<Blueprint> r = new HashSet<>();

        for (Tuple<String, String> k : blueprints.keySet()){
            if (k.o1.equals(author)){
                r.add(blueprints.get(k));
            }
        }
        if( r.size() == 0 ){
            throw new BlueprintPersistenceException("No se encontro");
        }
        return r;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException {
        Set<Blueprint> r = new HashSet<>();
        for (Tuple<String, String> k : blueprints.keySet()){
            r.add(blueprints.get(k));
        }

        return r;
    }
}
