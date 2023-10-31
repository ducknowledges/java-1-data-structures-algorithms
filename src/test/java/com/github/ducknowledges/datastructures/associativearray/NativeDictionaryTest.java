package com.github.ducknowledges.datastructures.associativearray;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("NativeDictionary")
class NativeDictionaryTest {

  private static final int SIZE = 19;
  private NativeDictionary<String> dictionary;

  @BeforeEach
  void setUp() {
    dictionary = new NativeDictionary<>(SIZE, String.class);
  }

  @Nested
  class HashFun {
    @Test
    @DisplayName("should return ZERO of hash function")
    void shouldReturnZeroOfHashFun() {
      this.repeatAssertEqualityTenTimes(dictionary.hashFun(null), 0);

      this.repeatAssertEqualityTenTimes(dictionary.hashFun(""), 0);

      dictionary = new NativeDictionary<>(1, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello"), 0);

      dictionary = new NativeDictionary<>(1, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 0);
    }

    @Test
    @DisplayName("should return correct int of hash function")
    void shouldReturnCorrectIntegerOfHashFun() {
      this.repeatAssertEqualityTenTimes(dictionary.hashFun(""), 0);

      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello"), 6);

      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 7);

      this.repeatAssertEqualityTenTimes(dictionary.hashFun("abba"), 10);
    }

    @Test
    @DisplayName("should return same int for collision")
    void shouldReturnSameIntegerSimilarCharsInString() {
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("key1"), 17);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("1key"), 17);
    }

    private void repeatAssertEqualityTenTimes(int actual, int expected) {
      for (int i = 0; i < 10; i++) {
        assertThat(actual).isEqualTo(expected);
      }
    }
  }

  @Nested
  class Put {

    @Test
    @DisplayName("should put by key to dictionary")
    void shouldPut() {
      String[] expectedKeys = new String[]{
          "\00", null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null };
      String[] expectedValues = new String[]{
          "value2", null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "value1", null };

      dictionary.put("key1", "value1");
      dictionary.put("\00", "value2");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should put by exist key and replace value")
    void shouldPutAndReplaceValue() {
      String[] expectedKeys = new String[]{
          null, null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null };
      String[] expectedValues = new String[]{
          null, null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "value2", null };


      dictionary.put("key1", "value1");
      dictionary.put("key1", "value2");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should put by key to next if key collision exist")
    void shouldPutIfCollision() {
      String[] expectedKeys = new String[]{
          null, "1key", null, null, "\01", null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null };
      String[] expectedValues = new String[]{
          null, "value2", null, null, "value3", null, null, null, null, null,
          null, null, null, null, null, null, null, "value1", null };

      dictionary.put("key1", "value1");
      dictionary.put("1key", "value2");
      dictionary.put("\01", "value3");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should put by key in last empty slot")
    void shouldPutInLastSlot() {
      String[] keys = new String[]{
          "0", "1", "2", "3", "4", "5", null, "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] values = new String[]{
          "0", "1", "2", "3", "4", "5", null, "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] expectedKeys = new String[]{
          "0", "1", "2", "3", "4", "5", "key1", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] expectedValues = new String[]{
          "0", "1", "2", "3", "4", "5", "value1", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      dictionary.slots = keys;
      dictionary.values = values;

      dictionary.put("key1", "value1");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should put by key in right key")
    void shouldPutInRightKey() {
      String[] keys = new String[]{
          "\00", "\00", "\00", "\00", "\00", "\00", "\00", "\00", "\00", "\00",
          "\00", "\00", "\00", "\00", "\00", "\00", "\00", "\00", "\00"
      };
      String[] valuesBefore = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] valuesAfter = new String[]{
          "value1", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      dictionary.slots = keys;
      dictionary.values = valuesBefore;

      dictionary.put("\00", "value1");
      assertThat(dictionary.slots).isEqualTo(keys);
      assertThat(dictionary.values).isEqualTo(valuesAfter);
    }

    @Test
    @DisplayName("should not put by key if is dictionary full")
    void shouldNotPutInFullDict() {
      String[] expectedKeys = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] expectedValues = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      dictionary.slots = expectedKeys;
      dictionary.values = expectedValues;

      dictionary.put("key1", "value1");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should not put by null key")
    void shouldNotPutNull() {
      String[] expectedKeys = new String[]{
          "0", "1", "2", "3", "4", "5", null, "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] expectedValues = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      dictionary.slots = expectedKeys;
      dictionary.values = expectedValues;

      dictionary.put(null, "value1");
      assertThat(dictionary.slots).isEqualTo(expectedKeys);
      assertThat(dictionary.values).isEqualTo(expectedValues);
    }
  }

}