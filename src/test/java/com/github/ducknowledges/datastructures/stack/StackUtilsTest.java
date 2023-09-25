package com.github.ducknowledges.datastructures.stack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("StackUtils")
class StackUtilsTest {

  @Nested
  @DisplayName("is balanced")
  class IsBalanced {

    @Test
    @DisplayName("should return true if brackets are empty")
    void shouldReturnTrueIfEmptyBrackets() {
      String empty = "";
      assertThat(StackUtils.areBalanced(empty)).isTrue();
    }

    @Test
    @DisplayName("should return true if brackets are balanced")
    void shouldReturnTrueIfBalancedBrackets() {
      String[] array = new String[]{"()", "((()))", "()()()", "(()())", "(()())(()())", "abrac~adabra"};
      for (String brackets: array) {
        assertThat(StackUtils.areBalanced(brackets)).isTrue();
      }

    }

    @Test
    @DisplayName("should return false if brackets are not balanced")
    void shouldReturnFalseIfNotBalancedBrackets() {
      String[] array = new String[]{"(()(())))", "(()(()))(", "((((((", "))))))", "(", ")", "(()", "())", "))(("};
      for (String brackets: array) {
        assertThat(StackUtils.areBalanced(brackets)).isFalse();
      }
    }
  }

}