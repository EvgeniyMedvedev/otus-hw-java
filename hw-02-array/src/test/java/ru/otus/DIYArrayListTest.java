package ru.otus;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import ru.otus.repository.DIYArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * ru.otus.DIYArrayListTest.
 *
 * @author Evgeniy_Medvedev
 */
public class DIYArrayListTest {

    List<String> diyList = new DIYArrayList<>();
    List<String> arrayList = new ArrayList<>();
    List<String> asList;

    @Before
    public void init() {
        for (int i = 0; i < 20; i++) {
            UUID uuid = UUID.randomUUID();
            diyList.add(uuid.toString() + i);
            arrayList.add(uuid.toString() + i);
        }
        asList = Arrays.asList(UUID.randomUUID() + "as", UUID.randomUUID() + "as");
    }

    @Test
    public void test() {
        assertEquals(arrayList.get(0), diyList.get(0));
        assertEquals(diyList.size(), arrayList.size());
    }

    @Test
    public void addTest() {
        String id = UUID.randomUUID().toString();
        List<String> strings = new DIYArrayList<>();
        strings.add(id);
        assertEquals(strings.get(0), id);
    }

    @Test
    public void setTest() {
        String id = UUID.randomUUID().toString();
        List<String> strings = new DIYArrayList<>();
        strings.set(1,id);
        assertEquals(strings.get(1), id);
    }

    @Test
    public void addAllTest() {
        diyList.addAll(asList);
        arrayList.addAll(asList);

        assertEquals(diyList.size(), arrayList.size());
    }

    @Test
    public void collectionsAddAll(){
        List<String> copy = new DIYArrayList<>();
        Collections.addAll(copy, diyList.toArray(new String[]{}));
        Logger.getGlobal().info(Arrays.toString(copy.toArray()));
        assertArrayEquals(copy.toArray(), diyList.toArray());
    }

    @Test
    public void copyTest(){
        List<String> copy = new DIYArrayList<>(diyList.toArray().length);
        Collections.copy(copy, diyList);

        Logger.getGlobal().info(Arrays.toString(copy.toArray()));
        assertArrayEquals(copy.toArray(), diyList.toArray());
    }

    @Test
    public void remove(){
        String s = asList.get(0);
        diyList.remove(s);
        arrayList.remove(s);

        assertFalse(diyList.contains(s));
        assertFalse(arrayList.contains(s));
    }

    @Test
    public void removeAll(){
        diyList.removeAll(asList);
        arrayList.removeAll(asList);

        assertEquals(diyList.size(), arrayList.size());
    }
}