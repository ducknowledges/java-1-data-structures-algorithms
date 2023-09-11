package com.github.ducknowledges.datastructures.linkedlist2;

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

  LinkedList2 getListWith(List<Node> nodes) {
    LinkedList2 list = new LinkedList2();
    for (Node node: nodes) {
     list.addInTail(node);
    }
    return list;
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
      assertThat(singleNodeList.head).isEqualTo(node);
      assertThat(singleNodeList.head.next).isNull();
      assertThat(singleNodeList.tail).isEqualTo(node);
      assertThat(singleNodeList.tail.next).isNull();
    }

    @Test
    @DisplayName("should count nodes in list")
    void shouldCountList() {
      LinkedList2 singleNodeList = getListWith(nodes);
      assertThat(singleNodeList.count()).isEqualTo(nodes.size());
      assertThat(singleNodeList.head).isEqualTo(nodes.get(0));
      assertThat(singleNodeList.head.next).isEqualTo(nodes.get(1));
      assertThat(singleNodeList.tail).isEqualTo(nodes.get(3));
      assertThat(singleNodeList.tail.next).isNull();
    }
  }

}