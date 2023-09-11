package com.github.ducknowledges.datastructures.linkedlist;

import java.util.ArrayList;
import java.util.List;

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

  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<>();
    if (head == null) return nodes;
    this.addNodesWithValueToList(_value, nodes);
    return nodes;
  }

  private void addNodesWithValueToList(int value, List<Node> nodes) {
    Node node = this.head;
    while (node != null) {
      if (node.value == value)
        nodes.add(node);
      node = node.next;
    }
  }

  public boolean remove(int _value) {
    if (this.head == null) return false;
    Node previousPointer = null;
    Node currentPointer = this.head;
    while (currentPointer != null) {
      if (currentPointer.value == _value) {
        if (previousPointer == null) {
          this.head = currentPointer.next;
        } else {
          previousPointer.next = currentPointer.next;
        }
        if (currentPointer == tail) {
          tail = previousPointer;
        }
        this.length--;
        return true;
      } else {
        previousPointer = currentPointer;
        currentPointer = currentPointer.next;
      }
    }
    return false;
  }

  public void removeAll(int _value) {
    if (this.head == null) return;
    Node previousPointer = null;
    Node currentPointer = this.head;
    while (currentPointer != null) {
      if (currentPointer.value == _value) {
        if (previousPointer == null) {
          this.head = currentPointer.next;
        } else {
          previousPointer.next = currentPointer.next;
        }
        if (currentPointer == tail) {
          tail = previousPointer;
        }
        currentPointer = currentPointer.next;
        this.length--;
      } else {
        previousPointer = currentPointer;
        currentPointer = currentPointer.next;
      }
    }
  }

  public void clear() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  public int count() {
    return this.length;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter == null) {
      _nodeToInsert.next = this.head;
      this.head = _nodeToInsert;
      if (this.length == 0) {
        this.tail = _nodeToInsert;
      }
      this.length++;
      return;
    }
    Node nextNode = _nodeAfter.next;
    _nodeAfter.next = _nodeToInsert;
    _nodeToInsert.next = nextNode;
    if (this.tail == _nodeAfter) {
      this.tail = _nodeToInsert;
    }
    this.length++;
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
