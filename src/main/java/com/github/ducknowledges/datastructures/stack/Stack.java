package com.github.ducknowledges.datastructures.stack;

import java.util.Deque;
import java.util.LinkedList;

public class Stack<T> {

  private final Deque<T> storage;

  public Stack() {
    this.storage = new LinkedList<>();
  }

  public int size() {
    return storage.size();
  }

  public Object[] toArray() {
    return storage.toArray();
  }
}
