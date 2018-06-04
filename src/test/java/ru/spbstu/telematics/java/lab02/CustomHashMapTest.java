package ru.spbstu.telematics.java.lab02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import ru.spbstu.telematics.java.lab02.CustomHashMap.Entry;

public class CustomHashMapTest {

  private int capacity = 6;
  CustomHashMap<Integer, String> emptyMap = new CustomHashMap<>(capacity);
  CustomHashMap<String, Integer> fullMap = new CustomHashMap<>();

  @Before
  public void setUp() throws Exception {
    fullMap.put("number1", 1);
    fullMap.put("number2", 2);
    fullMap.put("number3", 3);
    fullMap.put("number4", 4);
  }

  @Test
  public void putAndGetTest() {
    emptyMap.put(12, "twelve");
    emptyMap.put(13, "thirtheen");
    assertEquals("New value put incorrectly!","twelve", emptyMap.get(12));

    emptyMap.put(14, "wrong value");
    emptyMap.put(14, "fourteen");
    assertEquals("Value hasn't changed!","fourteen", emptyMap.get(14));
  }

  @Test(expected = RuntimeException.class)
  public void putFailureTest() {
    fullMap.put("fifth_number", 5);
  }

  @Test
  public void containsKeyTest() {
    Boolean actualRight = fullMap.containsKey("number1");
    Boolean actualWrong = fullMap.containsKey("nonexistent");

    assertTrue("containsKey() is incorrect!", actualRight);
    assertFalse("containsKey() is incorrect!", actualWrong);
  }

  @Test
  public void containsValueTest() {
    Boolean actualRight = fullMap.containsValue(1);
    Boolean actualWrong = fullMap.containsValue(100);

    assertTrue("containsValue() is incorrect!", actualRight);
    assertFalse("containsValue() is incorrect!", actualWrong);
  }

  @Test
  public void removeTest() {
    fullMap.remove("number3");
    Boolean actual = fullMap.containsKey("number3");

    assertFalse("remove() is incorrect!", actual);
  }

  @Test
  public void getSizeTest() {
    int actual = fullMap.getSize();
    assertEquals("size() is incorrect!",4, actual);
  }

  @Test
  public void isEmptyTest() {
    assertTrue("isEmpy() is incorrect!", emptyMap.isEmpty());
    assertFalse("isEmpy() is incorrect!",fullMap.isEmpty());
  }

  @Test
  public void iteratorTest() {
    Entry element = null;
    for (Iterator<CustomHashMap.Entry> iter = fullMap.iterator(); iter.hasNext(); ) {
      element = iter.next();
    }
    assertTrue(element.getValue().equals(fullMap.get("number1")));
  }
}