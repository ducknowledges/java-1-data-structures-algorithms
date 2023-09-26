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

  public static Double calculate(String postfixExpressionString) {
    Stack<String> expressionStack = getPostfixExpression(postfixExpressionString);
    Stack<Double> accumulator = new Stack<>();
    while (!expressionStack.isEmpty()) {
      String element = expressionStack.pop();
      if(element.equals("=")) break;
      operateOn(element, accumulator);
    }
    return accumulator.peek();
  }

  private static void operateOn(String element, Stack<Double> accumulator) {
    double result = switch (element) {
      case "+" -> sum(accumulator.pop(), accumulator.pop());
      case "-" -> subtract(accumulator.pop(), accumulator.pop());
      case "*" -> multiply(accumulator.pop(), accumulator.pop());
      case "/" -> divide(accumulator.pop(), accumulator.pop());
      default -> Double.parseDouble(element);
    };
    accumulator.push(result);
  }

  private static double sum(double firstOperand, double secondOperand) {
    return secondOperand + firstOperand;
  }

  private static double subtract(double firstOperand, double secondOperand) {
    return secondOperand - firstOperand;
  }

  private static double multiply(double firstOperand, double secondOperand) {
    return secondOperand * firstOperand;
  }

  private static double divide(double firstOperand, double secondOperand) {
    return secondOperand / firstOperand;
  }

  private static Stack<String> getPostfixExpression(String postfixExpressionString) {
    String[] array = postfixExpressionString.split(" ");
    Stack<String> stack = new Stack<>();
    for(int i = array.length - 1; i >= 0; i--) {
      stack.push(array[i]);
    }
    return stack;
  }

}
