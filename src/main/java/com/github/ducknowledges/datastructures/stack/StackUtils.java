package com.github.ducknowledges.datastructures.stack;

import java.util.function.DoubleBinaryOperator;

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
      case "+" -> getResult(StackUtils::sum, accumulator);
      case "-" -> getResult(StackUtils::subtract, accumulator);
      case "*" -> getResult(StackUtils::multiply, accumulator);
      case "/" -> getResult(StackUtils::divide, accumulator);
      default -> Double.parseDouble(element);
    };
    accumulator.push(result);
  }

  private static double getResult(DoubleBinaryOperator function, Stack<Double> accumulator) {
    double firstOperand = accumulator.pop();
    double secondOperand = accumulator.pop();
    return function.applyAsDouble(firstOperand, secondOperand);
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
