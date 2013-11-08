package bzh.terrevirtuelle.navisu.core.model.geom.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.location.ReadWriteLocation;
import bzh.terrevirtuelle.navisu.core.model.geom.location.impl.ReadWriteLocationImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: tibus
 * Date: 18/10/13
 * Time: 00:46
 * To change this template use File | Settings | File Templates.
 */
public class ReadWriteLocationImplTest {

    public static final double DELTA = 0.000001;

    public static final double LAT_TEST_VALUE = 12.345678910;
    public static final double LON_TEST_VALUE = 98.765432101 ;

    @Test
    public void testSetLat() throws Exception {

        ReadWriteLocation location = new ReadWriteLocationImpl();
        boolean passed = false;

        try {
            location.setLatitudeDegree(-91d);
            passed = true;
        } catch (Exception ex) {}
        System.err.println("Passed : " + passed);
        assertFalse(passed);

        try {
            location.setLatitudeDegree(91d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);
    }

    @Test
    public void testSetLon() throws Exception {

        ReadWriteLocation location = new ReadWriteLocationImpl();
        boolean passed = false;

        try {
            location.setLongitudeDegree(-181d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);

        try {
            location.setLongitudeDegree(181d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);
    }

    @Test
    public void testLatConstructor() throws Exception {

        boolean passed = false;

        try {
            new ReadWriteLocationImpl(-91d, 0d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);

        try {
            new ReadWriteLocationImpl(91d, 0d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);
    }

    @Test
    public void testLonConstructor() throws Exception {

        boolean passed = false;

        try {
            new ReadWriteLocationImpl(0d, -181d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);

        try {
            new ReadWriteLocationImpl(0d, 181d);
            passed = true;
        } catch (Exception ex) {}

        assertFalse(passed);
    }
}
