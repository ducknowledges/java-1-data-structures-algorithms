package com.github.ducknowledges.datastructures.queue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("QueueUtils")
class QueueUtilsTest {

  private Queue<Integer> queue;

  @BeforeEach
  void initBeforeEach() {
    this.queue = new Queue<>();
  }

  @Nested
  @DisplayName("rotate")
  class Rotate {
    @Test
    @DisplayName("should not rotate if queue is null")
    void shouldNotRotateWhenQueueIsNull() {
      queue = null;
      boolean isRotated = QueueUtils.rotate(queue, 1);
      assertThat(isRotated).isFalse();
      assertThat(queue).isNull();
    }

    @Test
    @DisplayName("should not rotate if queue is empty")
    void shouldNotRotateWhenQueueIsEmpty() {
      boolean isRotated = QueueUtils.rotate(queue, 1);
      assertThat(isRotated).isFalse();
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should not rotate if queue has single element")
    void shouldNotRotateWhenQueueHasSingleElement() {
      Integer[] array = new Integer[]{1};
      queue.enqueue(array[0]);
      boolean isRotated = QueueUtils.rotate(queue, 1);
      assertThat(isRotated).isFalse();
      assertThat(queue.toArray()).hasSize(1);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should not rotate if queue has single element")
    void shouldNotRotateWhenRotationCountIsZero() {
      Integer[] array = new Integer[]{1, 2, 3};
      queue.enqueue(array[0]);
      queue.enqueue(array[1]);
      queue.enqueue(array[2]);
      boolean isRotated = QueueUtils.rotate(queue, 0);
      assertThat(isRotated).isFalse();
      assertThat(queue.toArray()).hasSize(3);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
      assertThat(queue.toArray()[1]).isEqualTo(array[1]);
      assertThat(queue.toArray()[2]).isEqualTo(array[2]);
    }

    @Test
    @DisplayName("should rotate queue forward")
    void shouldRotateForward() {
      Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      boolean isRotated = QueueUtils.rotate(queue, 3);
      assertThat(isRotated).isTrue();
      assertThat(queue.toArray()).hasSize(7);
      assertThat(queue.toArray()[0]).isEqualTo(array[3]);
      assertThat(queue.toArray()[1]).isEqualTo(array[4]);
      assertThat(queue.toArray()[2]).isEqualTo(array[5]);
      assertThat(queue.toArray()[3]).isEqualTo(array[6]);
      assertThat(queue.toArray()[4]).isEqualTo(array[0]);
      assertThat(queue.toArray()[5]).isEqualTo(array[1]);
      assertThat(queue.toArray()[6]).isEqualTo(array[2]);
    }

    @Test
    @DisplayName("should rotate queue forward")
    void shouldRotateBackward() {
      Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      boolean isRotated = QueueUtils.rotate(queue, -3);
      assertThat(isRotated).isTrue();
      assertThat(queue.toArray()).hasSize(7);
      assertThat(queue.toArray()[0]).isEqualTo(array[4]);
      assertThat(queue.toArray()[1]).isEqualTo(array[5]);
      assertThat(queue.toArray()[2]).isEqualTo(array[6]);
      assertThat(queue.toArray()[3]).isEqualTo(array[0]);
      assertThat(queue.toArray()[4]).isEqualTo(array[1]);
      assertThat(queue.toArray()[5]).isEqualTo(array[2]);
      assertThat(queue.toArray()[6]).isEqualTo(array[3]);
    }

    @Test
    @DisplayName("should rotate queue forward when rotation count more than queue size")
    void shouldRotateForwardWhenRotationCountMoreQueueSize() {
      Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      boolean isRotated = QueueUtils.rotate(queue, 10);
      assertThat(isRotated).isTrue();
      assertThat(queue.toArray()).hasSize(7);
      assertThat(queue.toArray()[0]).isEqualTo(array[3]);
      assertThat(queue.toArray()[1]).isEqualTo(array[4]);
      assertThat(queue.toArray()[2]).isEqualTo(array[5]);
      assertThat(queue.toArray()[3]).isEqualTo(array[6]);
      assertThat(queue.toArray()[4]).isEqualTo(array[0]);
      assertThat(queue.toArray()[5]).isEqualTo(array[1]);
      assertThat(queue.toArray()[6]).isEqualTo(array[2]);
    }

    @Test
    @DisplayName("should rotate queue forward when rotation count less than queue size")
    void shouldRotateBackwardWhenRotationCountLessQueueSize() {
      Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      boolean isRotated = QueueUtils.rotate(queue, -10);
      assertThat(isRotated).isTrue();
      assertThat(queue.toArray()).hasSize(7);
      assertThat(queue.toArray()[0]).isEqualTo(array[4]);
      assertThat(queue.toArray()[1]).isEqualTo(array[5]);
      assertThat(queue.toArray()[2]).isEqualTo(array[6]);
      assertThat(queue.toArray()[3]).isEqualTo(array[0]);
      assertThat(queue.toArray()[4]).isEqualTo(array[1]);
      assertThat(queue.toArray()[5]).isEqualTo(array[2]);
      assertThat(queue.toArray()[6]).isEqualTo(array[3]);
    }
  }

}