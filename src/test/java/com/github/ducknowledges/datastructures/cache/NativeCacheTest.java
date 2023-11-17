package com.github.ducknowledges.datastructures.cache;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("NativeCache")
class NativeCacheTest {

  private static final int SIZE = 19;
  private NativeCache<String> cache;

  @BeforeEach
  void setUp() {
    cache = new NativeCache<>(SIZE, String.class);
  }

  @Nested
  @DisplayName("hashFun")
  class HashFun {
    @Test
    @DisplayName("should return ZERO of hash function")
    void shouldReturnZeroOfHashFun() {
      this.repeatAssertEqualityTenTimes(cache.hashFun(null), 0);

      this.repeatAssertEqualityTenTimes(cache.hashFun(""), 0);

      cache = new NativeCache<>(1, String.class);
      this.repeatAssertEqualityTenTimes(cache.hashFun("Hello"), 0);

      cache = new NativeCache<>(1, String.class);
      this.repeatAssertEqualityTenTimes(cache.hashFun("Hello World"), 0);
    }

    @Test
    @DisplayName("should return correct int of hash function")
    void shouldReturnCorrectIntegerOfHashFun() {
      this.repeatAssertEqualityTenTimes(cache.hashFun(""), 0);

      this.repeatAssertEqualityTenTimes(cache.hashFun("Hello"), 6);

      this.repeatAssertEqualityTenTimes(cache.hashFun("Hello World"), 7);

      this.repeatAssertEqualityTenTimes(cache.hashFun("abba"), 10);
    }

    @Test
    @DisplayName("should return same int for collision")
    void shouldReturnSameIntegerSimilarCharsInString() {
      this.repeatAssertEqualityTenTimes(cache.hashFun("key1"), 17);
      this.repeatAssertEqualityTenTimes(cache.hashFun("1key"), 17);
    }

    private void repeatAssertEqualityTenTimes(int actual, int expected) {
      for (int i = 0; i < 10; i++) {
        assertThat(actual).isEqualTo(expected);
      }
    }
  }

  @Nested
  @DisplayName("displace")
  class Displace {
    @Test
    @DisplayName("should displace elements when full cache")
    void shouldDisplace() {
      String[] keys = new String[]{
          "1aaaaaaaaaaaaaaaaaa", "a1aaaaaaaaaaaaaaaaa", "aa1aaaaaaaaaaaaaaaa",
          "aaa1aaaaaaaaaaaaaaa", "aaaa1aaaaaaaaaaaaaa", "aaaaa1aaaaaaaaaaaaa",
          "aaaaaa1aaaaaaaaaaaa", "aaaaaaa1aaaaaaaaaaa", "aaaaaaaa1aaaaaaaaaa",
          "aaaaaaaaa1aaaaaaaaa", "aaaaaaaaaa1aaaaaaaa", "aaaaaaaaaaa1aaaaaaa",
          "aaaaaaaaaaaa1aaaaaa", "aaaaaaaaaaaaa1aaaaa", "aaaaaaaaaaaaaa1aaaa",
          "aaaaaaaaaaaaaaa1aaa", "aaaaaaaaaaaaaaaa1aa", "aaaaaaaaaaaaaaaaa1a",
          "aaaaaaaaaaaaaaaaaa1"
      };
      putKeys(keys);
      callGetKeys(keys);
      assertHits(keys);
      getKey(keys[0], 3);
      getKey(keys[1], 1);

      assertThat(cache.getHit(keys[0])).isEqualTo(3);
      assertThat(cache.getHit(keys[1])).isEqualTo(2);

      cache.put("key1", "val");
      assertThat(cache.isKey("key1")).isTrue();
      assertThat(cache.getHit("key1")).isZero();
      assertThat(cache.isKey(keys[1])).isFalse();

      getKey("key1", 3);
      assertThat(cache.getHit("key1")).isEqualTo(3);
      cache.put("key2", "val");
      assertThat(cache.isKey("key2")).isTrue();
      assertThat(cache.getHit("key2")).isZero();
      assertThat(cache.isKey(keys[2])).isFalse();
    }

    private void putKeys(String[] keys) {
      for (String key: keys) {
        cache.put(key, "val1");
      }
    }

    private void callGetKeys(String[] keys) {
      for (int i = 0; i < keys.length; i++) {
        getKey(keys[i], i);
      }
    }

    private void getKey(String key, int times) {
      for (int i = 0; i < times; i++) {
        cache.get(key);
      }
    }

    private void assertHits(String[] keys) {
      for (int i = 0; i < keys.length; i++) {
        assertThat(cache.getHit(keys[i])).isEqualTo(i);
      }
    }
  }

  @Nested
  @DisplayName("put")
  class Put {

    @Test
    @DisplayName("should put by key to cache")
    void shouldPut() {
      String[] expectedKeys = new String[]{
          "\00", null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null };
      String[] expectedValues = new String[]{
          "value2", null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "value1", null };

      cache.put("key1", "value1");
      cache.put("\00", "value2");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
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


      cache.put("key1", "value1");
      cache.put("key1", "value2");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
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

      cache.put("key1", "value1");
      cache.put("1key", "value2");
      cache.put("\01", "value3");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
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
      cache.slots = keys;
      cache.values = values;

      cache.put("key1", "value1");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
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
      cache.slots = keys;
      cache.values = valuesBefore;

      cache.put("\00", "value1");
      assertThat(cache.slots).isEqualTo(keys);
      assertThat(cache.values).isEqualTo(valuesAfter);
    }

    @Test
    @DisplayName("should not put by key if is cache full")
    void shouldNotPutInFullDict() {
      String[] expectedKeys = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      String[] expectedValues = new String[]{
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
          "10", "11", "12", "13", "14", "15", "16", "17", "18"
      };
      cache.slots = expectedKeys;
      cache.values = expectedValues;

      cache.put("key1", "value1");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
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
      cache.slots = expectedKeys;
      cache.values = expectedValues;

      cache.put(null, "value1");
      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
    }

    @Test
    @DisplayName("should put and replace existed key with collision")
    void shouldPutAndReplaceExistedOneKey() {
      String[] expectedKeys = new String[]{
          null, null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null
      };
      String[] expectedValues = new String[]{
          null, null, null, null, null, null, null, null, null, null,
          null, null, null, null, null, null, null, "val3",null
      };

      cache.put("key1", "val1");
      cache.put("key1", "val2");
      cache.put("key1", "val3");

      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
      assertThat(cache.get("key1")).isEqualTo("val3");
      assertThat(cache.remove("key1")).isTrue();
      assertThat(cache.isKey("key1")).isFalse();
    }

    @Test
    @DisplayName("should put and replace existed key with collision")
    void shouldPutAndReplaceExistedKey() {
      String[] expectedKeys = new String[]{
          null, "1key", null, null, "k1ey", null, null, null, null, null,
          null, null, null, null, null, null, null, "key1", null
      };
      String[] expectedValues = new String[]{
          null, "val1", null, null, "val2", null, null, null, null, null,
          null, null, null, null, null, null, null, "val1",null
      };

      cache.put("key1", "val1");
      cache.put("1key", "val1");
      cache.put("k1ey", "val1");
      cache.put("k1ey", "val2");

      assertThat(cache.slots).isEqualTo(expectedKeys);
      assertThat(cache.values).isEqualTo(expectedValues);
      assertThat(cache.get("k1ey")).isEqualTo("val2");
      assertThat(cache.remove("k1ey")).isTrue();
      assertThat(cache.isKey("k1ey")).isFalse();
    }
  }

  @Nested
  @DisplayName("isKey")
  class IsKey {

    @Test
    @DisplayName("should return True if key exist")
    void shouldFoundKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.isKey("key1")).isTrue();
      assertThat(cache.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should return True if key exist with collision")
    void shouldFoundKeyInCollision() {
      cache.put("key1", "val1");
      cache.put("1key", "val1");
      cache.put("key2", "val2");
      cache.put("2key", "val2");

      assertThat(cache.isKey("key1")).isTrue();
      assertThat(cache.remove("key1")).isTrue();
      assertThat(cache.isKey("key1")).isFalse();
      assertThat(cache.isKey("1key")).isTrue();
      assertThat(cache.remove("1key")).isTrue();
      assertThat(cache.isKey("1key")).isFalse();
      assertThat(cache.isKey("key2")).isTrue();
      assertThat(cache.isKey("2key")).isTrue();
    }

    @Test
    @DisplayName("should return False if key not exist in empty cache")
    void shouldNotFoundKeyInEmptycache() {
      assertThat(cache.isKey("1key")).isFalse();
      assertThat(cache.isKey("2key")).isFalse();
      assertThat(cache.isKey("abracadabra")).isFalse();
    }

    @Test
    @DisplayName("should return False if key null")
    void shouldNotFoundNullKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.isKey(null)).isFalse();
    }

    @Test
    @DisplayName("should return False if key not exist")
    void shouldNotFoundKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.isKey("1key")).isFalse();
      assertThat(cache.isKey("2key")).isFalse();
      assertThat(cache.isKey("abracadabra")).isFalse();
    }
  }

  @Nested
  @DisplayName("get")
  class Get {

    @Test
    @DisplayName("should get value by key")
    void shouldGetValueByKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.getHit("key1")).isZero();
      assertThat(cache.getHit("key2")).isZero();
      assertThat(cache.get("key1")).isEqualTo("val1");
      assertThat(cache.get("key2")).isEqualTo("val2");
      assertThat(cache.getHit("key1")).isEqualTo(1);
      assertThat(cache.getHit("key2")).isEqualTo(1);
    }

    @Test
    @DisplayName("should get value by key two times")
    void shouldGetValueByKeyTwoTimes() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.get("key1")).isEqualTo("val1");
      assertThat(cache.get("key2")).isEqualTo("val2");
      assertThat(cache.get("key1")).isEqualTo("val1");
      assertThat(cache.get("key2")).isEqualTo("val2");
      assertThat(cache.getHit("key1")).isEqualTo(2);
      assertThat(cache.getHit("key2")).isEqualTo(2);
    }

    @Test
    @DisplayName("should return value by key if has collision")
    void shouldGetValueByKeyInCollision() {
      cache.put("key1", "val1");
      cache.put("1key", "val1");
      cache.put("key2", "val2");
      cache.put("2key", "val2");

      assertThat(cache.get("key1")).isEqualTo("val1");
      assertThat(cache.get("1key")).isEqualTo("val1");
      assertThat(cache.get("key2")).isEqualTo("val2");
      assertThat(cache.get("2key")).isEqualTo("val2");
      assertThat(cache.getHit("key1")).isEqualTo(1);
      assertThat(cache.getHit("1key")).isEqualTo(1);
      assertThat(cache.getHit("key2")).isEqualTo(1);
      assertThat(cache.getHit("2key")).isEqualTo(1);
    }

    @Test
    @DisplayName("should return null if key not exist in empty cache")
    void shouldGetNullInEmptycache() {
      assertThat(cache.get("1key")).isNull();
      assertThat(cache.get("2key")).isNull();
      assertThat(cache.getHit("1key")).isZero();
      assertThat(cache.getHit("2key")).isZero();
    }

    @Test
    @DisplayName("should return Null if key null")
    void shouldGetNullIfKeyNull() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.get(null)).isNull();
      assertThat(cache.getHit(null)).isZero();
    }

    @Test
    @DisplayName("should not get value by key if has collision in full cache")
    void shouldGetNullByKeyInFullCollision() {
      cache.put("1aaaaaaaaaaaaaaaaaa", "val1");
      cache.put("a1aaaaaaaaaaaaaaaaa", "val1");
      cache.put("aa1aaaaaaaaaaaaaaaa", "val1");
      cache.put("aaa1aaaaaaaaaaaaaaa", "val1");
      cache.put("aaaa1aaaaaaaaaaaaaa", "val1");
      cache.put("aaaaa1aaaaaaaaaaaaa", "val1");
      cache.put("aaaaaa1aaaaaaaaaaaa", "val1");
      cache.put("aaaaaaa1aaaaaaaaaaa", "val1");
      cache.put("aaaaaaaa1aaaaaaaaaa", "val1");
      cache.put("aaaaaaaaa1aaaaaaaaa", "val1");
      cache.put("aaaaaaaaaa1aaaaaaaa", "val1");
      cache.put("aaaaaaaaaaa1aaaaaaa", "val1");
      cache.put("aaaaaaaaaaaa1aaaaaa", "val1");
      cache.put("aaaaaaaaaaaaa1aaaaa", "val1");
      cache.put("aaaaaaaaaaaaaa1aaaa", "val1");
      cache.put("aaaaaaaaaaaaaaa1aaa", "val1");
      cache.put("aaaaaaaaaaaaaaaa1aa", "val1");
      cache.put("aaaaaaaaaaaaaaaaa1a", "val1");
      cache.put("aaaaaaaaaaaaaaaaaa1", "val1");

      assertThat(cache.get("key1")).isNull();
      assertThat(cache.getHit("key1")).isZero();
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should remove by key")
    void shouldRemoveByKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");
      cache.put("key3", "val3");

      assertThat(cache.remove("key1")).isTrue();
      assertThat(cache.remove("key2")).isTrue();
      assertThat(cache.isKey("key1")).isFalse();
      assertThat(cache.isKey("key2")).isFalse();
      assertThat(cache.isKey("key3")).isTrue();
    }

    @Test
    @DisplayName("should remove by key if has collision")
    void shouldRemoveByKeyInCollision() {
      cache.put("key1", "val1");
      cache.put("1key", "val1");
      cache.put("k1ey", "val1");
      cache.put("key2", "val2");
      cache.put("2key", "val2");

      assertThat(cache.remove("1key")).isTrue();
      assertThat(cache.remove("key1")).isTrue();
      assertThat(cache.remove("2key")).isTrue();
      assertThat(cache.remove("key2")).isTrue();
      assertThat(cache.isKey("1key")).isFalse();
      assertThat(cache.isKey("key1")).isFalse();
      assertThat(cache.isKey("2key")).isFalse();
      assertThat(cache.isKey("key2")).isFalse();
      assertThat(cache.isKey("k1ey")).isTrue();
    }

    @Test
    @DisplayName("should not remove if key not exist in empty cache")
    void shouldNotRemoveByKeyInEmptycache() {
      assertThat(cache.isKey("1key")).isFalse();
      assertThat(cache.isKey("2key")).isFalse();
      assertThat(cache.remove("1key")).isFalse();
      assertThat(cache.remove("2key")).isFalse();
    }

    @Test
    @DisplayName("should not remove if key not exist")
    void shouldNotRemoveByKey() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.remove("abracadabra")).isFalse();
      assertThat(cache.isKey("key1")).isTrue();
      assertThat(cache.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should not remove if key null")
    void shouldNotRemoveByKeyIfKeyNull() {
      cache.put("key1", "val1");
      cache.put("key2", "val2");

      assertThat(cache.remove(null)).isFalse();
      assertThat(cache.isKey("key1")).isTrue();
      assertThat(cache.isKey("key2")).isTrue();
    }

    @Test
    @DisplayName("should remove by key if has collision in full")
    void shouldRemoveByKeyInCollision1() {
      cache.put("1aaaaaaaaaaaaaaaaaa", "val1");
      cache.put("a1aaaaaaaaaaaaaaaaa", "val1");
      cache.put("aa1aaaaaaaaaaaaaaaa", "val1");
      cache.put("aaa1aaaaaaaaaaaaaaa", "val1");
      cache.put("aaaa1aaaaaaaaaaaaaa", "val1");
      cache.put("aaaaa1aaaaaaaaaaaaa", "val1");
      cache.put("aaaaaa1aaaaaaaaaaaa", "val1");
      cache.put("aaaaaaa1aaaaaaaaaaa", "val1");
      cache.put("aaaaaaaa1aaaaaaaaaa", "val1");
      cache.put("aaaaaaaaa1aaaaaaaaa", "val1");
      cache.put("aaaaaaaaaa1aaaaaaaa", "val1");
      cache.put("aaaaaaaaaaa1aaaaaaa", "val1");
      cache.put("aaaaaaaaaaaa1aaaaaa", "val1");
      cache.put("aaaaaaaaaaaaa1aaaaa", "val1");
      cache.put("aaaaaaaaaaaaaa1aaaa", "val1");
      cache.put("aaaaaaaaaaaaaaa1aaa", "val1");
      cache.put("aaaaaaaaaaaaaaaa1aa", "val1");
      cache.put("aaaaaaaaaaaaaaaaa1a", "val1");
      cache.put("aaaaaaaaaaaaaaaaaa1", "val1");

      cache.remove("1aaaaaaaaaaaaaaaaaa");
      cache.remove("a1aaaaaaaaaaaaaaaaa");
      cache.remove("aa1aaaaaaaaaaaaaaaa");
      cache.remove("aaa1aaaaaaaaaaaaaaa");
      cache.remove("aaaa1aaaaaaaaaaaaaa");
      cache.remove("aaaaa1aaaaaaaaaaaaa");
      cache.remove("aaaaaa1aaaaaaaaaaaa");
      cache.remove("aaaaaaa1aaaaaaaaaaa");
      cache.remove("aaaaaaaa1aaaaaaaaaa");
      cache.remove("aaaaaaaaa1aaaaaaaaa");
      cache.remove("aaaaaaaaaa1aaaaaaaa");
      cache.remove("aaaaaaaaaaa1aaaaaaa");
      cache.remove("aaaaaaaaaaaa1aaaaaa");
      cache.remove("aaaaaaaaaaaaa1aaaaa");
      cache.remove("aaaaaaaaaaaaaa1aaaa");
      cache.remove("aaaaaaaaaaaaaaa1aaa");
      cache.remove("aaaaaaaaaaaaaaaa1aa");
      cache.remove("aaaaaaaaaaaaaaaaa1a");
      cache.remove("aaaaaaaaaaaaaaaaaa1");

      assertThat(cache.slots).isEqualTo(new String[19]);
    }
  }

}