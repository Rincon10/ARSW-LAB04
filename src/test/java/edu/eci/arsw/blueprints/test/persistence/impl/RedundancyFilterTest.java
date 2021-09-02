package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Leonardo Galeano
 * @author Iván Camilo Rincón Saavedra
 * @version 8/29/2021
 */

@Component
public class RedundancyFilterTest {
    public BlueprintsServices services;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        services =ac.getBean(BlueprintsServices.class);
    }

    @Test
    public void deberiaFiltrarUnPlanoPorRedundancia() {


        try {
            Point[] pts = new Point[]{new Point(0, 0),new Point(0, 0), new Point(10, 10),new Point(10, 10)};
            Blueprint bp = new Blueprint("john", "thepaint", pts);

            services.addNewBlueprint(bp);
            Blueprint blueprint = services.getBlueprint("john","thepaint");

            assertEquals(blueprint.getPoints().size(), 2);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Fallo que no deberia de activarse");
        }
    }

    @Test
    public void deberiaFiltrarVariosPlanoPorRedundancia() {
        try {
            Point[] pts = new Point[]{new Point(0, 0),new Point(0, 0), new Point(10, 10),new Point(10, 10),new Point(10, 101),new Point(10, 10)};
            Blueprint bp1 = new Blueprint("john", "thepaint", pts);
            Blueprint bp2 = new Blueprint("john", "thepaint2", pts);

            services.addNewBlueprint(bp1);
            services.addNewBlueprint(bp2);

            Set<Blueprint> blueprints = services.getBlueprintsByAuthor("john");
            blueprints.forEach( blueprint -> {
                assertEquals(blueprint.getPoints().size(), 4);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Fallo que no deberia de activarse");
        }
    }

}
