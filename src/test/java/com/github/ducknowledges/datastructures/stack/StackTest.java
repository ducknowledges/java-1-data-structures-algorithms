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
  }

}