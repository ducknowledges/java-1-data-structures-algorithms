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
    for (char ch: value.toCharArray()) {
      sum += ch;
    }
    return sum % this.size;
  }

  public int seekSlot(String value) {
    int hash = this.hashFun(value);
    String temp = slots[hash];
    if (temp == null) {
      return hash;
    } else {
      hash += step;
      if (hash > size - 1) {
        hash -= size;
      }
      while (!temp.equals(slots[hash])) {
        if(slots[hash] == null) {
          return hash;
        }
        hash += step;
        if (hash > size - 1) {
          hash -= size;
        }
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
    return -1;
  }

}
