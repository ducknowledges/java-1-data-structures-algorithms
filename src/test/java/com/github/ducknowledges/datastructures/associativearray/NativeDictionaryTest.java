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
  @DisplayName("hashFun")
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
  @DisplayName("put")
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

  @Nested
  @DisplayName("isKey")
  class IsKey {

    @Test
    @DisplayName("should return True if key exist")
    void shouldFoundKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.isKey("key1")).isTrue();
      assertThat(dictionary.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should return True if key exist with collision")
    void shouldFoundKeyInCollision() {
      dictionary.put("key1", "val1");
      dictionary.put("1key", "val1");
      dictionary.put("key2", "val2");
      dictionary.put("2key", "val2");

      assertThat(dictionary.isKey("key1")).isTrue();
      assertThat(dictionary.remove("key1")).isTrue();
      assertThat(dictionary.isKey("key1")).isFalse();
      assertThat(dictionary.isKey("1key")).isTrue();
      assertThat(dictionary.remove("1key")).isTrue();
      assertThat(dictionary.isKey("1key")).isFalse();
      assertThat(dictionary.isKey("key2")).isTrue();
      assertThat(dictionary.isKey("2key")).isTrue();
    }

    @Test
    @DisplayName("should return False if key not exist in empty dictionary")
    void shouldNotFoundKeyInEmptyDictionary() {
      assertThat(dictionary.isKey("1key")).isFalse();
      assertThat(dictionary.isKey("2key")).isFalse();
      assertThat(dictionary.isKey("abracadabra")).isFalse();
    }

    @Test
    @DisplayName("should return False if key null")
    void shouldNotFoundNullKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.isKey(null)).isFalse();
    }

    @Test
    @DisplayName("should return False if key not exist")
    void shouldNotFoundKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.isKey("1key")).isFalse();
      assertThat(dictionary.isKey("2key")).isFalse();
      assertThat(dictionary.isKey("abracadabra")).isFalse();
    }

    @Test
    @DisplayName("should return False by key if key not exist in full dictionary with collisions")
    void shouldGetNotFoundKeyInFullCollision() {
      dictionary.put("1aaaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("a1aaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aa1aaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaa1aaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaa1aaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaa1aaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaa1aaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaa1aaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaa1aaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaa1aaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaa1aaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaa1aaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaa1aaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaa1aaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaa1aaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaa1aaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaa1aa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaa1a", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaaa1", "val1");
      dictionary.put("key1", "val");

      assertThat(dictionary.isKey("key1")).isFalse();
    }
  }

  @Nested
  @DisplayName("get")
  class Get {

    @Test
    @DisplayName("should get value by key")
    void shouldGetValueByKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");
      dictionary.put("key3", "val3");

      assertThat(dictionary.get("key1")).isEqualTo("val1");
      assertThat(dictionary.get("key2")).isEqualTo("val2");
    }

    @Test
    @DisplayName("should return value by key if has collision")
    void shouldGetValueByKeyInCollision() {
      dictionary.put("key1", "val1");
      dictionary.put("1key", "val1");
      dictionary.put("key2", "val2");
      dictionary.put("2key", "val2");

      assertThat(dictionary.get("key1")).isEqualTo("val1");
      assertThat(dictionary.get("1key")).isEqualTo("val1");
      assertThat(dictionary.get("key2")).isEqualTo("val2");
      assertThat(dictionary.get("2key")).isEqualTo("val2");
    }

    @Test
    @DisplayName("should return null if key not exist in empty dictionary")
    void shouldGetNullInEmptyDictionary() {
      assertThat(dictionary.get("1key")).isNull();
      assertThat(dictionary.get("2key")).isNull();
    }

    @Test
    @DisplayName("should return Null if key null")
    void shouldGetNullIfKeyNull() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.get(null)).isNull();
    }

    @Test
    @DisplayName("should not get value by key if has collision in full dictionary")
    void shouldGetNullByKeyInFullCollision() {
      dictionary.put("1aaaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("a1aaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aa1aaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaa1aaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaa1aaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaa1aaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaa1aaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaa1aaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaa1aaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaa1aaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaa1aaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaa1aaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaa1aaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaa1aaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaa1aaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaa1aaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaa1aa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaa1a", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaaa1", "val1");

      assertThat(dictionary.get("key1")).isNull();
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should remove by key")
    void shouldRemoveByKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");
      dictionary.put("key3", "val3");

      assertThat(dictionary.remove("key1")).isTrue();
      assertThat(dictionary.remove("key2")).isTrue();
      assertThat(dictionary.isKey("key1")).isFalse();
      assertThat(dictionary.isKey("key2")).isFalse();
      assertThat(dictionary.isKey("key3")).isTrue();
    }

    @Test
    @DisplayName("should remove by key if has collision")
    void shouldRemoveByKeyInCollision() {
      int x = dictionary.hashFun("key1");
      int y = dictionary.hashFun("k1ey");

      dictionary.put("key1", "val1");
      dictionary.put("1key", "val1");
      dictionary.put("k1ey", "val1");
      dictionary.put("key2", "val2");
      dictionary.put("2key", "val2");

      assertThat(dictionary.remove("1key")).isTrue();
      assertThat(dictionary.remove("key1")).isTrue();
      assertThat(dictionary.remove("2key")).isTrue();
      assertThat(dictionary.remove("key2")).isTrue();
      assertThat(dictionary.isKey("1key")).isFalse();
      assertThat(dictionary.isKey("key1")).isFalse();
      assertThat(dictionary.isKey("2key")).isFalse();
      assertThat(dictionary.isKey("key2")).isFalse();
      assertThat(dictionary.isKey("k1ey")).isTrue();
    }

    @Test
    @DisplayName("should not remove if key not exist in empty dictionary")
    void shouldNotRemoveByKeyInEmptyDictionary() {
      assertThat(dictionary.isKey("1key")).isFalse();
      assertThat(dictionary.isKey("2key")).isFalse();
      assertThat(dictionary.remove("1key")).isFalse();
      assertThat(dictionary.remove("2key")).isFalse();
    }

    @Test
    @DisplayName("should not remove if key not exist")
    void shouldNotRemoveByKey() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.remove("abracadabra")).isFalse();
      assertThat(dictionary.isKey("key1")).isTrue();
      assertThat(dictionary.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should not remove if key null")
    void shouldNotRemoveByKeyIfKeyNull() {
      dictionary.put("key1", "val1");
      dictionary.put("key2", "val2");

      assertThat(dictionary.remove(null)).isFalse();
      assertThat(dictionary.isKey("key1")).isTrue();
      assertThat(dictionary.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should remove by key if has collision in full")
    void shouldRemoveByKeyInCollision1() {
      dictionary.put("1aaaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("a1aaaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aa1aaaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaa1aaaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaa1aaaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaa1aaaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaa1aaaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaa1aaaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaa1aaaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaa1aaaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaa1aaaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaa1aaaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaa1aaaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaa1aaaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaa1aaaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaa1aaa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaa1aa", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaa1a", "val1");
      dictionary.put("aaaaaaaaaaaaaaaaaa1", "val1");

      dictionary.remove("1aaaaaaaaaaaaaaaaaa");
      dictionary.remove("a1aaaaaaaaaaaaaaaaa");
      dictionary.remove("aa1aaaaaaaaaaaaaaaa");
      dictionary.remove("aaa1aaaaaaaaaaaaaaa");
      dictionary.remove("aaaa1aaaaaaaaaaaaaa");
      dictionary.remove("aaaaa1aaaaaaaaaaaaa");
      dictionary.remove("aaaaaa1aaaaaaaaaaaa");
      dictionary.remove("aaaaaaa1aaaaaaaaaaa");
      dictionary.remove("aaaaaaaa1aaaaaaaaaa");
      dictionary.remove("aaaaaaaaa1aaaaaaaaa");
      dictionary.remove("aaaaaaaaaa1aaaaaaaa");
      dictionary.remove("aaaaaaaaaaa1aaaaaaa");
      dictionary.remove("aaaaaaaaaaaa1aaaaaa");
      dictionary.remove("aaaaaaaaaaaaa1aaaaa");
      dictionary.remove("aaaaaaaaaaaaaa1aaaa");
      dictionary.remove("aaaaaaaaaaaaaaa1aaa");
      dictionary.remove("aaaaaaaaaaaaaaaa1aa");
      dictionary.remove("aaaaaaaaaaaaaaaaa1a");
      dictionary.remove("aaaaaaaaaaaaaaaaaa1");

      assertThat(dictionary.slots).isEqualTo(new String[19]);
    }
  }

}