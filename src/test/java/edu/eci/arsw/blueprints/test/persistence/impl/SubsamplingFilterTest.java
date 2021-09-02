package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filter.Filter;
import edu.eci.arsw.blueprints.filter.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Leonardo Galeano
 * @author Iván Camilo Rincón Saavedra
 * @version 8/29/2021
 */
public class SubsamplingFilterTest {
    public InMemoryBlueprintPersistence ibpp;
    public Filter filter;

    @Before
    public void setUp() throws Exception {
        ibpp = new InMemoryBlueprintPersistence();
        filter = new SubsamplingFilter();
    }

    @Test
    public void deberiaFiltrarUnPlanoPorSubMuestreo() {
        try {
            Point[] pts = new Point[]{new Point(0, 0),new Point(0, 0), new Point(10, 10),new Point(10, 10)};
            List<Point> pts2 = Arrays.asList(new Point[]{new Point(0, 0),new Point(10, 10)});

            Blueprint bp = new Blueprint("john", "thepaint", pts);

            ibpp.saveBlueprint(bp);
            Blueprint blueprint = ibpp.getBlueprint("john","thepaint");
            blueprint = filter.bluePrintFilter( blueprint );

            for (int i = 0; i < blueprint.getPoints().size(); i++) {
                assertTrue( blueprint.getPoints().get(i).equals(pts2.get(i)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Fallo que no deberia de activarse");
        }
    }

    @Test
    public void deberiaFiltrarVariosPlanoPorSubMuestreo() {
        try {
            Point[] pts = new Point[]{new Point(0, 0),new Point(0, 1), new Point(0, 2),new Point(0, 3),new Point(10, 101),new Point(0, 4)};
            Blueprint bp1 = new Blueprint("johnz", "thepaint", pts);
            Blueprint bp2 = new Blueprint("johnz", "thepaint2", pts);

            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);

            Set<Blueprint> blueprints = filter.bluePrintsFilter( ibpp.getBlueprintByAuthor("johnz"));
            blueprints.forEach( blueprint -> {
                assertEquals(blueprint.getPoints().size(), 3);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Fallo que no deberia de activarse");
        }
    }



}
