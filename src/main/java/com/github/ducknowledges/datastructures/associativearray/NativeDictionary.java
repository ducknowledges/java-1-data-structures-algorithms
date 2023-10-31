package com.github.ducknowledges.datastructures.associativearray;

import java.lang.reflect.Array;

public class NativeDictionary<T> {

  public int size;
  public String [] slots;
  public T [] values;

  private final int step = 3;

  public NativeDictionary(int sz, Class clazz) {
    size = sz;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, this.size);
  }

  public int hashFun(String key) {
    int sum = 0;
    if (key == null) {
      return sum;
    }
    for (char ch: key.toCharArray()) {
      sum += ch;
    }
    return sum % this.size;
  }

  public boolean isKey(String key) {
    return false;
  }

  public void put(String key, T value) {
    int slot = seekSlot(key);
    if (slot >= 0) {
      slots[slot] = key;
      values[slot] = value;
    }
  }

  public T get(String key) {
    return null;
  }

  private int seekSlot(String key) {
    if (key == null) {
      return -1;
    }

    int hash = this.hashFun(key);
    String temp = slots[hash];
    if (temp == null || key.equals(temp)) {
      return hash;
    } else {
      hash = (hash + step) % size;
      while (!temp.equals(slots[hash])) {
        if(slots[hash] == null) {
          return hash;
        }
        hash = (hash + step) % size;
      }
    }

    return -1;
  }

}
