package com.github.ducknowledges.datastructures.doublylinkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Doubly linked list")
class LinkedList2Test {

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
    @DisplayName("should find node by value in single node list")
    void shouldFindNodeInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.find(value)).isEqualTo(node);

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should find no node by value in single node list")
    void shouldFindNoNodeInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.find(100)).isNull();

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should find node in head by value in list")
    void shouldFindNodeInHead() {
      int value = 0;
      List<Node> nodes = getNodes(List.of(value, 1, 2, 3));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in middle by value in list")
    void shouldFindNodeInMiddle() {
      int value = 2;
      List<Node> nodes = getNodes(List.of(0, 1, value, 3));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in tail by value in list")
    void shouldFindNodeInTail() {
      int value = 3;
      List<Node> nodes = getNodes(List.of(0, 1, 2, value));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating nodes in list")
    void shouldFindNodeInRepeatingNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find no node by value in list")
    void shouldFindNoNodeInRepeatingNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      LinkedList2 list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.find(notExistValue)).isNull();

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(3), nodes.get(2));
    }

    private void assertThatFindNode(List<Node> testNodes, int nodeValue) {
      LinkedList2 list = getListWith(testNodes);
      assertThat(list.find(nodeValue)).isEqualTo(testNodes.get(nodeValue));

      assertListHead(list, testNodes.get(0), testNodes.get(1));
      assertListTail(list, testNodes.get(3), testNodes.get(2));
    }
  }

  @Nested
  @DisplayName("find all")
  class FindAll {
    @Test
    @DisplayName("should find no nodes by value in empty list")
    void shouldFindNoNodeInEmptyList() {
      LinkedList2 emptyList = getListWith(List.of());
      assertThat(emptyList.findAll(1)).isEmpty();

      assertThat(emptyList.head).isNull();
      assertThat(emptyList.tail).isNull();
    }

    @Test
    @DisplayName("should find all nodes by value in single node list")
    void shouldFindAllNodesInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      List<Node> foundNodes = singleNodeList.findAll(value);
      assertThat(foundNodes).hasSize(1);
      assertThat(foundNodes.get(0)).isEqualTo(node);

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should find no nodes by value in single node list")
    void shouldFindNoNodesInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      List<Node> foundNodes = singleNodeList.findAll(2);
      assertThat(foundNodes).isEmpty();

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should find node in head by value in list")
    void shouldFindNodeInHead() {
      int value = 0;
      List<Node> nodes = getNodes(List.of(value, 1, 2, 3));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in middle by value in list")
    void shouldFindNodeInMiddle() {
      int value = 2;
      List<Node> nodes = getNodes(List.of(0, 1, value, 3));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in tail by value in list")
    void shouldFindNodeInTail() {
      int value = 3;
      List<Node> nodes = getNodes(List.of(0, 1, 2, value));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating head nodes in list")
    void shouldFindAllNodesInRepeatingHeadNodes() {
      int value = 0;
      List<Node> nodes = getNodes(List.of(value, value, 1, 2));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating middle nodes in list")
    void shouldFindAllNodesInRepeatingMiddleNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find no node by value with repeating middle nodes in list")
    void shouldFindNoNodesInRepeatingMiddleNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      LinkedList2 list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.find(notExistValue)).isNull();

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(3), nodes.get(2));
    }

    @Test
    @DisplayName("should find node by value with repeating tail nodes in list")
    void shouldFindAllNodeInRepeatingTailNodes() {
      int value = 2;
      List<Node> nodes = getNodes(List.of(0, 1, value, value));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating nodes in list")
    void shouldFindAllNodesInRepeatingNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value, value, value, value));
      LinkedList2 list = getListWith(nodes);
      List<Node> foundNodes = list.findAll(value);
      assertThat(foundNodes).hasSize(4);
      assertThat(foundNodes.get(0)).isEqualTo(nodes.get(0));
      assertThat(foundNodes.get(1)).isEqualTo(nodes.get(1));
      assertThat(foundNodes.get(2)).isEqualTo(nodes.get(2));
      assertThat(foundNodes.get(3)).isEqualTo(nodes.get(3));

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(3), nodes.get(2));
    }

    private void assertThatFindTwoNodes(List<Node> testNodes, int nodeValue) {
      LinkedList2 list = getListWith(testNodes);
      List<Node> foundNodes = list.findAll(nodeValue);
      assertThat(foundNodes).hasSize(2);
      assertThat(foundNodes.get(0).value).isEqualTo(nodeValue);
      assertThat(foundNodes.get(1).value).isEqualTo(nodeValue);

      assertListHead(list, testNodes.get(0), testNodes.get(1));
      assertListTail(list, testNodes.get(3), testNodes.get(2));
    }

    private void assertThatFindOneNode(List<Node> testNodes, int nodeValue) {
      LinkedList2 list = getListWith(testNodes);
      List<Node> foundNodes = list.findAll(nodeValue);
      assertThat(foundNodes).hasSize(1);
      assertThat(foundNodes.get(0).value).isEqualTo(nodeValue);

      assertListHead(list, testNodes.get(0), testNodes.get(1));
      assertListTail(list, testNodes.get(3), testNodes.get(2));
    }
  }


  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should not remove node by value in empty list")
    void shouldNotRemoveNodeInEmptyList() {
      LinkedList2 emptyList = getListWith(List.of());
      assertThat(emptyList.remove(1)).isFalse();

      assertThat(emptyList.head).isNull();
      assertThat(emptyList.tail).isNull();
    }

    @Test
    @DisplayName("should remove node by value in single node list")
    void shouldRemoveNodeInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(1);
      assertThat(singleNodeList.remove(value)).isTrue();
      assertThat(singleNodeList.count()).isZero();

      assertThat(singleNodeList.head).isNull();
      assertThat(singleNodeList.tail).isNull();
    }

    @Test
    @DisplayName("should not remove node by value in single node list")
    void shouldNotRemoveNodeInSingleList() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(1);
      assertThat(singleNodeList.remove(0)).isFalse();
      assertThat(singleNodeList.count()).isEqualTo(1);

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should remove node in head by value in list")
    void shouldRemoveNodeInHead() {
      int value = 0;
      List<Node> nodes = getNodes(List.of(value, 1, 2, 3));
      LinkedList2 list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);

      assertListHead(list, nodes.get(1), nodes.get(2));
      assertListTail(list, nodes.get(3), nodes.get(2));
    }

    @Test
    @DisplayName("should remove node in middle by value in list")
    void shouldRemoveNodeInMiddle() {
      int value = 2;
      List<Node> nodes = getNodes(List.of(0, 1, value, 3));
      LinkedList2 list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(3), nodes.get(1));
    }

    @Test
    @DisplayName("should remove node in tail by value in list")
    void shouldRemoveNodeInTail() {
      int value = 3;
      List<Node> nodes = getNodes(List.of(0, 1, 2, value));
      LinkedList2 list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(2), nodes.get(1));
    }

    @Test
    @DisplayName("should remove node by value with repeating nodes in list")
    void shouldRemoveNodeInRepeatingNodes() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      LinkedList2 list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);

      assertListHead(list, nodes.get(0), nodes.get(2));
      assertListTail(list, nodes.get(3), nodes.get(2));
    }

    @Test
    @DisplayName("should not remove node by value in list")
    void shouldNotRemoveNode() {
      int value = 1;
      List<Node> nodes = getNodes(List.of(0, value, value, 2));
      LinkedList2 list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(notExistValue)).isFalse();
      assertThat(list.count()).isEqualTo(nodes.size());

      assertListHead(list, nodes.get(0), nodes.get(1));
      assertListTail(list, nodes.get(3), nodes.get(2));
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
      int value = 1;
      List<Node> nodes = getNodes(List.of(value));
      Node node = nodes.get(0);
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(value);

      assertListHead(singleNodeList, node, null);
      assertListTail(singleNodeList, node, null);
    }

    @Test
    @DisplayName("should count nodes in list")
    void shouldCountList() {
      List<Node> nodes = getNodes(List.of(0, 1, 2, 3));
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());

      assertListHead(singleNodeList, nodes.get(0), nodes.get(1));
      assertListTail(singleNodeList, nodes.get(3), nodes.get(2));
    }
  }

  @Nested
  @DisplayName("clear")
  class Clear {
    @Test
    @DisplayName("should clear empty list")
    void shouldClearEmptyList() {
      LinkedList2 emptyList = getListWith(List.of());
      assertThat(emptyList.count()).isZero();
      emptyList.clear();
      assertThat(emptyList.count()).isZero();

      assertThat(emptyList.head).isNull();
      assertThat(emptyList.tail).isNull();
    }

    @Test
    @DisplayName("should clear list")
    void shouldClearList() {
      List<Node> nodes = getNodes(List.of(1, 2, 3, 4));
      LinkedList2 list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.clear();
      assertThat(list.count()).isZero();

      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();
    }
  }

  private List<Node> getNodes(List<Integer> values) {
    return values
        .stream()
        .map(Node::new)
        .collect(Collectors.toList());
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
      assertThat(list.head.prev).isNull();
    } else {
      assertThat(list.head).isEqualTo(head);
      assertThat(list.head.next).isEqualTo(headNext);
      assertThat(list.head.prev).isNull();
    }
  }

  private void assertListTail(LinkedList2 list, Node tail, Node tailPrev) {
    if (tail == null) {
      throw new IllegalArgumentException("Expected Tail can't be null");
    }
    assertThat(list.tail).isEqualTo(tail);
    assertThat(list.tail.prev).isEqualTo(tailPrev);
    assertThat(list.tail.next).isNull();
  }
}