package com.github.ducknowledges.datastructures.deque;

public class DequeUtils {
  private DequeUtils() {
  }

  public static boolean isPalindrome(String str) {
    Deque<Character> deque = getDeque(str);
    while (deque.size() > 1) {
      Character first = deque.removeFront();
      Character last = deque.removeTail();
      if (!first.equals(last)) {
        return false;
      }
    }
    return true;
  }

  private static Deque<Character> getDeque(String str) {
    Deque<Character> deque = new Deque<>();
    for (Character c: str.toCharArray()) {
      deque.addTail(c);
    }
    return deque;
  }
}
