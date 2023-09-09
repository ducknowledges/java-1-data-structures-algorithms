package com.github.ducknowledges.datastructures.linkedlist;

public class LinkedList {
  public Node head;
  public Node tail;
  private int length;

  public LinkedList() {
    head = null;
    tail = null;
    length = 0;
  }

  public void addInTail(Node item) {
    if (this.head == null)
      this.head = item;
    else
      this.tail.next = item;
    this.tail = item;
    this.length++;
  }

  public Node find(int value) {
    Node node = this.head;
    while (node != null) {
      if (node.value == value) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

}

class Node {
  public int value;
  public Node next;

  public Node(int _value) {
    value = _value;
    next = null;
  }
}
