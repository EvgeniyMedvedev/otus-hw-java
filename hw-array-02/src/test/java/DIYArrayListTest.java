/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2020 VTB Group. All rights reserved.
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import ru.otus.repository.DIYArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * DIYArrayListTest.
 *
 * @author Evgeniy_Medvedev
 */
public class DIYArrayListTest {

    List<String> diyList = new DIYArrayList<>();
    List<String> arrayList = new ArrayList<>();
    List<String> asList;

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
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
    public void addAllTest() {
        diyList.addAll(asList);
        arrayList.addAll(asList);

        assertEquals(diyList.size(), arrayList.size());
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