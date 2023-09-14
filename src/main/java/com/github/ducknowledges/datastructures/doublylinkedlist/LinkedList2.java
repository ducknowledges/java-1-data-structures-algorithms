package com.github.ducknowledges.datastructures.doublylinkedlist;

public class LinkedList2 {

  public Node head;
  public Node tail;
  private int length;

  public LinkedList2() {
    head = null;
    tail = null;
    length = 0;
  }

  public void addInTail(Node _item) {
    if (head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
    this.length++;
  }

  public Node find(int _value) {
    Node node = this.head;
    while (node != null) {
      if (node.value == _value) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  public int count() {
    return length;
  }

  public void clear() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

}

class Node {

  public int value;
  public Node next;
  public Node prev;

  public Node(int _value)
  {
    value = _value;
    next = null;
    prev = null;
  }
}
