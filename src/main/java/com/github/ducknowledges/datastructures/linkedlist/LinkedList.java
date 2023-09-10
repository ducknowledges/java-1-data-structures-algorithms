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
    if (this.length == 0) return false;
    return head.value == _value ? this.removeFirstNode() : this.removeNextNode(_value);
  }

  private boolean removeFirstNode() {
    this.head = this.head.next;
    this.length--;
    if (this.length == 0) {
      this.tail = null;
    }
    return true;
  }

  private boolean removeNextNode(int value) {
    Node currentNode = this.head;
    Node nextNode = this.head.next;
    while (nextNode != null) {
      if (nextNode.value == value) {
        currentNode.next = nextNode.next;
        if (nextNode == tail) {
          tail = currentNode;
        }
        this.length--;
        return true;
      }
      currentNode = nextNode;
      nextNode = nextNode.next;
    }
    return false;
  }

  public void removeAll(int _value) {
    if (this.length == 0) return;
    if (head.value == _value) {
      this.removeFirstNode();
    }
    if (this.length > 0) {
      this.removeRestNodes(_value);
    }
  }

  private void removeRestNodes(int value) {
    Node currentNode = this.head;
    Node nextNode = this.head.next;
    while (nextNode != null) {
      if (nextNode.value == value) {
        currentNode.next = nextNode.next;
        if (nextNode == tail) {
          tail = currentNode;
        }
        this.length--;
      }
      currentNode = nextNode;
      nextNode = nextNode.next;
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

}

class Node {
  public int value;
  public Node next;

  public Node(int _value) {
    value = _value;
    next = null;
  }
}
