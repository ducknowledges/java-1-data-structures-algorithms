package com.github.ducknowledges.datastructures.hashtable;

public class HashTable {

  public int size;
  public int step;
  public String [] slots;

  public HashTable(int sz, int stp) {
    size = sz;
    step = stp;
    slots = new String[size];
    for(int i=0; i<size; i++) slots[i] = null;
  }

  public int hashFun(String value) {
    int sum = 0;
    if (value == null) {
      return sum;
    }
    for (char ch: value.toCharArray()) {
      sum += ch;
    }
    return sum % this.size;
  }

  public int seekSlot(String value) {
    if (value == null) {
      return -1;
    }

    int hash = this.hashFun(value);
    String temp = slots[hash];
    if (temp == null) {
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

  public int put(String value) {
    int slot = seekSlot(value);
    if (slot < 0) {
      return -1;
    }
    slots[slot] = value;
    return slot;
  }

  public int find(String value) {
    int hash = this.hashFun(value);
    if (value == null || slots[hash] == null) {
      return -1;
    }
    if (slots[hash].equals(value)) {
      return hash;
    }

    int temp = hash;
    do {
      hash = (hash + step) % size;
      if (slots[hash] != null && value.equals(slots[hash])) {
        return hash;
      }
    } while (hash != temp && slots[hash] != null);

    return -1;
  }

}
