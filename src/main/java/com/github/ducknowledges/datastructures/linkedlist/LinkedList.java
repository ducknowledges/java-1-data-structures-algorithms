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
    return head.value == _value ? this.removeFirstNode() : this.removeNextNode(_value);
  }

  private boolean removeFirstNode() {
    this.head = this.head.next;
    this.length--;
    if (this.head == null) {
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
    if (this.head == null) return;
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

  public static LinkedList sumLists(LinkedList list1, LinkedList list2) {
    LinkedList listOfSummedNodes = new LinkedList();
    if (list1.count() == list2.count()) {
      Node currentNode1 = list1.head;
      Node currentNode2 = list2.head;
      while (currentNode1 != null) {
        int sum = currentNode1.value + currentNode2.value;
        listOfSummedNodes.addInTail(new Node(sum));
        currentNode1 = currentNode1.next;
        currentNode2 = currentNode2.next;
      }
    }
    return listOfSummedNodes;
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
