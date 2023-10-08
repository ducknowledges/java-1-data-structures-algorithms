package com.github.ducknowledges.datastructures.deque;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("DequeUtils")
class DequeUtilsTest {

  @Nested
  @DisplayName("isPalindrome")
  class IsPalindrome {
    @Test
    @DisplayName("should get true if str is palindrome")
    void shouldGetTrue() {
      assertThat(DequeUtils.isPalindrome("")).isTrue();
      assertThat(DequeUtils.isPalindrome("a")).isTrue();
      assertThat(DequeUtils.isPalindrome("aa")).isTrue();
      assertThat(DequeUtils.isPalindrome("aba")).isTrue();
      assertThat(DequeUtils.isPalindrome("abcba")).isTrue();
      assertThat(DequeUtils.isPalindrome("abcdedcba")).isTrue();
    }

    @Test
    @DisplayName("should get false if str is not palindrome")
    void shouldGetFalse() {
      assertThat(DequeUtils.isPalindrome("ab")).isFalse();
      assertThat(DequeUtils.isPalindrome("abc")).isFalse();
      assertThat(DequeUtils.isPalindrome("abca")).isFalse();
      assertThat(DequeUtils.isPalindrome("aacaaa")).isFalse();
    }

  }

}