package com.github.ducknowledges.datastructures.associativearray;

import java.lang.reflect.Array;

public class NativeDictionary<T> {

  public int size;
  public String [] slots;
  public T [] values;

  public NativeDictionary(int sz, Class clazz) {
    size = sz;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, this.size);
  }

  public int hashFun(String key) {
    return 0;
  }

  public boolean isKey(String key) {
    return false;
  }

  public void put(String key, T value) {
  }

  public T get(String key) {
    return null;
  }

}
