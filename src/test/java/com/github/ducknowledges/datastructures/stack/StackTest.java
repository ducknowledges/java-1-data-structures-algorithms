package com.github.ducknowledges.datastructures.stack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Stack")
class StackTest {

  private Stack<Integer> stack;

  @BeforeEach
  void initBeforeEach() {
    this.stack = new Stack<>();
  }

  @Nested
  @DisplayName("size")
  class Size {
    @Test
    @DisplayName("should get size of empty stack")
    void shouldSizeEmptyStack() {
      assertThat(stack.size()).isZero();
      assertThat(stack.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should get size of single element stack")
    void shouldSizeSingleStack() {
      Integer[] array = new Integer[]{1};
      stack.push(array[0]);
      assertThat(stack.size()).isEqualTo(1);
      assertThat(stack.toArray())
          .hasSize(1);
    }

    @Test
    @DisplayName("should get size of stack")
    void shouldSizeStack() {
      Integer[] array = new Integer[]{1, 2 ,3};
      stack.push(array[0]);
      stack.push(array[1]);
      stack.push(array[2]);
      assertThat(stack.size()).isEqualTo(3);
      assertThat(stack.toArray()).hasSize(3);
    }
  }

  @Nested
  @DisplayName("push")
  class Push {
    @Test
    @DisplayName("should push element to end of stack")
    void shouldPushElementToEnd() {
      Integer[] array = new Integer[]{1, 2 ,3};
      stack.push(array[0]);
      stack.push(array[1]);
      stack.push(array[2]);
      assertThat(stack.size()).isEqualTo(3);
      assertThat(stack.toArray()).hasSize(3);
    }
  }

}