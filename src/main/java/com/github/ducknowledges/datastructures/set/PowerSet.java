package com.github.ducknowledges.datastructures.set;

import java.util.LinkedList;
import java.util.List;

public class PowerSet {

  private final List<String> list;
  private int size;

  public PowerSet() {
    list = new LinkedList<>();
  }

  public int size() {
    return size;
  }

  public void put(String value) {
    if (value == null) return;
    if (!this.list.contains(value)) {
      list.add(value);
      size++;
    }
  }

  public boolean get(String value) {
    return list.contains(value);
  }

  public boolean remove(String value) {
    boolean removed = list.remove(value);
    if (removed) {
      size--;
    }
    return removed;
  }

  public List<String> getKeys() {
    return list;
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