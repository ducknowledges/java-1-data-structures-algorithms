package com.github.ducknowledges.datastructures.hashtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

  @Nested
  class HashFun {
    @Test
    @DisplayName("should return ZERO of hash function")
    void shouldReturnZeroOfHashFun() {
      this.repeatAssertEqualityTenTimes(hashTable.hashFun(null), 0);

      this.repeatAssertEqualityTenTimes(hashTable.hashFun(""), 0);

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
      this.repeatAssertEqualityTenTimes(hashTable.hashFun(""), 0);
      hashTable = new HashTable(19, 1);
      this.repeatAssertEqualityTenTimes(hashTable.hashFun(""), 0);

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

    @DisplayName("")

    private void repeatAssertEqualityTenTimes(int actual, int expected) {
      for (int i = 0; i < 10; i++) {
        assertThat(actual).isEqualTo(expected);
      }
    }
  }

  @Nested
  class SeekSlot {
    @Test
    @DisplayName("should get not seek slot for null")
    void shouldNotSeekSlotForNull() {
      int actual = hashTable.seekSlot(null);
      assertThat(actual).isEqualTo(-1);
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
    @DisplayName("should not seek empty slot in full hashtable")
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

    @Test
    @DisplayName("should not seek empty slot in full hashtable of same elements")
    void shouldNotBeSeekInSameElements() {
      String[] fakeSlot = new String[]{
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
          "0", "0", "0", "0", "0", "0", "0", "0", "0"
      };
      String input = "0";
      hashTable.slots = fakeSlot;
      int actual = hashTable.seekSlot(input);
      assertThat(actual).isEqualTo(-1);
    }
  }

  @Nested
  class Put {
    @Test
    @DisplayName("should not put null to hashtable")
    void shouldNotPutNull() {
      int actual = hashTable.put(null);
      assertThat(actual).isEqualTo(-1);

      String[] expectedSlot = new String[]{
          null, null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, null, null
      };
      assertThat(hashTable.slots).isEqualTo(expectedSlot);
    }


    @Test
    @DisplayName("should put to empty hashtable")
    void shouldPutToEmptyHashtable() {
      String input = "Hello World";
      int actual = hashTable.put(input);
      assertThat(actual).isEqualTo(7);

      String[] expectedSlot = new String[]{
          null, null, null, null, null, null, null, "Hello World", null, null,
          null, null, null, null, null, null, null, null, null
      };
      assertThat(hashTable.slots).isEqualTo(expectedSlot);
    }

    @Test
    @DisplayName("should put to next slot for not empty hashtable slot")
    void shouldPutIfNotEmptySlotOfHashtable() {
      String input = "World Hello";
      hashTable.slots[7] = "Hello World";
      int actual = hashTable.put(input);
      assertThat(actual).isEqualTo(10);

      String[] expectedSlot = new String[]{
          null, null, null, null, null, null, null, "Hello World", null, null,
          "World Hello", null, null, null, null, null, null, null, null
      };
      assertThat(hashTable.slots).isEqualTo(expectedSlot);
    }

    @Test
    @DisplayName("should put slot in single empty slot of hashtable")
    void shouldPutSingleEmptySlot() {
      String[] fakeSlot = new String[]{
          "0", "1", "2", "3", "4", "5", null, "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String input = "7";
      hashTable.slots = fakeSlot;
      int actual = hashTable.put(input);
      assertThat(actual).isEqualTo(6);

      String[] expectedSlot = new String[]{
          "0", "1", "2", "3", "4", "5", "7", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      assertThat(hashTable.slots).isEqualTo(expectedSlot);
    }

    @Test
    @DisplayName("should put slot in last empty slot of hashtable")
    void shouldPutLastEmptySlot() {
      String[] fakeSlot = new String[]{
          null, "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String input = "\022";
      assertThat(hashTable.hashFun(input)).isEqualTo(18);
      hashTable.slots = fakeSlot;
      int actual = hashTable.put(input);
      assertThat(actual).isZero();

      String[] expectedSlot = new String[]{
          "\022", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      assertThat(hashTable.slots).isEqualTo(expectedSlot);
    }

    @Test
    @DisplayName("should not put in full hashtable slots")
    void shouldNotBePut() {
      String[] fakeSlot = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String input = "input";
      hashTable.slots = fakeSlot;
      int actual = hashTable.put(input);
      assertThat(actual).isEqualTo(-1);

      assertThat(hashTable.slots).isEqualTo(fakeSlot);
    }

    @Test
    @DisplayName("should not put in full hashtable slots with same elements")
    void shouldNotBePutInSameElements() {
      String[] fakeSlot = new String[]{
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
          "0", "0", "0", "0", "0", "0", "0", "0", "0"
      };
      String input = "input";
      hashTable.slots = fakeSlot;
      int actual = hashTable.put(input);
      assertThat(actual).isEqualTo(-1);

      assertThat(hashTable.slots).isEqualTo(fakeSlot);
    }
  }

}