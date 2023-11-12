package com.github.ducknowledges.datastructures.set;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

  @Nested
  @DisplayName("intersection")
  class Intersection {

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet() {
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.intersection(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet1() {
      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.intersection(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet2() {
      set.put("1");
      set.put("2");
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.intersection(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get intersected set")
    void shouldGetIntersectedSet1() {
      set.put("1");
      set.put("2");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");
      set2.put("3");

      PowerSet newSet = set.intersection(set2);

      assertThat(newSet.size()).isEqualTo(2);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2"));
    }

    @Test
    @DisplayName("should get intersected set")
    void shouldGetIntersectedSet2() {
      set.put("1");
      set.put("2");
      set.put("3");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.intersection(set2);

      assertThat(newSet.size()).isEqualTo(2);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2"));
    }

    @Test
    @DisplayName("intersection performance less 1 second")
    void testIntersectionPerformance() {
      PowerSet set1 = getFullSet();
      PowerSet set2 = getFullSet();

      long startTime = System.currentTimeMillis();
      set1.intersection(set2);
      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;

      assertThat(executionTime).isLessThan(1000L);
      System.out.println("Execution Time: " + executionTime + " millis");
    }
  }

  @Nested
  @DisplayName("union")
  class Union {

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet() {
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.union(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get union set with empty set")
    void shouldGetUnionSetWithEmptySet1() {
      set.put("1");
      set.put("2");
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.union(set2);
      assertThat(newSet.size()).isEqualTo(2);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2"));
    }

    @Test
    @DisplayName("should get union set with empty set")
    void shouldGetUnionSetWithEmptySet2() {
      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.union(set2);
      assertThat(newSet.size()).isEqualTo(2);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2"));
    }

    @Test
    @DisplayName("should get union set")
    void shouldGetUnionSet1() {
      set.put("1");
      set.put("2");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");
      set2.put("3");

      PowerSet newSet = set.union(set2);

      assertThat(newSet.size()).isEqualTo(3);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2", "3"));
    }

    @Test
    @DisplayName("should get union set")
    void shouldGetUnionSet2() {
      set.put("1");
      set.put("2");
      set.put("3");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.union(set2);

      assertThat(newSet.size()).isEqualTo(3);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2", "3"));
    }

    @Test
    @DisplayName("intersection union less 1 second")
    void testIntersectionPerformance() {
      PowerSet set1 = getFullSet();
      PowerSet set2 = getFullSet();

      long startTime = System.currentTimeMillis();
      set1.union(set2);
      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;

      assertThat(executionTime).isLessThan(1000L);
      System.out.println("Execution Time: " + executionTime + " millis");
    }
  }

  @Nested
  @DisplayName("difference")
  class Difference {

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet() {
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.difference(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet1() {
      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.difference(set2);
      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get empty set")
    void shouldGetEmptySet2() {
      set.put("1");
      set.put("2");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");
      set2.put("3");

      PowerSet newSet = set.difference(set2);

      assertThat(newSet.size()).isZero();
      assertThat(newSet.getKeys()).isEmpty();
    }

    @Test
    @DisplayName("should get difference set")
    void shouldGetDifferenceSet() {
      set.put("1");
      set.put("2");
      PowerSet set2 = new PowerSet();

      PowerSet newSet = set.difference(set2);
      assertThat(newSet.size()).isEqualTo(2);
      assertThat(newSet.getKeys()).isEqualTo(List.of("1", "2"));
    }

    @Test
    @DisplayName("should get difference set")
    void shouldGetDifferenceSet2() {
      set.put("1");
      set.put("2");
      set.put("3");

      PowerSet set2 = new PowerSet();
      set2.put("1");
      set2.put("2");

      PowerSet newSet = set.difference(set2);

      assertThat(newSet.size()).isEqualTo(1);
      assertThat(newSet.getKeys()).isEqualTo(List.of("3"));
    }

    @Test
    @DisplayName("intersection difference less 1 second")
    void testIntersectionPerformance() {
      PowerSet set1 = getDifferenceFullSe1();
      PowerSet set2 = getDifferenceFullSet2();

      long startTime = System.currentTimeMillis();
      set1.difference(set2);
      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;

      assertThat(executionTime).isLessThan(1000L);
      System.out.println("Execution Time: " + executionTime + " millis");
    }
  }

  private static PowerSet getFullSet() {
    PowerSet set = new PowerSet();
    for (int i = 0; i < 20000; i++) {
      set.put("key" + i);
    }
    return set;
  }

  private static PowerSet getDifferenceFullSe1() {
    PowerSet set = new PowerSet();
    for (int i = 0; i < 10000; i++) {
      set.put("key" + i);
    }
    return set;
  }

  private static PowerSet getDifferenceFullSet2() {
    PowerSet set = new PowerSet();
    for (int i = 0; i < 10000; i++) {
      set.put("" + i + "key");
    }
    return set;
  }

}