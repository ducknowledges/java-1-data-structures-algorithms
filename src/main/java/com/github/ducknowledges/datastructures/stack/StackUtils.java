package com.github.ducknowledges.datastructures.stack;

public class StackUtils {

  private StackUtils() {
  }

  public static boolean areBalanced(String roundedBrackets) {
    if (roundedBrackets.isEmpty()) return true;
    Stack<String> stack = new Stack<>();
    for (String bracket: roundedBrackets.split("")) {
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

  private static boolean isOpenBracket(String bracket) {
    return bracket.equals("(");
  }

  private static boolean isClosedBracket(String bracket) {
    return bracket.equals(")");
  }

  public static Integer calculate(String postfixExpressionString) {
    Stack<String> expressionStack = getPostfixExpression(postfixExpressionString);
    Stack<Integer> resultStack = new Stack<>();
    while (!expressionStack.isEmpty()) {
      String element = expressionStack.pop();
      if(element.equals("=")) break;
      if(element.equals("+")) {
        sumStackElementsOnHead(resultStack);
      } else if(element.equals("*")) {
        multiplyStackElementsOnHead(resultStack);
      } else {
        resultStack.push(Integer.parseInt(element));
      }
    }
    return resultStack.peek();
  }

  private static Stack<String> getPostfixExpression(String postfixExpressionString) {
    String[] array = postfixExpressionString.split(" ");
    Stack<String> stack = new Stack<>();
    for(int i = array.length - 1; i >= 0; i--) {
      stack.push(array[i]);
    }
    return stack;
  }

  private static void sumStackElementsOnHead(Stack<Integer> stack) {
    int result = stack.pop();
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    stack.push(result);
  }

  private static void multiplyStackElementsOnHead(Stack<Integer> stack) {
    int result = stack.pop();
    while (!stack.isEmpty()) {
      result *= stack.pop();
    }
    stack.push(result);
  }

}
