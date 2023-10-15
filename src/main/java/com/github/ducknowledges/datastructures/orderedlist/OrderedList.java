package com.github.ducknowledges.datastructures.orderedlist;

public class OrderedList<T> {

  public Node<T> head;
  public Node<T> tail;
  private boolean _ascending;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  public int compare(T v1, T v2) {
    int difference = ((int)v1 - (int)v2) > 0 ? -1 : 1;
    return v1.equals(v2) ? 0 : difference;
  }

}

class Node<T> {
  public T value;
  public Node<T> next;
  public Node<T> prev;

  public Node(T _value) {
    value = _value;
    next = null;
    prev = null;
  }
}
