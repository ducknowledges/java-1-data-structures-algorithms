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
      Integer[] array = new Integer[]{1,};
      queue.enqueue(array[0]);
      assertThat(queue.toArray()).hasSize(1);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should enqueue element to tail of queue")
    void shouldEnqueueElementToQueueTail() {
      Integer[] array = new Integer[]{1, 2 ,3};
      queue.enqueue(array[0]);
      queue.enqueue(array[1]);
      queue.enqueue(array[2]);
      assertThat(queue.toArray()).hasSize(3);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
      assertThat(queue.toArray()[2]).isEqualTo(array[2]);
    }
  }

  @Nested
  @DisplayName("dequeue")
  class Dequeue {
    @Test
    @DisplayName("should dequeue null from empty stack")
    void shouldPopNullFromEmptyStack() {
      assertThat(queue.dequeue()).isNull();
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should dequeue element from head of single element queue")
    void shouldPopElementFromSingleElementStack() {
      Integer[] array = new Integer[]{1};
      queue.enqueue(array[0]);
      assertThat(queue.dequeue()).isEqualTo(array[0]);
      assertThat(queue.toArray()).isEmpty();
    }

    @Test
    @DisplayName("should dequeue element from head of queue")
    void shouldPeekElementFromStack() {
      Integer[] array = new Integer[]{1, 2 ,3};
      queue.enqueue(array[0]);
      queue.enqueue(array[1]);
      queue.enqueue(array[2]);
      assertThat(queue.dequeue()).isEqualTo(array[0]);
      assertThat(queue.toArray()).hasSize(2);
      assertThat(queue.toArray()[0]).isEqualTo(array[1]);
      assertThat(queue.toArray()[1]).isEqualTo(array[2]);
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
      assertThat(queue.toArray()).hasSize(1);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should get size of queue")
    void shouldGetSizeQueue() {
      Integer[] array = new Integer[]{1, 2 ,3};
      queue.enqueue(array[0]);
      queue.enqueue(array[1]);
      queue.enqueue(array[2]);
      assertThat(queue.size()).isEqualTo(3);
      assertThat(queue.toArray()).hasSize(3);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
      assertThat(queue.toArray()[2]).isEqualTo(array[2]);
    }
  }

}