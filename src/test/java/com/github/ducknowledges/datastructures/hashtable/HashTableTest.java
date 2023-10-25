package com.github.ducknowledges.datastructures.hashtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hashtable")
class HashTableTest {

  private HashTable hashTable;

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
    hashTable = new HashTable(16, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello"), 4);

    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 28);
    hashTable = new HashTable(16, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 12);

    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("abba"), 6);
    hashTable = new HashTable(16, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("abba"), 6);
  }

  @Test
  @DisplayName("should return same int of hash function for similar chars in strings")
  void shouldReturnSameIntegerSimilarCharsInString() {
    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 28);
    hashTable = new HashTable(128, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("World Hello"), 28);

    hashTable = new HashTable(16, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("Hello World"), 12);
    hashTable = new HashTable(16, 1);
    this.repeatAssertEqualityTenTimes(hashTable.hashFun("World Hello"), 12);
  }

  private void repeatAssertEqualityTenTimes(int actual, int expected) {
    for (int i = 0; i < 10; i++) {
      assertThat(actual).isEqualTo(expected);
    }
  }



}