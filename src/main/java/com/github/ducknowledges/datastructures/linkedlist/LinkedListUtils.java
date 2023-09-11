package com.github.ducknowledges.datastructures.linkedlist;

public class LinkedListUtils {

  private LinkedListUtils() {
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
