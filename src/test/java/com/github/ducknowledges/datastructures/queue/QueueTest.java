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
  class Push {
    @Test
    @DisplayName("should enqueue single element to tail of queue")
    void shouldEnqueueSingleElementToTail() {
      Integer[] array = new Integer[]{1,};
      queue.enqueue(array[0]);
      assertThat(queue.toArray()).hasSize(1);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
    }

    @Test
    @DisplayName("should enqueue element to tail of queue")
    void shouldEnqueueElementToTail() {
      Integer[] array = new Integer[]{1, 2 ,3};
      queue.enqueue(array[0]);
      queue.enqueue(array[1]);
      queue.enqueue(array[2]);
      assertThat(queue.toArray()).hasSize(3);
      assertThat(queue.toArray()[0]).isEqualTo(array[0]);
      assertThat(queue.toArray()[2]).isEqualTo(array[2]);
    }
  }



}