package com.github.ducknowledges.datastructures.deque;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Deque")
class DequeTest {

  private Deque<Integer> deque;

  @BeforeEach
  void initBeforeEach() {
    this.deque = new Deque<>();
  }

  @Nested
  @DisplayName("addFront")
  class AddFront {
    @Test
    @DisplayName("should add element to front of deque")
    void shouldAddElementToFront() {
      int expected = 1;
      deque.addFront(expected);
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(expected);
      assertThat(deque.size()).isEqualTo(expected);
      assertThat(dequeArray[0]).isEqualTo(expected);
    }

    @Test
    @DisplayName("should add elements to front of deque")
    void shouldAddElementsToFront() {
      Integer[] array = new Integer[]{1, 2, 3};
      int expected1 = array[2];
      int expected2 = array[0];
      for (Integer integer : array) {
        deque.addFront(integer);
      }
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(array.length);
      assertThat(deque.size()).isEqualTo(array.length);
      assertThat(dequeArray[0]).isEqualTo(expected1);
      assertThat(dequeArray[2]).isEqualTo(expected2);
    }
  }

  @Nested
  @DisplayName("addTail")
  class AddTail {
    @Test
    @DisplayName("should add element to tail of deque")
    void shouldAddElementToTail() {
      int expected = 1;
      deque.addTail(expected);
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(expected);
      assertThat(deque.size()).isEqualTo(expected);
      assertThat(dequeArray[0]).isEqualTo(expected);
    }

    @Test
    @DisplayName("should add elements to tail of deque")
    void shouldAddElementsToTail() {
      Integer[] array = new Integer[]{1, 2, 3};
      int expected1 = array[0];
      int expected2 = array[2];
      for (Integer integer : array) {
        deque.addTail(integer);
      }
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(array.length);
      assertThat(deque.size()).isEqualTo(array.length);
      assertThat(dequeArray[0]).isEqualTo(expected1);
      assertThat(dequeArray[2]).isEqualTo(expected2);
    }
  }

  @Nested
  @DisplayName("add one by one")
  class AddOneByOne {
    @Test
    @DisplayName("should add tail and add front one by one to deque")
    void shouldAddTailAndAddFront() {
      int expected1 = 1;
      int expected2 = 2;
      deque.addTail(expected1);
      deque.addFront(expected2);
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(2);
      assertThat(deque.size()).isEqualTo(2);
      assertThat(dequeArray[0]).isEqualTo(expected2);
      assertThat(dequeArray[1]).isEqualTo(expected1);
    }

    @Test
    @DisplayName("should add front and add tail one by one to deque")
    void shouldAddFrontAndAddTail() {
      int expected1 = 1;
      int expected2 = 2;
      deque.addFront(expected1);
      deque.addTail(expected2);
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(2);
      assertThat(deque.size()).isEqualTo(2);
      assertThat(dequeArray[0]).isEqualTo(expected1);
      assertThat(dequeArray[1]).isEqualTo(expected2);
    }
  }

  @Nested
  @DisplayName("removeFront")
  class RemoveFront {
    @Test
    @DisplayName("should return null if deque is empty")
    void shouldReturnNullIfDequeIsEmpty() {
      Integer actual = deque.removeFront();
      assertThat(actual).isNull();
    }

    @Test
    @DisplayName("should remove front element from single deque element")
    void shouldRemoveFrontSingleElementDeque() {
      int expected = 1;
      deque.addTail(expected);
      Integer actual = deque.removeFront();
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).isEmpty();
      assertThat(deque.size()).isZero();
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("should remove front elements from deque")
    void shouldRemoveFrontElementsDeque() {
      int expected1 = 1;
      int expected2 = 2;
      deque.addTail(expected1);
      deque.addFront(expected2);
      Integer actual1 = deque.removeFront();
      Integer actual2 = deque.removeFront();
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).isEmpty();
      assertThat(deque.size()).isZero();
      assertThat(actual1).isEqualTo(expected2);
      assertThat(actual2).isEqualTo(expected1);
    }
  }

  @Nested
  @DisplayName("removeTail")
  class RemoveTail {
    @Test
    @DisplayName("should return null if deque is empty")
    void shouldReturnNullIfDequeIsEmpty() {
      Integer actual = deque.removeTail();
      assertThat(actual).isNull();
    }

    @Test
    @DisplayName("should remove tail element from single deque element")
    void shouldRemoveTailSingleElementDeque() {
      int expected = 1;
      deque.addFront(expected);
      Integer actual = deque.removeTail();
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).isEmpty();
      assertThat(deque.size()).isZero();
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("should remove tail elements from deque")
    void shouldRemoveTailElementsDeque() {
      int expected1 = 1;
      int expected2 = 2;
      deque.addFront(expected1);
      deque.addTail(expected2);
      Integer actual1 = deque.removeTail();
      Integer actual2 = deque.removeTail();
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).isEmpty();
      assertThat(deque.size()).isZero();
      assertThat(actual1).isEqualTo(expected2);
      assertThat(actual2).isEqualTo(expected1);
    }
  }

}