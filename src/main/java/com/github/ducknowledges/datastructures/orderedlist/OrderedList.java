package com.github.ducknowledges.datastructures.orderedlist;

import java.util.ArrayList;

public class OrderedList<T extends Comparable<T>> {

  public Node<T> head;
  public Node<T> tail;
  private boolean _ascending;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  public int compare(T v1, T v2) {
    if(v1 instanceof String) {
      return compareStrings(((String) v1).trim(), ((String) v2).trim());
    }
    return compareElements(v1, v2);
  }

  private int compareStrings(String str1, String str2) {
    return _ascending ? str1.compareTo(str2) : str2.compareTo(str1);
  }

  private int compareElements(T v1, T v2) {
    return _ascending ? v1.compareTo(v2) : v2.compareTo(v1);
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
