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

      dictionary = new NativeDictionary<>(1, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("World Hello"), 0);
    }

    @Test
    @DisplayName("should return correct int of hash function")
    void shouldReturnCorrectIntegerOfHashFun() {
      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun(""), 0);
      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun(""), 0);

      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello"), 116);
      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello"), 6);

      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 28);
      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 7);

      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("abba"), 6);
      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("abba"), 10);
    }

    @Test
    @DisplayName("should return same int of hash function for similar chars in strings")
    void shouldReturnSameIntegerSimilarCharsInString() {
      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 28);
      dictionary = new NativeDictionary<>(128, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("World Hello"), 28);

      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("Hello World"), 7);
      dictionary = new NativeDictionary<>(19, String.class);
      this.repeatAssertEqualityTenTimes(dictionary.hashFun("World Hello"), 7);
    }

    private void repeatAssertEqualityTenTimes(int actual, int expected) {
      for (int i = 0; i < 10; i++) {
        assertThat(actual).isEqualTo(expected);
      }
    }
  }

}