package com.github.ducknowledges.datastructures.set;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("PowerSet")
class PowerSetTest {

  private PowerSet set;

  @BeforeEach
  void setUp() {
    set = new PowerSet();
  }

  @Nested
  @DisplayName("put")
  class Put {

    @Test
    @DisplayName("should put element to set")
    void shouldPutElement() {
      set.put("key1");
      set.put("1key");
      assertThat(set.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should not put existed element to set")
    void shouldNotPutExistedElement() {
      set.put("key1");
      set.put("key1");
      assertThat(set.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("should not put null to set")
    void shouldNotPutNull() {
      set.put("key1");
      set.put(null);
      assertThat(set.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("should not put null to empty set")
    void shouldNotPutNullToEmptySet() {
      set.put(null);
      assertThat(set.size()).isEqualTo(0);
    }
  }

  @Nested
  @DisplayName("get")
  class Gut {

    @Test
    @DisplayName("should get Ture if element exist")
    void shouldGetTrueIfExist() {
      set.put("key1");
      set.put("1key");
      assertThat(set.get("key1")).isTrue();
      assertThat(set.get("1key")).isTrue();
    }

    @Test
    @DisplayName("should get False if empty set")
    void shouldGetFalseIfEmptySet() {
      assertThat(set.get("key1")).isFalse();
      assertThat(set.get("1key")).isFalse();
    }

    @Test
    @DisplayName("should get False if element not exist")
    void shouldGetFalseIfElementNotExist() {
      set.put("key1");
      set.put("1key");
      assertThat(set.get("key2")).isFalse();
      assertThat(set.get("2key")).isFalse();
    }

    @Test
    @DisplayName("should get False if null element if empty set")
    void shouldGetFalseOfNullElement() {
      assertThat(set.get(null)).isFalse();
    }

    @Test
    @DisplayName("should get False if null element if set not empty")
    void shouldGetFalseOfNullElementIfSetNotEmpty() {
      set.put("key1");
      set.put("1key");
      assertThat(set.get(null)).isFalse();
    }
  }

  @Nested
  @DisplayName("remove")
  class Remove {
    @Test
    @DisplayName("should remove existed element in set")
    void shouldRemoveElement() {
      set.put("key1");
      set.put("1key");
      set.put("key3");
      assertThat(set.size()).isEqualTo(3);
      assertThat(set.remove("key3")).isTrue();
      assertThat(set.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should not remove not existed element in set")
    void shouldNotPutExistedElement() {
      set.put("key1");
      set.put("key2");
      assertThat(set.remove("key3")).isFalse();
      assertThat(set.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should not remove null element in empty set")
    void shouldNotRemoveNullIfEmptySet() {
      assertThat(set.remove(null)).isFalse();
      assertThat(set.size()).isZero();
    }

    @Test
    @DisplayName("should not remove null element in set")
    void shouldNotPutNull() {
      set.put("key1");
      set.put("key2");
      assertThat(set.remove(null)).isFalse();
      assertThat(set.size()).isEqualTo(2);
    }
  }

}