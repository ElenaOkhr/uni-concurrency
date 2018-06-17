package ru.spbstu.telematics.java.lab02;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashMap<K, V> implements Iterable {

  private static final int DEFAULT_INITIAL_CAPACITY = 4;
  private static final int MAXIMUM_CAPACITY = 16;

  private Entry<K, V>[] table;
  private int size = 0;

  public CustomHashMap() {
    table = new Entry[DEFAULT_INITIAL_CAPACITY];
  }

  public CustomHashMap(int capacity) {
    capacity = (capacity < MAXIMUM_CAPACITY && capacity >= 1) ? capacity : DEFAULT_INITIAL_CAPACITY;
    table = new Entry[capacity];
  }

  public V put(K key, V value) {
    if (size == table.length) {
      throw new RuntimeException("Exceding maximum capacity");
    }

    if (key != null) {

      int index = hash(key.hashCode(), getCapacity());
      Entry<K, V> newEntry = new Entry<>(key, value);

      if (table[index] == null) {
        table[index] = newEntry;
        size++;
        return newEntry.getValue();
      }

      for (Entry<K, V> entry : table) {
        if (entry != null && entry.getKey().equals(newEntry.getKey())) {
          entry.setValue(newEntry.getValue());
          return newEntry.getValue();
        }
      }

      for (int i = 0; i < table.length; i++) {
        if (table[i] == null) {
          table[i] = newEntry;
          size++;
          return table[i].getValue();
        }
      }
    }
    return null;
  }

  public V get(K key) {
    int index = hash(key.hashCode(), getCapacity());

    if (table[index] != null) {
      for (Entry<K, V> entry : table) {
        if (entry != null && key.equals(entry.getKey())) {
          return entry.getValue();
        }
      }
    }
    return null;
  }

  public boolean containsKey(K key) {
    for (Entry<K,V> entry : table) {
      if (entry != null && key.equals(entry.getKey())) {
        return true;
      }
    }
    return false;
  }

  public boolean containsValue(V value) {
    for (Entry<K,V> entry : table) {
      if (entry != null && value.equals(entry.getValue())) {
        return true;
      }
    }
    return false;
  }

  public V remove(K key) {
    int index = hash(key.hashCode(), getCapacity());
    V removed = table[index].getValue();
    table[index] = null;
    size--;
    return removed;
  }

  public int getSize() {
    return size;
  }

  public int getCapacity() {
    return table.length;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private static int hash(int h, int capacity) {
    h ^= (h >>> 20) ^ (h >>> 12);
    h = h ^ (h >>> 7) ^ (h >>> 4);
    return h & (capacity - 1);
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append('{');
    for (int i = 0; i < table.length; i++) {
      Entry entry = table[i];
      if (entry != null) {
        if (i != 0) { string.append(", "); }
        string.append(entry);
      }
    }
    string.append('}');
    return string.toString();
  }

  class Entry<K, V> {

    private K key;
    private V value;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    K getKey() {
      return key;
    }

    V getValue() {
      return value;
    }

    void setValue(V value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return key + "=" + value;
    }
  }

  @Override
  public Iterator<CustomHashMap.Entry> iterator() {
    return new EntryIterator();
  }

  private class EntryIterator implements Iterator<CustomHashMap.Entry> {

    private int position = -1;

    @Override
    public boolean hasNext() {
      return (position < table.length-1 && table[position + 1] != null);
    }

    @Override
    public CustomHashMap.Entry next() {
      if (this.hasNext()) {
        return table[++position];
      } else {
        throw new NoSuchElementException("No such element");
      }
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Read-only");
    }
  }

}
