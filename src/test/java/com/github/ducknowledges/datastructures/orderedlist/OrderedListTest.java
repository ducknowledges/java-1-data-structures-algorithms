package com.github.ducknowledges.datastructures.orderedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
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

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should add integer element to Head in ASC order")
    void shouldAddElementToHeadInAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);
      list.add(-1);
      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length + 1);
      assertThat(array).hasSize(expected.length + 1);
      assertThat(array[0]).isEqualTo(-1);
      assertThat(array[1]).isEqualTo(expected[0]);
      assertThat(array[2]).isEqualTo(expected[1]);
      assertThat(array[3]).isEqualTo(expected[2]);
      assertThat(array[4]).isEqualTo(expected[3]);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should add integer element to Tail in ASC order")
    void shouldAddElementToTailInAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);
      list.add(6);
      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length + 1);
      assertThat(array).hasSize(expected.length + 1);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
      assertThat(array[4]).isEqualTo(6);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
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

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should add integer element to Head in DESC order")
    void shouldAddElementToHeadInDescOrder() {
      list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);
      list.add(6);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length + 1);
      assertThat(array).hasSize(expected.length + 1);
      assertThat(array[0]).isEqualTo(6);
      assertThat(array[1]).isEqualTo(expected[0]);
      assertThat(array[2]).isEqualTo(expected[1]);
      assertThat(array[3]).isEqualTo(expected[2]);
      assertThat(array[4]).isEqualTo(expected[3]);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should add integer element to Tail in DESC order")
    void shouldAddElementToTailInDescOrder() {
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

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
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

  @Nested
  @DisplayName("find")
  class Find {
    @Test
    @DisplayName("should find null in empty list")
    void shouldFindNullInEmptyList() {
      var actual = list.find(1);
      assertThat(actual).isNull();
      assertThat(list.toArray()).isEqualTo(new Integer[]{});

      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();
    }

    @Test
    @DisplayName("should find element in asc list")
    void shouldFindElementInAscList() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(expected[2]);
      assertThat(actual.value).isEqualTo(expected[2]);
      assertThat(list.toArray()).isEqualTo(expected);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should find element in desc list")
    void shouldFindElementInDescList() {
      OrderedList<Integer> list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(expected[2]);
      assertThat(actual.value).isEqualTo(expected[2]);
      assertThat(list.toArray()).isEqualTo(expected);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should find strings elements in ASC order")
    void shouldFindStringsInAscOrder() {
      OrderedList<String> list = new OrderedList<>(true);
      var expected = new String[]{"a ", " b", " c ", "  d   "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(" " + expected[2]);
      assertThat(actual.value).isEqualTo(expected[2]);
      assertThat(list.toArray()).isEqualTo(expected);
    }

    @Test
    @DisplayName("should find strings elements in DESC order")
    void shouldFindStringsInDescOrder() {
      OrderedList<String> list = new OrderedList<>(false);
      var expected = new String[]{"  d   ", " c ", " b", "a "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(expected[2] + " ");
      assertThat(actual.value).isEqualTo(expected[2]);
      assertThat(list.toArray()).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return null element if element not found in asc order")
    void shouldReturnNullIfNotFoundInAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(6);
      assertThat(actual).isNull();
      assertThat(list.toArray()).isEqualTo(expected);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should return null element if element not found in desc order")
    void shouldReturnNullIfNotFoundInDescOrder() {
      OrderedList<Integer> list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(6);
      assertThat(actual).isNull();
      assertThat(list.toArray()).isEqualTo(expected);

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should return null element if string not found in asc order")
    void shouldReturnNullIfStringNotFoundInAscOrder() {
      OrderedList<String> list = new OrderedList<>(true);
      var expected = new String[]{"a ", " b", " c ", "  d   "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find("f ");
      assertThat(actual).isNull();
      assertThat(list.toArray()).isEqualTo(expected);
    }

    @Test
    @DisplayName("should return null element if string not found in desc order")
    void shouldReturnNullIfStringNotFoundInDescOrder() {
      OrderedList<String> list = new OrderedList<>(false);
      var expected = new String[]{"  d   ", " c ", " b", "a "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var actual = list.find(" f");
      assertThat(actual).isNull();
      assertThat(list.toArray()).isEqualTo(expected);
    }
  }

  @Nested
  @DisplayName("delete")
  class Delete {
    @Test
    @DisplayName("should delete nothing in empty list")
    void shouldDeleteNothingInEmptyList() {
      list.delete(1);
      assertThat(list.count()).isZero();
      assertThat(list.toArray()).isEqualTo(new Integer[]{});

      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();
    }

    @Test
    @DisplayName("should delete element in asc list")
    void shouldDeleteElementInAscList() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(expected[2]);
      assertThat(list.count()).isEqualTo(expected.length - 1);
      assertThat(list.toArray()).isEqualTo(new Integer[]{0, 1, 5});

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should delete element in desc list")
    void shouldDeleteElementInDescList() {
      OrderedList<Integer> list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(expected[2]);
      assertThat(list.count()).isEqualTo(expected.length - 1);
      assertThat(list.toArray()).isEqualTo(new Integer[]{5, 2, 0});

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should delete strings elements in ASC order")
    void shouldDeleteStringsInAscOrder() {
      OrderedList<String> list = new OrderedList<>(true);
      var expected = new String[]{"a ", " b", " c ", "  d   "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(expected[2]);
      assertThat(list.count()).isEqualTo(expected.length - 1);
      assertThat(list.toArray()).isEqualTo(new String[]{"a ", " b", "  d   "});
    }

    @Test
    @DisplayName("should delete strings elements in DESC order")
    void shouldDeleteStringsInDescOrder() {
      OrderedList<String> list = new OrderedList<>(false);
      var expected = new String[]{"  d   ", " c ", " b", "a "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(expected[2]);
      assertThat(list.count()).isEqualTo(expected.length - 1);
      assertThat(list.toArray()).isEqualTo(new String[]{"  d   ", " c ", "a "});
    }

    @Test
    @DisplayName("should not delete element if element not found in asc order")
    void shouldNotDeleteIfNotFoundInAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(6);
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(list.toArray()).isEqualTo(new Integer[]{0, 1, 2, 5});

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should not delete element if element not found in desc order")
    void shouldNotDeleteIfNotFoundInDescOrder() {
      OrderedList<Integer> list = new OrderedList<>(false);
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete(6);
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(list.toArray()).isEqualTo(new Integer[]{5, 2, 1, 0});

      List<Node<Integer>> all = list.getAll();
      assertListHead(all);
      asserListTail(all);
    }

    @Test
    @DisplayName("should not delete string if string not found in asc order")
    void shouldNotDeleteIfStringNotFoundInAscOrder() {
      OrderedList<String> list = new OrderedList<>(true);
      var expected = new String[]{"a ", " b", " c ", "  d   "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete("f ");
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(list.toArray()).isEqualTo(new String[]{"a ", " b", " c ", "  d   "});
    }

    @Test
    @DisplayName("should not delete string if string not found in desc order")
    void shouldNotDeleteIfStringNotFoundInDescOrder() {
      OrderedList<String> list = new OrderedList<>(false);
      var expected = new String[]{"  d   ", " c ", " b", "a "};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      list.delete("f ");
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(list.toArray()).isEqualTo(new String[]{"  d   ", " c ", " b", "a "});
    }
  }

  @Nested
  @DisplayName("clear")
  class Clear {
    @Test
    @DisplayName("should clear to asc empty list")
    void shouldClearToAscEmptyList() {
      assertThat(list.count()).isZero();
      list.clear(true);
      assertThat(list.count()).isZero();
      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();

      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(array).hasSize(expected.length);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should clear to desc empty list")
    void shouldClearToDescEmptyList() {
      assertThat(list.count()).isZero();
      list.clear(false);
      assertThat(list.count()).isZero();
      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();

      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(array).hasSize(expected.length);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should clear list to asc order")
    void shouldClearListToAscOrder() {
      var expected = new Integer[]{0, 1, 2, 5};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      assertThat(list.count()).isEqualTo(expected.length);
      list.clear(true);
      assertThat(list.count()).isZero();
      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();

      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(array).hasSize(expected.length);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }

    @Test
    @DisplayName("should clear list to desc order")
    void shouldClearListToDescOrder() {
      var expected = new Integer[]{5, 2, 1, 0};
      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      assertThat(list.count()).isEqualTo(expected.length);
      list.clear(false);
      assertThat(list.count()).isZero();
      assertThat(list.head).isNull();
      assertThat(list.tail).isNull();

      list.add(expected[2]);
      list.add(expected[1]);
      list.add(expected[0]);
      list.add(expected[3]);

      var array = list.toArray();
      assertThat(list.count()).isEqualTo(expected.length);
      assertThat(array).hasSize(expected.length);
      assertThat(array[0]).isEqualTo(expected[0]);
      assertThat(array[1]).isEqualTo(expected[1]);
      assertThat(array[2]).isEqualTo(expected[2]);
      assertThat(array[3]).isEqualTo(expected[3]);
    }
  }

  void assertListHead(List<Node<Integer>> list) {
    if (list.size() == 1) {
      assertThat(list.get(0).next).isNull();
      assertThat(list.get(0).prev).isNull();
    } else {
      assertThat(list.get(0).next).isEqualTo(list.get(1));
      assertThat(list.get(0).prev).isNull();
    }
  }

  void asserListTail(List<Node<Integer>> list) {
    if (list.size() == 1) {
      assertThat(list.get(0).next).isNull();
      assertThat(list.get(0).prev).isNull();
    } else {
      assertThat(list.get(list.size() - 1).next).isNull();
      assertThat(list.get(list.size() - 1).prev).isEqualTo(list.get(list.size() - 2));
    }
  }

}