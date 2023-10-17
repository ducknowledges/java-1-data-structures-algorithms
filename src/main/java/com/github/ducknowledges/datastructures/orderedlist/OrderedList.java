package com.github.ducknowledges.datastructures.orderedlist;

import java.util.ArrayList;

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
    return v1.compareTo(v2);
  }

  private int compareStrings(String str1, String str2) {
    return str1.compareTo(str2);
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (this.isEmpty()) {
      addInTail(newNode);
      return;
    }
    Node<T> current = head;
    while (current != null && isCorrectOrder(value, current.value)) {
      current = current.next;
    }
    Node<T> prevNode = current != null ? current.prev : tail;
    if (prevNode != null) {
      prevNode.next = newNode;
      newNode.prev = prevNode;
    } else {
      head = newNode;
    }
    if (current != null) {
      newNode.next = current;
      current.prev = newNode;
    } else {
      tail = newNode;
    }
    size++;
  }

  private boolean isCorrectOrder(T v1, T v2) {
    return _ascending ? compare(v1, v2) > 0 : compare(v1, v2) < 0;
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

  private void insertBefore(Node<T> node, Node<T> newNode) {
    if (node.prev != null) {
      node.prev.next = newNode;
      newNode.prev = node.prev;
    } else {
      head = newNode;
    }

    newNode.next = node;
    node.prev = newNode;
    size++;
  }

  // Time complexity O(n)
  public Node<T> find(T val) {
    Node<T> current = head;

    while (current != null) {
      int compare = compare(val, current.value);
      if ((_ascending && compare < 0) || (!_ascending && compare > 0)) {
        break;
      }
      if (compare == 0) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  public void delete(T val) {
    Node<T> node = find(val);
    if(node == null) {
      return;
    }
    if (node.prev != null) {
      node.prev.next = node.next;
    } else {
      head = node.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    } else {
      tail = node.prev;
    }
    size--;
  }

  public void clear(boolean asc) {
    _ascending = asc;
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public int count() {
    return size;
  }

  ArrayList<Node<T>> getAll() {
    ArrayList<Node<T>> r = new ArrayList<Node<T>>();
    Node<T> node = head;
    while(node != null)
    {
      r.add(node);
      node = node.next;
    }
    return r;
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
