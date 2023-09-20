package com.github.ducknowledges.datastructures.dynamicarray;

import java.lang.reflect.Array;

public class DynArray<T> {

  private static final int DEF_CAPACITY = 16;
  private static final int INCREASE_CAPACITY = 2;
  private static final double DECREASE_CAPACITY = 1.5;

  public T [] array;
  public int count;
  public int capacity;
  Class clazz;

  public DynArray(Class clz) {
    this.clazz = clz;
    this.count = 0;
    makeArray(DEF_CAPACITY);
  }

  public void makeArray(int new_capacity) {
    this.capacity = Math.max(new_capacity, DEF_CAPACITY);
    T[] tmp = array;
    this.array = (T[]) Array.newInstance(this.clazz, this.capacity);
    if (tmp != null) {
      System.arraycopy(tmp, 0, array, 0, this.count);
    }
  }

  public T getItem(int index) {
    this.checkIndexInRange(index);
    return array[index];
  }

  public void append(T itm) {
    if (this.count == this.capacity) {
      this.grow();
    }
    this.array[this.count] = itm;
    this.count++;
  }

  public void insert(T itm, int index) {
    if (index != this.count) {
      this.checkIndexInRange(index);
      if (this.count == this.capacity) {
        this.grow();
      }
      this.shiftElementsToRightFrom(index);
      array[index] = itm;
      this.count++;
    } else {
      this.append(itm);
    }
  }

  public void remove(int index) {
    this.checkIndexInRange(index);
    this.shiftElementsToLeftFrom(index);
    this.array[this.count - 1] = null;
    this.count--;
    if (((double) this.count / this.capacity) < 0.5 && this.capacity > 16) {
      this.shrink();
    }
  }

  private void shiftElementsToLeftFrom(int index) {
    System.arraycopy(array, index + 1, array, index, this.count - index - 1);
  }

  private void shrink() {
    makeArray((int)(this.capacity / DECREASE_CAPACITY));
  }

  private void shiftElementsToRightFrom(int index) {
    System.arraycopy(array, index, array, index + 1, this.count - index);
  }

  private void grow() {
    makeArray(INCREASE_CAPACITY * this.capacity);
  }

  private void checkIndexInRange(int index) {
    if (index >= this.count || index < 0) {
      String message = String.format("Index %d out of bounds for length %d", index, this.count);
      throw new ArrayIndexOutOfBoundsException(message);
    }
  }
}
