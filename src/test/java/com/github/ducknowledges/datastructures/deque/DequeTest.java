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
      Integer[] array = new Integer[]{1};
      deque.addFront(array[0]);
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(1);
      assertThat(dequeArray[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should add elements to front of deque")
    void shouldAddElementsToFront() {
      Integer[] array = new Integer[]{1, 2, 3};
      for (Integer integer : array) {
        deque.addFront(integer);
      }
      Object[] dequeArray = deque.toArray();
      assertThat(dequeArray).hasSize(3);
      assertThat(dequeArray[0]).isEqualTo(array[2]);
      assertThat(dequeArray[2]).isEqualTo(array[0]);
    }
  }

  @Nested
  @DisplayName("addTail")
  class AddTail {
  }

  @Nested
  @DisplayName("removeFront")
  class RemoveFront {
  }

  @Nested
  @DisplayName("removeTail")
  class RemoveTail {
  }
}