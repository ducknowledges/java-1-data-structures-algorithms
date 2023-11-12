package com.github.ducknowledges.datastructures.set;

import com.github.ducknowledges.datastructures.associativearray.NativeDictionary;
import java.util.LinkedList;
import java.util.List;

public class PowerSet {

  private static final Object PRESENT = new Object();

  private final NativeDictionary<Object> map;
  private int size;

  public PowerSet() {
    map = new NativeDictionary<>(20000, Object.class);
  }

  public int size() {
    return size;
  }

  public void put(String value) {
    if (value == null) return;
    if (!this.map.isKey(value)) {
      map.put(value, PRESENT);
      size++;
    }
  }

  public boolean get(String value) {
    return map.isKey(value);
  }

  public boolean remove(String value) {
    boolean removed = map.remove(value);
    if (removed) {
      size--;
    }
    return removed;
  }

  public List<String> getKeys() {
    String[] slots = map.slots;
    List<String> keys = new LinkedList<>();
    for (String key: slots) {
      if (key != null) {
        keys.add(key);
      }
    }
    return keys;
  }

  public PowerSet intersection(PowerSet set2) {
    PowerSet newSet = new PowerSet();
    for (String key: this.getKeys()) {
      if (set2.get(key)) {
        newSet.put(key);
      }
    }
    return newSet;
  }

  public PowerSet union(PowerSet set2) {
    PowerSet newSet = new PowerSet();
    for (String key: this.getKeys()) {
      newSet.put(key);
    }
    for (String key: set2.getKeys()) {
      newSet.put(key);
    }
    return newSet;
  }

  public PowerSet difference(PowerSet set2) {
    PowerSet newSet = new PowerSet();
    for (String key: this.getKeys()) {
      if (!set2.get(key)) {
        newSet.put(key);
      }
    }
    return newSet;
  }

  public boolean isSubset(PowerSet set2) {
    for (String key: set2.getKeys()) {
      if (!this.get(key)) {
        return false;
      }
    }
    return true;
  }

}