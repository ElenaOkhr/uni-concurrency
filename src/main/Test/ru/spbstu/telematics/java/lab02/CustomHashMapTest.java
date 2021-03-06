package ru.spbstu.telematics.java.lab02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class CustomHashMapTest {


    private int capacity = 6;
    CustomHashMap<Integer, String> emptyMap = new CustomHashMap<>(capacity);
    CustomHashMap<String, Integer> fullMap = new CustomHashMap<>();
    Map<Integer, String> trueMap = new HashMap<>(capacity);

    @Before
    public void setUp() {
        fullMap.put("number1", 1);
        fullMap.put("number2", 2);
        fullMap.put("number3", 3);
        fullMap.put("number4", 4);
    }

    @Test
    public void compareTest(){
        emptyMap.put(1,"one");
        trueMap.put(1,"one");

        emptyMap.put(2,"two");
        trueMap.put(2,"two");

        emptyMap.remove(1);
        trueMap.remove(1);

        assertEquals(emptyMap.getSize(), trueMap.size());
        assertEquals(emptyMap.containsValue("one"), trueMap.containsValue("one"));

        emptyMap.put(2,"new value");
        trueMap.put(2,"new value");

        assertEquals(emptyMap.get(2), trueMap.get(2));

    }

    @Test
    public void putAndGetTest() {
        emptyMap.put(12, "twelve");
        emptyMap.put(13, "thirtheen");
        Assert.assertEquals("New value put incorrectly!","twelve", emptyMap.get(12));

        emptyMap.put(14, "wrong value");
        emptyMap.put(14, "fourteen");
        Assert.assertEquals("Value hasn't changed!","fourteen", emptyMap.get(14));
    }

    @Test(expected = RuntimeException.class)
    public void putFailureTest() {
        fullMap.put("fifth_number", 5);
    }

    @Test
    public void containsKeyTest() {
        boolean actualRight = fullMap.containsKey("number1");
        boolean actualWrong = fullMap.containsKey("nonexistent");

        Assert.assertTrue("containsKey() is incorrect!", actualRight);
        Assert.assertFalse("containsKey() is incorrect!", actualWrong);
    }

    @Test
    public void containsValueTest() {
        boolean actualRight = fullMap.containsValue(1);
        boolean actualWrong = fullMap.containsValue(100);

        Assert.assertTrue("containsValue() is incorrect!", actualRight);
        Assert.assertFalse("containsValue() is incorrect!", actualWrong);
    }

    @Test
    public void removeTest() {
        fullMap.remove("number3");
        boolean actual = fullMap.containsKey("number3");

        Assert.assertFalse("remove() is incorrect!", actual);
    }

    @Test
    public void getSizeTest() {
        int actual = fullMap.getSize();
        Assert.assertEquals("size() is incorrect!",4, actual);
    }

    @Test
    public void isEmptyTest() {
        Assert.assertTrue("isEmpy() is incorrect!", emptyMap.isEmpty());
        Assert.assertFalse("isEmpy() is incorrect!",fullMap.isEmpty());
    }

    @Test
    public void iteratorTest() {
        CustomHashMap.Entry element = null;
        for (CustomHashMap.Entry o : (Iterable<CustomHashMap.Entry>) fullMap) {
            element = o;
        }
        assert element != null;
        assertEquals(element.getValue(), fullMap.get("number1"));
    }

}