package com.github.ducknowledges.datastructures.stack;

public class StackUtils {

  private StackUtils() {
  }

  public static boolean areBalanced(String roundedBrackets) {
    if (roundedBrackets.isEmpty()) return true;
    Stack<Character> stack = new Stack<>();
    for (char bracket: roundedBrackets.toCharArray()) {
      if(isClosedBracket(bracket) && stack.isEmpty()) {
        return false;
      }
      if(isOpenBracket(bracket)) {
        stack.push(bracket);
      }
      if(isClosedBracket(bracket)) {
        stack.pop();
      }
    }
    return stack.isEmpty();
  }

  private static boolean isOpenBracket(char bracket) {
    return bracket == '(';
  }

  private static boolean isClosedBracket(char bracket) {
    return bracket == ')';
  }

}
