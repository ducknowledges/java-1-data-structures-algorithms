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
    int slot = seekSlot(key);
    if (slot >= 0) {
      slots[slot] = key;
      values[slot] = value;
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
      int firstIndex = hashFun(key);
      int currentIndex = index;
      if (!hasCollision(currentIndex, firstIndex)) {
        this.clearSlot(currentIndex);
        return true;
      }
      while (hasCollision(currentIndex, firstIndex)) {
          shiftSlotToNext(currentIndex);
          currentIndex = nextIndex(currentIndex);
          if (!hasCollision(currentIndex, firstIndex)) {
            this.clearSlot(currentIndex);
            return true;
          }
          if (currentIndex == firstIndex) {
            int x = (currentIndex - step) % size;
            this.clearSlot(x < 0 ? x + size : x);
            return true;
          }
        }
        return true;
    }
    return false;
  }

  private void shiftSlotToNext(int currentIndex) {
    slots[currentIndex] = slots[nextIndex(currentIndex)];
    values[currentIndex] = values[nextIndex(currentIndex)];
  }

  private boolean hasCollision(int currentIndex, int firstHash) {
    return hashFun(slots[nextIndex(currentIndex)]) == firstHash;
  }

  private int nextIndex(int index) {
    return (index + step) % size;
  }

  private void clearSlot(int hash) {
    slots[hash] = null;
    values[hash] = null;
  }

  public int findIndex(String key) {
    if (key == null) {
      return -1;
    }

    int index = this.hashFun(key);
    if (slots[index] == null) {
      return -1;
    }
    if (slots[index] != null && slots[index].equals(key)) {
      return index;
    }

    int temp = index;
    do {
      index = (index + step) % size;
      if (slots[index] != null && key.equals(slots[index])) {
        return index;
      }
    } while (index != temp && slots[index] != null);

    return -1;
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
