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
    if (key == null) {
      return false;
    }
    return findIndex(key) >= 0;
  }

  public void put(String key, T value) {
    int index = findIndex(key);
    if (index >= 0) {
      slots[index] = key;
      values[index] = value;
    } else {
      index = seekSlot(key);
      if (index >= 0) {
        slots[index] = key;
        values[index] = value;
      }
    }
  }

  public T get(String key) {
    if (key == null) {
      return null;
    }
    int index = findIndex(key);
    return index >= 0 ? values[index] : null;
  }

  public boolean remove(String key) {
    if (key == null) {
      return false;
    }
    int index = findIndex(key);
    if (index >= 0) {
      this.clearSlot(index);
      return true;
    }
    return false;
  }

  private void clearSlot(int index) {
    slots[index] = null;
    values[index] = null;
  }

  private int findIndex(String key) {
    if (key == null) {
      return -1;
    }

    int index = this.hashFun(key);
    if (slots[index] != null && slots[index].equals(key)) {
      return index;
    }

    int temp = index;
    do {
      index = (index + step) % size;
      if (slots[index] != null && key.equals(slots[index])) {
        return index;
      }
    } while (index != temp);

    return -1;
  }

  private int seekSlot(String key) {
    if (key == null) {
      return -1;
    }

    int index = this.hashFun(key);
    String temp = slots[index];
    if (temp == null || key.equals(temp)) {
      return index;
    } else {
      index = (index + step) % size;
      while (!temp.equals(slots[index])) {
        if(slots[index] == null) {
          return index;
        }
        index = (index + step) % size;
      }
    }

    return -1;
  }

}
