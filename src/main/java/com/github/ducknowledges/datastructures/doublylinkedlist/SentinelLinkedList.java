package com.github.ducknowledges.datastructures.doublylinkedlist;

import java.util.ArrayList;

public class SentinelLinkedList {
  public DoublyNode head;
  public DoublyNode tail;
  private int length;

  public SentinelLinkedList() {
    head = new SentinelNode();
    tail = new SentinelNode();
    head.next = tail;
    tail.prev = head;
    length = 0;
  }

  public void addInTail(DoublyNode _item) {
    DoublyNode lastNode = tail.prev;
    lastNode.next = _item;
    _item.prev = lastNode;
    _item.next = tail;
    tail.prev = _item;
    length++;
  }

  public DoublyNode find(int _value) {
    DoublyNode current = head.next;
    while (!(current instanceof SentinelNode)) {
      if (current.value == _value) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  public ArrayList<DoublyNode> findAll(int _value) {
    ArrayList<DoublyNode> nodes = new ArrayList<>();
    DoublyNode current = head.next;
    while (!(current instanceof SentinelNode)) {
      if (current.value == _value) {
        nodes.add(current);
      }
      current = current.next;
    }
    return nodes;
  }

  public boolean remove(int _value) {
    DoublyNode current = head.next;
    while (!(current instanceof SentinelNode)) {
      if (current.value == _value) {
        DoublyNode prevNode = current.prev;
        DoublyNode nextNode = current.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        length--;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public void removeAll(int _value) {
    DoublyNode current = head.next;
    while (!(current instanceof SentinelNode)) {
      if (current.value == _value) {
        DoublyNode prevNode = current.prev;
        DoublyNode nextNode = current.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        length--;
      }
      current = current.next;
    }
  }

  public int count() {
    return length;
  }

  public void clear() {
    head.next = tail;
    tail.prev = head;
    length = 0;
  }

  public void insertAfter(DoublyNode _nodeAfter, DoublyNode _nodeToInsert) {
    if (_nodeAfter == null) {
      this.addInHead(_nodeToInsert);
    } else {
      DoublyNode nextNode = _nodeAfter.next;
      _nodeAfter.next = _nodeToInsert;
      _nodeToInsert.prev = _nodeAfter;
      _nodeToInsert.next = nextNode;
      nextNode.prev = _nodeToInsert;
      length++;
    }

  }

  public void addInHead(DoublyNode _item) {
    DoublyNode firstNode = head.next;
    head.next = _item;
    _item.prev = head;
    _item.next = firstNode;
    firstNode.prev = _item;
    length++;
  }

}

class DoublyNode {
  public Integer value;
  public DoublyNode next;
  public DoublyNode prev;

  public DoublyNode(Integer value) {
    this.value = value;
    next = null;
    prev = null;
  }
}

class SentinelNode extends DoublyNode {
  public SentinelNode next;
  public SentinelNode prev;

  public SentinelNode() {
    super(null);
    next = null;
    prev = null;
  }
}
