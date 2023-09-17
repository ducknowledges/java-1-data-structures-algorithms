package com.github.ducknowledges.datastructures.dynamicarray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Dynamic array")
class DynArrayTest {

  private final static int DEF_CAPACITY = 16;

  @Nested
  @DisplayName("make array")
  class MakeArray {
    @Test
    @DisplayName("should make array with default capacity")
    void shouldMakeDefaultCapacityArray() {
      int capacity = 15;
      int expected = 16;
      Integer[] expectedArray =  new Integer[16];
      DynArray<Integer> dynArray = new DynArray<>(Integer.class);

      assertThat(dynArray.capacity).isEqualTo(expected);
      assertThat(dynArray.array).hasSize(expected);
      dynArray.makeArray(capacity);
      assertThat(dynArray.capacity).isEqualTo(expected);
      assertThat(dynArray.array).isEqualTo(expectedArray);
    }

    @Test
    @DisplayName("should make array with new capacity")
    void shouldMakeNewCapacityArray() {
      int expected = 32;
      Integer[] expectedArray =  new Integer[32];
      DynArray<Integer> dynArray = new DynArray<>(Integer.class);

      dynArray.makeArray(expected);
      assertThat(dynArray.capacity).isEqualTo(expected);
      assertThat(dynArray.array).isEqualTo(expectedArray);
    }
  }

  @Nested
  @DisplayName("append")
  class Append {
    @Test
    @DisplayName("should append elements")
    void shouldAppendElements() {
      Integer[] expected = new Integer[] {1, 2, 3};
      DynArray<Integer> dynArray = getDynArray(expected);
      assertThat(dynArray.array[0]).isEqualTo(expected[0]);
      assertThat(dynArray.array[1]).isEqualTo(expected[1]);
      assertThat(dynArray.array[2]).isEqualTo(expected[2]);
    }

    @Test
    @DisplayName("should append element without increasing capacity")
    void shouldAppendElementWithoutIncreasingCapacity() {
      Integer[] arr = getIntegerArray(16);
      DynArray<Integer> dynArray = getDynArray(arr);

      assertThat(dynArray.count).isEqualTo(arr.length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.array).hasSize(DEF_CAPACITY);
    }

    @Test
    @DisplayName("should append element with increasing capacity")
    void shouldAppendElementWithIncreasingCapacity() {
      int length = DEF_CAPACITY * 2;
      int expectedCapacity = DEF_CAPACITY * 4;
      int expectedValue = 999;
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      dynArray.append(expectedValue);
      assertThat(dynArray.array[length]).isEqualTo(expectedValue);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(expectedCapacity);
      assertThat(dynArray.array).hasSize(expectedCapacity);
    }
  }

  @Nested
  @DisplayName("get item")
  class GetItem {
    @Test
    @DisplayName("should get item by index")
    void shouldGetItem() {
      int length = 4;
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThat(dynArray.getItem(3)).isEqualTo(arr[3]);
    }

    @Test
    @DisplayName("should throw ArrayIndexOutOfBoundsException if array is empty")
    void shouldThrowExceptionIfEmptyArray() {
      int outIndex = 0;
      int length = 0;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThatThrownBy(() -> dynArray.getItem(outIndex))
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
          .hasMessage(expected);
    }

    @Test
    @DisplayName("should throw ArrayIndexOutOfBoundsException if index is negative")
    void shouldThrowIndexOutOfBoundExceptionIfNegativeIndex() {
      int outIndex = -1;
      int length = 3;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThatThrownBy(() -> dynArray.getItem(outIndex))
          .isInstanceOf(IndexOutOfBoundsException.class)
          .hasMessage(expected);
    }

    @Test()
    @DisplayName("should throw ArrayIndexOutOfBoundsException if index out of max index")
    void shouldThrowIndexOutOfBoundExceptionIfIndexOutOfMaxIndex() {
      int outIndex = 3;
      int length = 3;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThatThrownBy(() -> dynArray.getItem(outIndex))
          .isInstanceOf(IndexOutOfBoundsException.class)
          .hasMessage(expected);
    }
  }

  private DynArray<Integer> getDynArray(Integer[] array) {
    DynArray<Integer> dynArray = new DynArray<>(Integer.class);
    for (Integer integer : array) {
      dynArray.append(integer);
    }
    return dynArray;
  }

  private Integer[] getIntegerArray(int length) {
    Integer[] array = new Integer[length];
    for (int i = 0; i < length; i++) {
      array[i] = i;
    }
    return array;
  }
}