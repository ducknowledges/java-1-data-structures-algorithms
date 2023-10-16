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
      int expected = -1;
      int actual = list.compare(first, second);
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return +1 if first element is less than first")
    void shouldReturnPositive() {
      first = 2;
      second = 1;
      int expected = 1;
      int actual = list.compare(first, second);
      assertThat(actual).isEqualTo(expected);
    }
  }


  @Nested
  @DisplayName("add")
  class Add {
    @Test
    @DisplayName("should add element to empty list")
    void shouldAddElementToEmptyList() {
      int expected = 1;
      assertThat(list.count()).isZero();
      list.add(expected);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(1);
      assertThat(array).hasSize(1);
      assertThat(array[0]).isEqualTo(expected);
    }

    @Test
    @DisplayName("should add integer elements in ASC order")
    void shouldAddElementsInAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should add integer elements in DESC order")
    void shouldAddElementsInDescOrder() {
      list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should add double elements in ASC order")
    void shouldAddDoublesInAscOrder() {
      OrderedList<Double> list = new OrderedList<>(true);
      var expected = new Double[]{0., 1., 2., 5.};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should add double elements in DESC order")
    void shouldAddDoublesInDescOrder() {
      OrderedList<Double> list = new OrderedList<>(false);
      var expected = new Double[]{5., 2., 1., 0.};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should add strings elements in ASC order")
    void shouldAddStringsInAscOrder() {
      OrderedList<String> list = new OrderedList<>(true);
      var expected = new String[]{"a ", " b", " c ", "  d   "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should add strings elements in DESC order")
    void shouldAddStringsInDescOrder() {
      OrderedList<String> list = new OrderedList<>(false);
      var expected = new String[]{"  d   ", " c ", " b", "a "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(4);
      assertThat(array).hasSize(4);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }
  }

}