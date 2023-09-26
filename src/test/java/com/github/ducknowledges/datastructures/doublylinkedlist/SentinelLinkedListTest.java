package com.github.ducknowledges.datastructures.doublylinkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Sentinel doubly linked list")
class SentinelLinkedListTest {

  @Nested
  @DisplayName("find")
  class Find {
    @Test
    @DisplayName("should find no node by value in empty list")
    void shouldFindNoNodeInEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.find(1)).isNull();
    }

    @Test
    @DisplayName("should find node by value in single node list")
    void shouldFindNodeInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      DoublyNode node = nodes.get(0);
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.find(value)).isEqualTo(node);
    }

    @Test
    @DisplayName("should find no node by value in single node list")
    void shouldFindNoNodeInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.find(100)).isNull();
    }

    @Test
    @DisplayName("should find node in head by value in list")
    void shouldFindNodeInHead() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, 1, 2, 3));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in middle by value in list")
    void shouldFindNodeInMiddle() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, 3));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in tail by value in list")
    void shouldFindNodeInTail() {
      int value = 3;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, value));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating nodes in list")
    void shouldFindNodeInRepeatingNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      this.assertThatFindNode(nodes, value);
    }

    @Test
    @DisplayName("should find no node by value in list")
    void shouldFindNoNodeInRepeatingNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.find(notExistValue)).isNull();
    }

    private void assertThatFindNode(List<DoublyNode> testNodes, int nodeValue) {
      SentinelLinkedList list = getListWith(testNodes);
      assertThat(list.find(nodeValue)).isEqualTo(testNodes.get(nodeValue));
    }
  }

  @Nested
  @DisplayName("find all")
  class FindAll {
    @Test
    @DisplayName("should find no nodes by value in empty list")
    void shouldFindNoNodeInEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.findAll(1)).isEmpty();
    }

    @Test
    @DisplayName("should find all nodes by value in single node list")
    void shouldFindAllNodesInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      DoublyNode node = nodes.get(0);
      SentinelLinkedList singleNodeList = getListWith(nodes);
      List<DoublyNode> foundNodes = singleNodeList.findAll(value);
      assertThat(foundNodes).hasSize(1);
      assertThat(foundNodes.get(0)).isEqualTo(node);
    }

    @Test
    @DisplayName("should find no nodes by value in single node list")
    void shouldFindNoNodesInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      List<DoublyNode> foundNodes = singleNodeList.findAll(2);
      assertThat(foundNodes).isEmpty();
    }

    @Test
    @DisplayName("should find node in head by value in list")
    void shouldFindNodeInHead() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, 1, 2, 3));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in middle by value in list")
    void shouldFindNodeInMiddle() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, 3));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node in tail by value in list")
    void shouldFindNodeInTail() {
      int value = 3;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, value));
      this.assertThatFindOneNode(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating head nodes in list")
    void shouldFindAllNodesInRepeatingHeadNodes() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, value, 1, 2));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating middle nodes in list")
    void shouldFindAllNodesInRepeatingMiddleNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find no node by value with repeating middle nodes in list")
    void shouldFindNoNodesInRepeatingMiddleNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.find(notExistValue)).isNull();
    }

    @Test
    @DisplayName("should find node by value with repeating tail nodes in list")
    void shouldFindAllNodeInRepeatingTailNodes() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, value));
      this.assertThatFindTwoNodes(nodes, value);
    }

    @Test
    @DisplayName("should find node by value with repeating nodes in list")
    void shouldFindAllNodesInRepeatingNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value, value, value, value));
      SentinelLinkedList list = getListWith(nodes);
      List<DoublyNode> foundNodes = list.findAll(value);
      assertThat(foundNodes).hasSize(4);
      assertThat(foundNodes.get(0)).isEqualTo(nodes.get(0));
      assertThat(foundNodes.get(1)).isEqualTo(nodes.get(1));
      assertThat(foundNodes.get(2)).isEqualTo(nodes.get(2));
      assertThat(foundNodes.get(3)).isEqualTo(nodes.get(3));
    }

    private void assertThatFindTwoNodes(List<DoublyNode> testNodes, int nodeValue) {
      SentinelLinkedList list = getListWith(testNodes);
      List<DoublyNode> foundNodes = list.findAll(nodeValue);
      assertThat(foundNodes).hasSize(2);
      assertThat(foundNodes.get(0).value).isEqualTo(nodeValue);
      assertThat(foundNodes.get(1).value).isEqualTo(nodeValue);
    }

    private void assertThatFindOneNode(List<DoublyNode> testNodes, int nodeValue) {
      SentinelLinkedList list = getListWith(testNodes);
      List<DoublyNode> foundNodes = list.findAll(nodeValue);
      assertThat(foundNodes).hasSize(1);
      assertThat(foundNodes.get(0).value).isEqualTo(nodeValue);
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should not remove node by value in empty list")
    void shouldNotRemoveNodeInEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.remove(1)).isFalse();
    }

    @Test
    @DisplayName("should remove node by value in single node list")
    void shouldRemoveNodeInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(1);
      assertThat(singleNodeList.remove(value)).isTrue();
      assertThat(singleNodeList.count()).isZero();
    }

    @Test
    @DisplayName("should not remove node by value in single node list")
    void shouldNotRemoveNodeInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(1);
      assertThat(singleNodeList.remove(0)).isFalse();
      assertThat(singleNodeList.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("should remove node in head by value in list")
    void shouldRemoveNodeInHead() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, 1, 2, 3));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove node in middle by value in list")
    void shouldRemoveNodeInMiddle() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, 3));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove node in tail by value in list")
    void shouldRemoveNodeInTail() {
      int value = 3;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, value));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove node by value with repeating nodes in list")
    void shouldRemoveNodeInRepeatingNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(value)).isTrue();
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should not remove node by value in list")
    void shouldNotRemoveNode() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      var notExistValue = 5;
      assertThat(list.count()).isEqualTo(nodes.size());
      assertThat(list.remove(notExistValue)).isFalse();
      assertThat(list.count()).isEqualTo(nodes.size());
    }
  }

  @Nested
  @DisplayName("remove all")
  class RemoveAll {
    @Test
    @DisplayName("should not remove all nodes by value in empty list")
    void shouldRemoveNodeInEmptyList() {
      int notExistValue = 1;
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.count()).isZero();
      emptyList.removeAll(notExistValue);
      assertThat(emptyList.count()).isZero();
    }

    @Test
    @DisplayName("should remove all nodes by value in single node list")
    void shouldRemoveAllNodesInSingleNodeList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.removeAll(value);
      assertThat(singleNodeList.count()).isZero();
    }

    @Test
    @DisplayName("should not remove all nodes by value in single node list")
    void shouldNotRemoveAllNodesInSingleNodeList() {
      int value = 1;
      int notExistValue = 2;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.removeAll(notExistValue);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
    }

    @Test
    @DisplayName("should remove node in head by value")
    void shouldRemoveNodeInHead() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, 1, 2, 3));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.removeAll(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove node in middle by value")
    void shouldRemoveNodeInMiddle() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, 3));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.remove(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove node in tail by value")
    void shouldRemoveNodeInTail() {
      int value = 3;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, value));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.removeAll(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 1);
    }

    @Test
    @DisplayName("should remove all repeating nodes in head by value")
    void shouldRemoveAllRepeatingNodesInHead() {
      int value = 0;
      List<DoublyNode> nodes = getNodes(List.of(value, value, 1, 2));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.removeAll(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 2);
    }

    @Test
    @DisplayName("should remove all repeating nodes in middle by value")
    void shouldRemoveAllRepeatingNodesInMiddle() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.removeAll(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 2);
    }

    @Test
    @DisplayName("should not remove repeating nodes in middle by value")
    void shouldNotRemoveRepeatingNodesInMiddle() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(0, value, value, 2));
      SentinelLinkedList list = getListWith(nodes);
      var notExistValue = 5;
      list.removeAll(notExistValue);
      assertThat(list.count()).isEqualTo(4);
    }

    @Test
    @DisplayName("should remove all repeating nodes in tail by value")
    void shouldRemoveAllRepeatingNodesInTail() {
      int value = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, value, value));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.removeAll(value);
      assertThat(list.count()).isEqualTo(nodes.size() - 2);
    }

    @Test
    @DisplayName("should remove all repeating nodes by value")
    void shouldRemoveAllNodesInRepeatingNodes() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value, value, value, value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.removeAll(value);
      assertThat(singleNodeList.count()).isZero();
    }
  }

  @Nested
  @DisplayName("count")
  class Count {
    @Test
    @DisplayName("should count nodes in empty list")
    void shouldCountEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.count()).isZero();
    }

    @Test
    @DisplayName("should count nodes in single node list")
    void shouldCountSingleNodeList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(value);
    }

    @Test
    @DisplayName("should count nodes in list")
    void shouldCountList() {
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, 3));
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
    }
  }

  @Nested
  @DisplayName("clear")
  class Clear {
    @Test
    @DisplayName("should clear empty list")
    void shouldClearEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      assertThat(emptyList.count()).isZero();
      emptyList.clear();
      assertThat(emptyList.count()).isZero();
    }

    @Test
    @DisplayName("should clear list")
    void shouldClearList() {
      List<DoublyNode> nodes = getNodes(List.of(1, 2, 3, 4));
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.clear();
      assertThat(list.count()).isZero();
    }
  }

  @Nested
  @DisplayName("insert node")
  class InsertAfter {
    @Test
    @DisplayName("should insert in empty list")
    void shouldInsertInEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      DoublyNode node = new DoublyNode(1);
      assertThat(emptyList.count()).isZero();
      emptyList.insertAfter(null, node);
      assertThat(emptyList.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("should insert before in single node list")
    void shouldInsertBeforeInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      DoublyNode insertNode = new DoublyNode(2);
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.insertAfter(null, insertNode);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size() + 1);
    }

    @Test
    @DisplayName("should insert after in single node list")
    void shouldInsertAfterInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      DoublyNode node = nodes.get(0);
      DoublyNode insertNode = new DoublyNode(2);
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.insertAfter(node, insertNode);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size() + 1);

    }

    @Test
    @DisplayName("should insert node after head")
    void shouldInsertAfterHead() {
      int index = 0;
      List<DoublyNode> nodes = getNodes(List.of(index, 1, 2, 3));
      DoublyNode node = new DoublyNode(4);
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.insertAfter(nodes.get(index), node);
      assertThat(list.count()).isEqualTo(nodes.size() + 1);
    }

    @Test
    @DisplayName("should insert node after middle node")
    void shouldInsertAfterMiddle() {
      int index = 2;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, index, 3));
      DoublyNode node = new DoublyNode(4);
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.insertAfter(nodes.get(2), node);
      assertThat(list.count()).isEqualTo(nodes.size() + 1);
    }

    @Test
    @DisplayName("should insert node after tail")
    void shouldInsertAfterTail() {
      int index = 3;
      List<DoublyNode> nodes = getNodes(List.of(0, 1, 2, index));
      DoublyNode node = new DoublyNode(4);
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.insertAfter(nodes.get(index), node);
      assertThat(list.count()).isEqualTo(nodes.size() + 1);
    }
  }

  @Nested
  @DisplayName("add head")
  class AddHead {
    @Test
    @DisplayName("should add head in empty list")
    void shouldAddHeadInEmptyList() {
      SentinelLinkedList emptyList = getListWith(List.of());
      DoublyNode node = new DoublyNode(1);
      assertThat(emptyList.count()).isZero();
      emptyList.addInHead(node);
      assertThat(emptyList.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("should add head in single node list")
    void shouldAddHeadInSingleList() {
      int value = 1;
      List<DoublyNode> nodes = getNodes(List.of(value));
      DoublyNode insertNode = new DoublyNode(2);
      SentinelLinkedList singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      singleNodeList.addInHead(insertNode);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size() + 1);
    }

    @Test
    @DisplayName("should add head in list")
    void shouldAddHead() {
      int index = 0;
      List<DoublyNode> nodes = getNodes(List.of(index, 1, 2, 3));
      DoublyNode node = new DoublyNode(4);
      SentinelLinkedList list = getListWith(nodes);
      assertThat(list.count()).isEqualTo(nodes.size());
      list.addInHead(node);
      assertThat(list.count()).isEqualTo(nodes.size() + 1);
    }
  }

  private List<DoublyNode> getNodes(List<Integer> values) {
    return values
        .stream()
        .map(DoublyNode::new)
        .collect(Collectors.toList());
  }

  private SentinelLinkedList getListWith(List<DoublyNode> nodes) {
    SentinelLinkedList list = new SentinelLinkedList();
    for (DoublyNode node: nodes) {
      list.addInTail(node);
    }
    return list;
  }

}