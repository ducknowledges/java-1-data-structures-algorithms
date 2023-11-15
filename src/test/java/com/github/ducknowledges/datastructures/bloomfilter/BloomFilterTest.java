package com.github.ducknowledges.datastructures.bloomfilter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloomFilter")
class BloomFilterTest {

  private BloomFilter filter;

  @BeforeEach
  void init() {
    filter = new BloomFilter(32);
    filter.add("0123456789");
    filter.add("1234567890");
    filter.add("2345678901");
    filter.add("3456789012");
    filter.add("4567890123");
    filter.add("5678901234");
    filter.add("6789012345");
    filter.add("7890123456");
    filter.add("8901234567");
    filter.add("9012345678");
  }

  @Test
  @DisplayName("should add")
  void shouldAdd() {
    filter = new BloomFilter(32);
    filter.add("0123456789");
    assertThat(filter.toString()).hasToString("10000000000000100000");
  }

  @Test
  @DisplayName("should set filter bits for 10 values")
  void shouldSetFilterBits() {
    assertThat(filter.toString()).hasToString("101000000010000010000000101000");
  }

  @Test
  @DisplayName("should get True for added 10 values")
  void shouldGetTrueForAddedValues() {
    assertThat(filter.isValue("0123456789")).isTrue();
    assertThat(filter.isValue("1234567890")).isTrue();
    assertThat(filter.isValue("2345678901")).isTrue();
    assertThat(filter.isValue("3456789012")).isTrue();
    assertThat(filter.isValue("4567890123")).isTrue();
    assertThat(filter.isValue("5678901234")).isTrue();
    assertThat(filter.isValue("6789012345")).isTrue();
    assertThat(filter.isValue("7890123456")).isTrue();
    assertThat(filter.isValue("8901234567")).isTrue();
    assertThat(filter.isValue("9012345678")).isTrue();
  }

  @Test
  @DisplayName("should get False for non existed values")
  void shouldGetFalseForNonAddedValues() {
    assertThat(filter.isValue("012345678")).isFalse();
    assertThat(filter.isValue("123456789")).isFalse();
    assertThat(filter.isValue("234567890")).isFalse();
    assertThat(filter.isValue("345678901")).isFalse();
    assertThat(filter.isValue("456789012")).isFalse();
    assertThat(filter.isValue("567890123")).isFalse();
    assertThat(filter.isValue("678901234")).isFalse();
    assertThat(filter.isValue("789012345")).isFalse();
    assertThat(filter.isValue("890123456")).isFalse();
    assertThat(filter.isValue("901234567")).isFalse();
  }

  @Test
  @DisplayName("should get false positive rate less then 35%")
  void shouldHasFalsePositiveRate() {
    int filterLen = 32;
    long elements = 10;
    BloomFilter filter = new BloomFilter(filterLen);

    for (int i = 0; i < elements; i++) {
      filter.add("key" + i);
    }
    for (int i = 0; i < elements; i++) {
      assertThat(filter.isValue("key" + i)).isTrue();
    }

    int iterations = 1000000;
    int count = 0;
    for (int i = 11; i < iterations; i++) {
      if (filter.isValue("key" + i)) {
        count++;
      }
    }

    double resultRate = (double) count / iterations;
    assertThat(resultRate).isLessThan(0.35);
  }
}