package com.github.ducknowledges.datastructures.orderedlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("OrderedList")
class OrderedListTest {

  OrderedList<Integer> list;

  @BeforeEach
  void init() {
    list = new OrderedList<>(true);
  }

  @Nested
  @DisplayName("compare")
  class Compare {
    Integer first;
    Integer second;

    @Test
    @DisplayName("should return 0 if elements are equal")
    void shouldReturnZero() {
      first = 2;
      second = 2;
      int expected = 0;
      int actual = list.compare(first, second);
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return -1 if first element is less than first")
    void shouldReturnNegative() {
      first = 1;
      second = 2;
      int expected = 1;
      int actual = list.compare(first, second);
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return +1 if first element is less than first")
    void shouldReturnPositive() {
      first = 2;
      second = 1;
      int expected = -1;
      int actual = list.compare(first, second);
      assertThat(actual).isEqualTo(expected);
    }
  }

}