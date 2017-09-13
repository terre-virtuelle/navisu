package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.tst;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ArrayList_test {
    @Test
    public void test1() {
        ArrayList<Object> objects = new ArrayList<Object>();
        long millis;

        //region while remove
        for (int i = 0; i < 200000; i++) {
            objects.add(new Object());
        }

        millis = System.currentTimeMillis();
        while (objects.size() > 0) {
            objects.remove(0);
        }
        System.out.println(System.currentTimeMillis() - millis);
        //endregion

        //region while desc remove - the best result
        for (int i = 0; i < 200000; i++) {
            objects.add(new Object());
        }

        millis = System.currentTimeMillis();
        while (objects.size() > 0) {
            objects.remove(objects.size() - 1);
        }
        System.out.println(System.currentTimeMillis() - millis);
        //endregion

        //remove iterator
        for (int i = 0; i < 200000; i++) {
            objects.add(new Object());
        }

        millis = System.currentTimeMillis();
        for (Iterator<Object> iterator = objects.iterator(); iterator.hasNext(); ) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(System.currentTimeMillis() - millis);
        //endregion
    }

    @Test
    public void test2() throws Exception {

        //region prepare data
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        Random rdm = new Random();
        long millis;
        for (int i = 0; i < 100000; i++) {
            Integer integer = rdm.nextInt();
            ints.add(integer);
        }
        ArrayList<Integer> intsForIndex = new ArrayList<Integer>(ints);
        ArrayList<Integer> intsDescIndex = new ArrayList<Integer>(ints);
        ArrayList<Integer> intsIterator = new ArrayList<Integer>(ints);
        //endregion

        // region for index
        millis = System.currentTimeMillis();
        for (int i = 0; i < intsForIndex.size(); i++) if (intsForIndex.get(i) % 2 == 0) intsForIndex.remove(i--);
        System.out.println(System.currentTimeMillis() - millis);
        // endregion

        // region for index desc
        millis = System.currentTimeMillis();
        for (int i = intsDescIndex.size() - 1; i >= 0; i--) if (intsDescIndex.get(i) % 2 == 0) intsDescIndex.remove(i);
        System.out.println(System.currentTimeMillis() - millis);
        //endregion

        // region iterator
        millis = System.currentTimeMillis();
        for (Iterator<Integer> iterator = intsIterator.iterator(); iterator.hasNext(); )
            if (iterator.next() % 2 == 0) iterator.remove();
        System.out.println(System.currentTimeMillis() - millis);
        //endregion


    }
}
