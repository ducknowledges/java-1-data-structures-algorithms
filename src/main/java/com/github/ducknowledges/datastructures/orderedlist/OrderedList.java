package com.github.ducknowledges.datastructures.orderedlist;

public class OrderedList<T extends Comparable<T>> {

  public Node<T> head;
  public Node<T> tail;
  private boolean _ascending;
  private int size;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
    size = 0;
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

  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (this.isEmpty()) {
      addInTail(newNode);
      return;
    }

    Node<T> current = head;
    while (current != null) {
      if (compare(value, current.value) < 0) {
        if (current.prev != null) {
          current.prev.next = newNode;
          newNode.prev = current.prev;
        } else {
          head = newNode;
        }
        newNode.next = current;
        current.prev = newNode;
        size++;
        return;
      }
      if (current.next == null) {
        addInTail(newNode);
        return;
      }
      current = current.next;
    }
  }

  private void addInTail(Node _item) {
    if (this.head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
    this.size++;
  }

  public int count() {
    return size;
  }

  public boolean isEmpty() {
    return size < 1;
  }

  public Object[] toArray() {
    Object[] array = new Object[this.size];
    Node<T> node = head;
    int index = 0;
    while(node != null) {
      array[index] = node.value;
      index++;
      node = node.next;
    }
    return array;
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