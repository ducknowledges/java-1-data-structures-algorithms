package com.github.ducknowledges.datastructures.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LinkedListUtils")
class LinkedListUtilsTest {

  @Test
  @DisplayName("should sum nodes of two linked lists with same length")
  void shouldSumNodesOfTwoLinkedLists() {
    int a = 1;
    int b = 2;
    int c = 3;

    LinkedList list1 = new LinkedList();
    list1.addInTail(new Node(a));
    list1.addInTail(new Node(b));
    list1.addInTail(new Node(c));

    LinkedList list2 = new LinkedList();
    list2.addInTail(new Node(a));
    list2.addInTail(new Node(b));
    list2.addInTail(new Node(c));

    LinkedList actual = LinkedListUtils.sumLists(list1, list2);
    assertThat(actual.count()).isEqualTo(list1.count());
    Node node1 = actual.find(a + a);
    Node node2 = actual.find(b + b);
    Node node3 = actual.find(c + c);
    assertThat(node1).isNotNull();
    assertThat(node2).isNotNull();
    assertThat(node3).isNotNull();
    assertThat(actual.head).isEqualTo(node1);
    assertThat(actual.head.next).isEqualTo(node2);
    assertThat(actual.tail).isEqualTo(node3);
    assertThat(actual.tail.next).isNull();
  }

  @Test
  @DisplayName("should not sum nodes of two linked lists with different length")
  void shouldNotSumNodesOfTwoLinkedLists() {
    int a = 1;
    int b = 2;

    LinkedList list1 = new LinkedList();
    list1.addInTail(new Node(a));
    list1.addInTail(new Node(b));

    LinkedList list2 = new LinkedList();
    list2.addInTail(new Node(a));

    LinkedList actualEmptyList = LinkedListUtils.sumLists(list1, list2);
    assertThat(actualEmptyList.count()).isZero();
    assertThat(actualEmptyList.head).isNull();
    assertThat(actualEmptyList.tail).isNull();
  }
}