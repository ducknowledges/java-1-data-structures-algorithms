package com.github.ducknowledges.datastructures.set;

import com.github.ducknowledges.datastructures.associativearray.NativeDictionary;

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

}