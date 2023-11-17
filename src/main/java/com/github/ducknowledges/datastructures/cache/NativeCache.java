package com.github.ducknowledges.datastructures.cache;

import java.lang.reflect.Array;

class NativeCache<T> {

  public int size;
  public String [] slots;
  public T [] values;
  public int [] hits;

  private final int step = 3;

  public NativeCache(int sz, Class clazz) {
    size = sz;
    hits = new int[size];
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
    if (key == null) {
      return;
    }
    int index = findIndex(key);
    if (index >= 0) {
      this.setCache(index, key, value);
      return;
    }
    index = seekSlot(key);
    if (index >= 0) {
      this.setCache(index, key, value);
    } else {
      this.displace(key, value);
    }
  }

  public T get(String key) {
    if (key == null) {
      return null;
    }
    int index = findIndex(key);
    if (index >= 0) {
      hits[index] += 1;
      return values[index];
    }
    return null;
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

  public int getHit(String key) {
    int index = findIndex(key);
    if (index >= 0) {
      return hits[index];
    }
    return 0;
  }

  private void displace(String key, T value) {
    int indexOfMinHits = 0;
    for (int i = 1; i < hits.length; i++) {
      if (hits[i] < hits[indexOfMinHits]) {
        indexOfMinHits = i;
      }
    }
    remove(slots[indexOfMinHits]);
    int index = seekSlot(key);
    this.setCache(index, key, value);
  }

  private void setCache(int index, String key, T value) {
    slots[index] = key;
    values[index] = value;
    hits[index] = 0;
  }

  private void clearSlot(int index) {
    slots[index] = null;
    values[index] = null;
    hits[index] = 0;
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
