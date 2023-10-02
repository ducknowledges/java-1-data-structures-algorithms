package com.github.ducknowledges.datastructures.queue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Queue")
class QueueTest {

  private Queue<Integer> queue;

  @BeforeEach
  void initBeforeEach() {
    this.queue = new Queue<>();
  }

  @Nested
  @DisplayName("enqueue")
  class Enqueue {
    @Test
    @DisplayName("should enqueue single element to tail of queue")
    void shouldEnqueueSingleElementToQueueTail() {
      Integer[] array = new Integer[]{1};
      queue.enqueue(array[0]);
      Object[] queueArray = queue.toArray();
      assertThat(queueArray).hasSize(1);
      assertThat(queueArray[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should enqueue element to tail of queue")
    void shouldEnqueueElementToQueueTail() {
      Integer[] array = new Integer[]{1, 2, 3};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      Object[] queueArray = queue.toArray();
      assertThat(queueArray).hasSize(3);
      assertThat(queueArray[0]).isEqualTo(array[0]);
      assertThat(queueArray[2]).isEqualTo(array[2]);
    }
  }

  @Nested
  @DisplayName("dequeue")
  class Dequeue {
    @Test
    @DisplayName("should dequeue null from empty stack")
    void shouldDequeueNullFromEmptyStack() {
      assertThat(queue.dequeue()).isNull();
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should dequeue element from head of single element queue")
    void shouldDequeueElementFromSingleElementStack() {
      Integer[] array = new Integer[]{1};
      queue.enqueue(array[0]);
      assertThat(queue.dequeue()).isEqualTo(array[0]);
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should dequeue element from head of queue")
    void shouldDequeueElementFromStack() {
      Integer[] array = new Integer[]{1, 2, 3};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      assertThat(queue.dequeue()).isEqualTo(array[0]);
      Object[] queueArray = queue.toArray();
      assertThat(queueArray).hasSize(2);
      assertThat(queueArray[0]).isEqualTo(array[1]);
      assertThat(queueArray[1]).isEqualTo(array[2]);
      assertThat(queue.dequeue()).isEqualTo(array[1]);
      assertThat(queue.dequeue()).isEqualTo(array[2]);
      assertThat(queue.toArray()).isEmpty();
    }
  }

  @Nested
  @DisplayName("size")
  class Size {
    @Test
    @DisplayName("should get size of empty queue")
    void shouldGetSizeEmptyQueue() {
      assertThat(queue.size()).isZero();
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should get size of single element queue")
    void shouldGetSizeSingleQueue() {
      Integer[] array = new Integer[]{1};
      queue.enqueue(array[0]);
      assertThat(queue.size()).isEqualTo(1);
      Object[] queueArray = queue.toArray();
      assertThat(queueArray).hasSize(1);
      assertThat(queueArray[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should get size of queue")
    void shouldGetSizeQueue() {
      Integer[] array = new Integer[]{1, 2 ,3};
      for (Integer integer : array) {
        queue.enqueue(integer);
      }
      assertThat(queue.size()).isEqualTo(3);
      Object[] queueArray = queue.toArray();
      assertThat(queueArray).hasSize(3);
      assertThat(queueArray[0]).isEqualTo(array[0]);
      assertThat(queueArray[2]).isEqualTo(array[2]);
    }
  }

}