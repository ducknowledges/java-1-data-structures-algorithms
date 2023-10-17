package com.github.ducknowledges.datastructures.deque;

public class DequeUtils {
  private DequeUtils() {
  }

  public static boolean isPalindrome(String str) {
    Deque<Character> deque = getDeque(str);
    while (deque.size() > 1) {
      if (areNotEqualChars(deque.removeFront(), deque.removeTail())) {
        return false;
      }
    }
    return true;
  }

  private static boolean areNotEqualChars(Character ch1, Character ch2) {
      return !ch1.equals(ch2);
  }

  private static Deque<Character> getDeque(String str) {
    Deque<Character> deque = new Deque<>();
    for (Character c: str.toCharArray()) {
      deque.addTail(c);
    }
    return deque;
  }
}
