package com.github.ducknowledges.datastructures.queue;

import java.util.LinkedList;

public class Queue<T> {

  private final java.util.Deque<T> storage;

  public Queue() {
    this.storage = new LinkedList<>();
  }

  public void enqueue(T item) {
    storage.addLast(item);
  }

  public int size() {
    return storage.size();
  }

  public Object[] toArray() {
    return storage.toArray();
  }

}
