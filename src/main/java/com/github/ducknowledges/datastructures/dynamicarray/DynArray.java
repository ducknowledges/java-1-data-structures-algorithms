package com.github.ducknowledges.datastructures.dynamicarray;

import java.lang.reflect.Array;

public class DynArray<T> {
  public T [] array;
  public int count;
  public int capacity;
  Class clazz;

  private static final int DEF_CAPACITY = 16;
  private static final int INCREASE = 2;

  public DynArray(Class clz) {
    clazz = clz;
    count = 0;
    makeArray(DEF_CAPACITY);
  }

  public void makeArray(int new_capacity) {
    this.capacity = Math.max(new_capacity, DEF_CAPACITY);
    T[] tmp = array;
    this.array = (T[]) Array.newInstance(this.clazz, capacity);
    if (tmp != null) {
      System.arraycopy(tmp, 0, array, 0, count);
    }
  }

  public T getItem(int index) {
    this.checkIndexInRange(index);
    return array[index];
  }

  private void checkIndexInRange(int index) {
    if (index >= count || index < 0) {
      String message = String.format("Index %d out of bounds for length %d", index, count);
      throw new ArrayIndexOutOfBoundsException(message);
    }
  }

  public void append(T itm) {
    if (count == capacity) {
      this.grow();
    }
    this.array[count] = itm;
    count++;
  }

  private void grow() {
    makeArray(INCREASE * capacity);
  }

}
