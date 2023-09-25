package com.github.ducknowledges.datastructures.stack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Stack")
public class StackToTailTest {
  private StackToTail<Integer> stack;

  @BeforeEach
  void initBeforeEach() {
    this.stack = new StackToTail<>();
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
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
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
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
      assertThat(stack.toArray()[2]).isEqualTo(array[2]);
    }
  }

  @Nested
  @DisplayName("pop")
  class Pop {
    @Test
    @DisplayName("should pop null from empty stack")
    void shouldPopNullFromEmptyStack() {
      assertThat(stack.pop()).isNull();
      assertThat(stack.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should pop element from top of single element stack")
    void shouldPopElementFromSingleElementStack() {
      Integer[] array = new Integer[]{1};
      stack.push(array[0]);
      assertThat(stack.pop()).isEqualTo(array[0]);
      assertThat(stack.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should pop element from top of stack")
    void shouldPeekElementFromStack() {
      Integer[] array = new Integer[]{1, 2 ,3};
      stack.push(array[0]);
      stack.push(array[1]);
      stack.push(array[2]);
      assertThat(stack.pop()).isEqualTo(array[2]);
      assertThat(stack.toArray())
          .hasSize(2);
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
      assertThat(stack.toArray()[1]).isEqualTo(array[1]);
      assertThat(stack.pop()).isEqualTo(array[1]);
      assertThat(stack.pop()).isEqualTo(array[0]);
      assertThat(stack.toArray()).isEmpty();
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
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
      assertThat(stack.toArray()[2]).isEqualTo(array[2]);
    }
  }

  @Nested
  @DisplayName("peek")
  class Peek {
    @Test
    @DisplayName("should peek null from empty stack")
    void shouldPeekNullFromEmptyStack() {
      assertThat(stack.peek()).isNull();
      assertThat(stack.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should peek element from top of single element stack")
    void shouldPeekElementFromSingleElementStack() {
      Integer[] array = new Integer[]{1};
      stack.push(array[0]);
      assertThat(stack.peek()).isEqualTo(array[0]);
      assertThat(stack.toArray()).hasSize(1);
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should peek element from top of stack")
    void shouldPeekElementFromStack() {
      Integer[] array = new Integer[]{1, 2 ,3};
      stack.push(array[0]);
      stack.push(array[1]);
      stack.push(array[2]);
      assertThat(stack.peek()).isEqualTo(array[2]);
      assertThat(stack.toArray()).hasSize(3);
      assertThat(stack.toArray()[0]).isEqualTo(array[0]);
      assertThat(stack.toArray()[2]).isEqualTo(array[2]);
    }
  }
}
