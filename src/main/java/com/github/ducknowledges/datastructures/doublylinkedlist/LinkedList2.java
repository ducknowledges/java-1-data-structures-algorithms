package com.github.ducknowledges.datastructures.doublylinkedlist;

import java.util.ArrayList;
import java.util.List;

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
    if (this.head == null) {
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
          if (currentPointer.next == null) {
            this.head = null;
          } else {
            this.head = currentPointer.next;
            this.head.prev = null;
          }
        } else {
          if (currentPointer.next != null) {
            currentPointer.next.prev = previousPointer;
          }
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
          if (currentPointer.next == null) {
            this.head = null;
          } else {
            this.head = currentPointer.next;
            this.head.prev = null;
          }
        } else {
          if (currentPointer.next != null) {
            currentPointer.next.prev = previousPointer;
          }
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

  public int count() {
    return length;
  }

  public void clear() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter == null) {
      this.addInHead(_nodeToInsert);
    } else {
      if (this.tail == _nodeAfter) {
        this.tail = _nodeToInsert;
      } else {
        _nodeAfter.next.prev = _nodeToInsert;
      }
      _nodeToInsert.next = _nodeAfter.next;
      _nodeAfter.next = _nodeToInsert;
      _nodeToInsert.prev = _nodeAfter;
      this.length++;
    }
  }

  public void addInHead(Node _item) {
    if (this.head == null) {
      this.head = _item;
      this.tail = _item;
    } else {
      _item.next = this.head;
      this.head.prev = _item;
      this.head = _item;
    }
    this.length++;
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
