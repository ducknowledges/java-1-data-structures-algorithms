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
    return 0;
  }

  public int seekSlot(String value) {
    return -1;
  }

  public int put(String value) {
    return -1;
  }

  public int find(String value) {
    return -1;
  }

}