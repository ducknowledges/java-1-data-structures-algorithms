package com.github.ducknowledges.datastructures.dynamicarray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Dynamic array")
class DynArrayTest {

  private final static int DEF_CAPACITY = 16;
  private final static int INCREMENT = 2;
  private final static double DECREMENT = 1.5;

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
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
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
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
          .hasMessage(expected);
    }
  }

  @Nested
  @DisplayName("insert")
  class Insert {
    @Test
    @DisplayName("should insert element without grow capacity")
    void shouldInsertWithoutGrowCapacity() {
      int length = 15;
      int expected = 999;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, 7);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(7)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should insert element with grow capacity")
    void shouldInsertWithGrowCapacity() {
      int length = 16;
      int expected = 999;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, 7);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(7)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should append element without grow capacity")
    void shouldAppendWithoutGrowCapacity() {
      int length = 15;
      int expected = 999;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, length);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(length)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should append element with grow capacity")
    void shouldAppendWithGrowCapacity() {
      int length = 16;
      int expected = 999;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, length);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(length)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should append element without grow capacity if array is empty")
    void shouldAppendWithoutGrowCapacityIfEmptyArray() {
      int index = 0;
      int expected = 0;
      DynArray<Integer> dynArray = new DynArray<>(Integer.class);

      assertThat(dynArray.count).isZero();
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, index);
      assertThat(dynArray.count).isEqualTo(1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(index)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should append element without grow capacity if array has single element")
    void shouldAppendWithoutGrowCapacityIfArrayHasSingleElement() {
      int length = 1;
      int expected = 999;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      dynArray.insert(expected, 0);
      assertThat(dynArray.count).isEqualTo(length + 1);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(0)).isEqualTo(expected);
    }

    @Test
    @DisplayName("should throw ArrayIndexOutOfBoundsException if index is negative")
    void shouldThrowIndexOutOfBoundExceptionIfNegativeIndex() {
      int outIndex = -1;
      int length = 3;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThatThrownBy(() -> dynArray.insert(1, outIndex))
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
          .hasMessage(expected);
    }

    @Test()
    @DisplayName("should throw ArrayIndexOutOfBoundsException if index out of max index")
    void shouldThrowIndexOutOfBoundExceptionIfIndexOutOfMaxIndex() {
      int outIndex = 4;
      int length = 3;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      Integer[] arr = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(arr);
      assertThatThrownBy(() -> dynArray.insert(1, outIndex))
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
          .hasMessage(expected);
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should remove single element without shrink capacity")
    void shouldRemoveSingleWithoutShrinkCapacity() {
      int length = 1;
      int expected = 0;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(expected)).isEqualTo(expected);
      dynArray.remove(expected);
      assertThat(dynArray.count).isZero();
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);

      assertThat(dynArray.array[length - 1]).isNull();
    }

    @Test
    @DisplayName("should remove first element without shrink capacity")
    void shouldRemoveFirstWithoutShrinkCapacity() {
      int length = 32;
      int lastIndex = length - 1;
      int index = 0;
      int expectedLength = length - 1;
      int expectedLastIndex = expectedLength - 1;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(index)).isEqualTo(index);
      dynArray.remove(index);
      assertThat(dynArray.count).isEqualTo(expectedLength);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(index)).isEqualTo(array[index + 1]);
      assertThat(dynArray.getItem(expectedLastIndex)).isEqualTo(array[lastIndex]);

      assertThat(dynArray.array[lastIndex]).isNull();
    }

    @Test
    @DisplayName("should remove last element without shrink capacity")
    void shouldRemoveLastWithoutShrinkCapacity() {
      int length = 32;
      int lastIndex = length - 1;
      int index = 31;
      int expectedLength = length - 1;
      int expectedLastIndex = expectedLength - 1;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(index)).isEqualTo(index);
      dynArray.remove(index);
      assertThat(dynArray.count).isEqualTo(expectedLength);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(expectedLastIndex)).isEqualTo(array[lastIndex - 1]);

      assertThat(dynArray.array[lastIndex]).isNull();
    }

    @Test
    @DisplayName("should remove element from middle without shrink capacity")
    void shouldRemoveMiddleWithoutShrinkCapacity() {
      int length = 32;
      int lastIndex = length - 1;
      int index = 7;
      int expectedLength = length - 1;
      int expectedLastIndex = expectedLength - 1;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(index)).isEqualTo(index);
      dynArray.remove(index);
      assertThat(dynArray.count).isEqualTo(expectedLength);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY * INCREMENT);
      assertThat(dynArray.getItem(index)).isEqualTo(array[index + 1]);
      assertThat(dynArray.getItem(expectedLastIndex)).isEqualTo(array[lastIndex]);

      assertThat(dynArray.array[lastIndex]).isNull();
    }

    @Test
    @DisplayName("should remove element with shrink capacity")
    void shouldRemoveWithShrinkCapacity() {
      int capacityBefore = DEF_CAPACITY * INCREMENT * INCREMENT;
      int capacityAfter = (int) (capacityBefore / DECREMENT);

      int length = 33;
      int lastIndex = length - 1;
      int index = 32;
      int expectedLength = length - 2;
      int expectedLastIndex = expectedLength - 1;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(capacityBefore);
      assertThat(dynArray.getItem(index)).isEqualTo(index);
      dynArray.remove(index);
      dynArray.remove(index - 1);
      assertThat(dynArray.count).isEqualTo(expectedLength);
      assertThat(dynArray.capacity).isEqualTo(capacityAfter);
      assertThat(dynArray.getItem(expectedLastIndex)).isEqualTo(array[lastIndex - 2]);

      assertThat(dynArray.array[lastIndex]).isNull();
    }

    @Test
    @DisplayName("should remove element with shrink to default capacity")
    void shouldRemoveWithShrinkToDefaultCapacity() {
      int capacityBefore = DEF_CAPACITY * INCREMENT * INCREMENT;

      int length = 33;
      int lastIndex = 32;
      int elementsToRemove = 26;
      int expectedLength = length - elementsToRemove;
      int expectedLastIndex = expectedLength - 1;
      Integer[] array = getIntegerArray(length);
      DynArray<Integer> dynArray = getDynArray(array);

      assertThat(dynArray.count).isEqualTo(length);
      assertThat(dynArray.capacity).isEqualTo(capacityBefore);
      assertThat(dynArray.getItem(lastIndex)).isEqualTo(lastIndex);
      for (int i = 0; i < length - expectedLength; i++) {
        dynArray.remove(lastIndex - i);
      }
      assertThat(dynArray.count).isEqualTo(expectedLength);
      assertThat(dynArray.capacity).isEqualTo(DEF_CAPACITY);
      assertThat(dynArray.getItem(expectedLastIndex)).isEqualTo(array[expectedLength - 1]);

      assertThat(dynArray.array[expectedLength]).isNull();
    }

    @Test
    @DisplayName("should throw ArrayIndexOutOfBoundsException if array is empty")
    void shouldThrowIndexOutOfBoundExceptionIfEmptyArray() {
      int outIndex = 0;
      int length = 0;
      String expected = String.format("Index %d out of bounds for length %d", outIndex, length);
      DynArray<Integer> dynArray = new DynArray<>(Integer.class);
      assertThatThrownBy(() -> dynArray.remove(outIndex))
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
      assertThatThrownBy(() -> dynArray.remove(outIndex))
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
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
      assertThatThrownBy(() -> dynArray.remove(outIndex))
          .isInstanceOf(ArrayIndexOutOfBoundsException.class)
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