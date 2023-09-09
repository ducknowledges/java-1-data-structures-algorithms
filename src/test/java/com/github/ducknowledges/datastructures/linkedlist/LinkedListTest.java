package com.github.ducknowledges.datastructures.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LinkedList")
class LinkedListTest {

  private LinkedList list;
  private LinkedList emptyList;
  private LinkedList singleNodeList;
  private Node node;
  private List<Node> nodes;

  @BeforeEach
  void init() {
    emptyList = new LinkedList();

    node = new Node(1);
    singleNodeList = new LinkedList();
    singleNodeList.addInTail(node);

    nodes = List.of(new Node(1), new Node(2),
        new Node(1), new Node(4), new Node(1));
    list = new LinkedList();
    list.addInTail(nodes.get(0));
    list.addInTail(nodes.get(1));
    list.addInTail(nodes.get(2));
    list.addInTail(nodes.get(3));
    list.addInTail(nodes.get(4));
  }

  @Test
  @DisplayName("should find node by value")
  void shouldFindNodeByValue() {
    assertThat(emptyList.find(1)).isNull();
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.find(2)).isNull();
    assertThat(singleNodeList.find(1)).isEqualTo(node);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    assertThat(list.find(0)).isNull();
    assertThat(list.find(2)).isEqualTo(nodes.get(1));
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(4));
  }

  @Test
  @DisplayName("should count nodes in linked list")
  void shouldCountNodes() {
    assertThat(emptyList.count()).isZero();
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.count()).isEqualTo(1);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    assertThat(list.count()).isEqualTo(5);
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(4));
  }

}