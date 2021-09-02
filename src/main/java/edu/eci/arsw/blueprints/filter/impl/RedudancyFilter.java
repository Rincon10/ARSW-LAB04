package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Leonardo Galeano
 * @author Iván Camilo Rincón Saavedra
 * @version 8/29/2021
 */
@Component
@Qualifier("Redundancy")
public class RedudancyFilter implements Filter {

    @Override
    public Blueprint bluePrintFilter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> filteredList = new ArrayList<>();
        int size = points.size();
        for( int index = 0; index < size - 1; index++ ){
            Point currentPoint = points.get(index);
            Point nextPoint = points.get(index+1);
            if( !currentPoint.equals(nextPoint) ){
                filteredList.add(currentPoint);
            }
        }
        if ( size != 0 ) {
            filteredList.add(points.get(size-1));
        }
        blueprint.setPoints(filteredList);
        return blueprint;
    }

    @Override
    public Set<Blueprint> bluePrintsFilter(Set<Blueprint> blueprints) {
        blueprints.forEach( blueprint -> {
            bluePrintFilter( blueprint );
        });
        return blueprints;
    }
}
