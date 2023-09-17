package com.github.ducknowledges.datastructures.dynamicarray;

import java.lang.reflect.Array;

public class DynArray<T> {
  public T [] array;
  public int count;
  public int capacity;
  Class clazz;

  private int defaultCapacity = 16;

  public DynArray(Class clz) {
    clazz = clz;
    count = 0;
    makeArray(defaultCapacity);
  }

  public void makeArray(int new_capacity) {
    this.capacity = Math.max(new_capacity, defaultCapacity);
    T[] tmp = array;
    this.array = (T[]) Array.newInstance(this.clazz, capacity);
    if (tmp != null) {
      System.arraycopy(tmp, 0, array, 0, count);
    }
  }

}
