package com.github.ducknowledges.datastructures.dynamicarray;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Dynamic array")
class DynArrayTest {

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
}