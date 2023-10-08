package com.github.ducknowledges.datastructures.deque;

import java.util.LinkedList;

public class Deque<T> {

  private final java.util.Deque<T> storage;

  public Deque() {
    this.storage = new LinkedList<>();
  }

  public void addFront(T item) {
    storage.addFirst(item);
  }

  public void addTail(T item) {
    storage.addLast(item);
  }

  public T removeFront() {
    return storage.pollFirst();
  }

  public T removeTail() {
    return storage.pollLast();
  }

  public int size() {
    return storage.size();
  }

  public Object[] toArray() {
    return storage.toArray();
  }
}
