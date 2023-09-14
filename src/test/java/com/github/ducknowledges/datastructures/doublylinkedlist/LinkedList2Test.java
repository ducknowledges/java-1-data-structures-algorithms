package com.github.ducknowledges.datastructures.doublylinkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Doubly linked list")
class LinkedList2Test {

  private final Node node = new Node(1);
  private final List<Node> nodes = List.of(
      new Node(0), new Node(1), new Node(2), new Node(3)
  );
  private final List<Node> repeatInBeginNodes = List.of(
      new Node(1), new Node(1), new Node(2), new Node(3)
  );
  private final List<Node> repeatInMiddleNodes = List.of(
      new Node(0), new Node(1), new Node(1), new Node(2)
  );
  private final List<Node> repeatInEndNodes = List.of(
      new Node(1), new Node(2), new Node(3), new Node(3)
  );
  private final List<Node> repeatInAllNodes = List.of(
      new Node(1), new Node(1), new Node(1), new Node(1)
  );

  @Nested
  @DisplayName("find")
  class Find {
    @Test
    @DisplayName("should find no node by value in empty list")
    void shouldFindNoNodeInEmptyList() {
      LinkedList2 emptyList = getListWith(List.of());
      assertThat(emptyList.find(1)).isNull();
      assertThat(emptyList.head).isNull();
      assertThat(emptyList.tail).isNull();
    }

    @Test
    @DisplayName("should find should find node by value in single node list")
    void shouldFindNodeInSingleList() {
      LinkedList2 singleNodeList = getListWith(List.of(node));
      assertThat(singleNodeList.find(node.value)).isEqualTo(node);
      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node);
    }

    @Test
    @DisplayName("should find no node by value in single node list")
    void shouldFindNoNodeInSingleList() {
      LinkedList2 singleNodeList = getListWith(List.of(node));
      assertThat(singleNodeList.find(100)).isNull();
      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node);
    }

    @Test
    @DisplayName("should find node in head by value in list")
    void shouldFindNodeInHead() {
      var headNodeValue = 0;
      this.assertThatFindNode(nodes, headNodeValue);
    }

    @Test
    @DisplayName("should find node in middle by value in list")
    void shouldFindNodeInMiddle() {
      var middleNodeValue = 2;
      this.assertThatFindNode(nodes, middleNodeValue);
    }

    @Test
    @DisplayName("should find node in tail by value in list")
    void shouldFindNodeInTail() {
      var tailNodeValue = 3;
      this.assertThatFindNode(nodes, tailNodeValue);
    }

    @Test
    @DisplayName("should find node by value with repeating nodes in list")
    void shouldFindNodeInRepeatingNodes() {
      LinkedList2 list = getListWith(repeatInMiddleNodes);
      var value = 1;
      assertThat(list.find(value)).isEqualTo(repeatInMiddleNodes.get(value));
      assertThat(list.head).isEqualTo(repeatInMiddleNodes.get(0));
      assertThat(list.head.next).isEqualTo(repeatInMiddleNodes.get(1));
      assertThat(list.tail).isEqualTo(repeatInMiddleNodes.get(3));
      assertThat(list.tail.next).isNull();
    }

    @Test
    @DisplayName("should find no node by value with repeating nodes in list")
    void shouldFindNoNodeInRepeatingNodes() {
      LinkedList2 list = getListWith(repeatInMiddleNodes);
      var value = 5;
      assertThat(list.find(value)).isNull();
      assertThat(list.head).isEqualTo(repeatInMiddleNodes.get(0));
      assertThat(list.head.next).isEqualTo(repeatInMiddleNodes.get(1));
      assertThat(list.tail).isEqualTo(repeatInMiddleNodes.get(3));
      assertThat(list.tail.next).isNull();
    }

    private void assertThatFindNode(List<Node> testNodes, int nodeValue) {
      LinkedList2 list = getListWith(testNodes);
      assertThat(list.find(nodeValue)).isEqualTo(testNodes.get(nodeValue));
      assertListHead(list, testNodes.get(0), testNodes.get(1));
      assertListTail(list, testNodes.get(3));
    }
  }

  @Nested
  @DisplayName("count")
  class Count {
    @Test
    @DisplayName("should count nodes in empty list")
    void shouldCountEmptyList() {
      LinkedList2 emptyList = getListWith(List.of());
      assertThat(emptyList.count()).isZero();
      assertThat(emptyList.head).isNull();
      assertThat(emptyList.tail).isNull();
    }

    @Test
    @DisplayName("should count nodes in single node list")
    void shouldCountSingleNodeList() {
      LinkedList2 singleNodeList = getListWith(List.of(node));
      assertThat(singleNodeList.count()).isEqualTo(1);
      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node);
    }

    @Test
    @DisplayName("should count nodes in list")
    void shouldCountList() {
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      assertListHead(singleNodeList, nodes.get(0), nodes.get(1));
      assertListTail(singleNodeList, nodes.get(3));
    }
  }

  private LinkedList2 getListWith(List<Node> nodes) {
    LinkedList2 list = new LinkedList2();
    for (Node node: nodes) {
      list.addInTail(node);
    }
    return list;
  }

  private void assertListHead(LinkedList2 list, Node head, Node headNext) {
    if (head == null) {
      throw new IllegalArgumentException("Expected Head can't be null");
    }
    if (headNext == null) {
      assertThat(list.head).isEqualTo(head);
      assertThat(list.head.next).isNull();
    } else {
      assertThat(list.head).isEqualTo(head);
      assertThat(list.head.next).isEqualTo(headNext);
    }
  }

  private void assertListTail(LinkedList2 list, Node tail) {
    if (tail == null) {
      throw new IllegalArgumentException("Expected Tail can't be null");
    }
    assertThat(list.tail).isEqualTo(tail);
    assertThat(list.tail.next).isNull();
  }

}