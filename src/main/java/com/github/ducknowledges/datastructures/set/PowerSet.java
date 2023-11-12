package com.github.ducknowledges.datastructures.set;

import java.util.LinkedList;
import java.util.List;

public class PowerSet {

  private final HashSet set;
  private int size;

  public PowerSet() {
    set = new HashSet(20000);
  }

  public int size() {
    return size;
  }

  public void put(String value) {
    if (value == null) return;
    if (!this.set.isKey(value)) {
      set.put(value);
      size++;
    }
  }

  public boolean get(String value) {
    return set.isKey(value);
  }

  public boolean remove(String value) {
    boolean removed = set.remove(value);
    if (removed) {
      size--;
    }
    return removed;
  }

  public List<String> getKeys() {
    String[] slots = set.slots;
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

  private static class HashSet {

    static final int STEP = 3;

    int size;
    String [] slots;

    HashSet(int sz) {
      size = sz;
      slots = new String[size];
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
      int hash = hashFun(key);
      while (slots[hash] != null) {
        if (slots[hash].equals(key)) {
          return true;
        }
        hash = (hash + STEP) % size;
      }
      return false;
    }

    public void put(String key) {
      int slot = seekSlot(key);
      if (slot >= 0) {
        slots[slot] = key;
      }
    }


    public boolean remove(String key) {
      if (key == null) {
        return false;
      }
      int firstIndex = hashFun(key);
      int currentIndex = firstIndex;
      while (slots[currentIndex] != null) {
        if (slots[currentIndex].equals(key) && !hasCollision(currentIndex, firstIndex)) {
          this.clearSlot(currentIndex);
          return true;
        }
        if (slots[currentIndex].equals(key)) {
          while (hasCollision(currentIndex, firstIndex)) {
            shiftSlotToNext(currentIndex);
            currentIndex = nextIndex(currentIndex);
            if (!hasCollision(currentIndex, firstIndex)) {
              this.clearSlot(currentIndex);
            }
          }
          return true;
        }
        currentIndex = nextIndex(currentIndex);
      }
      return false;
    }

    private void shiftSlotToNext(int currentIndex) {
      slots[currentIndex] = slots[nextIndex(currentIndex)];
    }

    private boolean hasCollision(int currentIndex, int firstHash) {
      return hashFun(slots[nextIndex(currentIndex)]) == firstHash;
    }

    private int nextIndex(int index) {
      return (index + STEP) % size;
    }

    private void clearSlot(int hash) {
      slots[hash] = null;
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
        hash = (hash + STEP) % size;
        while (!temp.equals(slots[hash])) {
          if(slots[hash] == null) {
            return hash;
          }
          hash = (hash + STEP) % size;
        }
      }

      return -1;
    }

  }
}