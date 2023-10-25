package com.github.ducknowledges.datastructures.hashtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hashtable")
class HashTableTest {


  private static final int SIZE = 19;
  private static final int STEP = 3;
  private HashTable hashTable;

  @BeforeEach
  void setUp() {
    hashTable = new HashTable(SIZE, STEP);
  }

  @Test
  @DisplayName("should return ZERO of hash function")
  void shouldReturnZeroOfHashFun() {
    hashTable = new HashTable(1, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello"), 0);

    hashTable = new HashTable(1, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 0);

    hashTable = new HashTable(1, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("World Hello"), 0);
  }

  @Test
  @DisplayName("should return correct int of hash function")
  void shouldReturnCorrectIntegerOfHashFun() {
    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello"), 116);
    hashTable = new HashTable(19, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello"), 6);

    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 28);
    hashTable = new HashTable(19, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 7);

    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("abba"), 6);
    hashTable = new HashTable(19, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("abba"), 10);
  }

  @Test
  @DisplayName("should return same int of hash function for similar chars in strings")
  void shouldReturnSameIntegerSimilarCharsInString() {
    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 28);
    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("World Hello"), 28);

    hashTable = new HashTable(19, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 7);
    hashTable = new HashTable(19, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("World Hello"), 7);
  }

  private void repeatAssertEqualityTenTimes(int actual, int expected) {
    for (int i = 0; i < 10; i++) {
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Test
  @DisplayName("should get correct slot of empty hashtable slot")
  void shouldSeekEmptySlot() {
    int actual = hashTable.seekSlot("Hello World");
    assertThat(actual).isEqualTo(7);
  }

  @Test
  @DisplayName("should seek next slot for not empty hashtable slot")
  void shouldSeekIfNotEmptySlot() {
    String input = "World Hello";
    hashTable.slots[7] = input;
    int actual = hashTable.seekSlot(input);
    assertThat(actual).isEqualTo(10);
  }

  @Test
  @DisplayName("should seek slot in single empty hashtable slot")
  void shouldSeekSingleEmptySlot() {
    String[] fakeSlot = new String[]{
        "0", "1", "2", "3", "4", "5", null, "7", "8", "9",
        "10", "11", "12", "13", "14", "15", "16", "17", "18"
    };
    String input = "7";
    hashTable.slots = fakeSlot;
    int actual = hashTable.seekSlot(input);
    assertThat(actual).isEqualTo(6);
  }

  @Test
  @DisplayName("should seek slot in last empty hashtable slot")
  void shouldSeekLastEmptySlot() {
    String[] fakeSlot = new String[]{
        null, "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "11", "12", "13", "14", "15", "16", "17", "18"
    };
    String input = "\022";
    assertThat(hashTable.hashFun(input)).isEqualTo(18);
    hashTable.slots = fakeSlot;
    int actual = hashTable.seekSlot(input);
    assertThat(actual).isZero();
  }

    @Test
  @DisplayName("should not seek empty slot")
  void shouldNotBeSeek() {
      String[] fakeSlot = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String input = "7";
      hashTable.slots = fakeSlot;
      int actual = hashTable.seekSlot(input);
      assertThat(actual).isEqualTo(-1);
  }

}