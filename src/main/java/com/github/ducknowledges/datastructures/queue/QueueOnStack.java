package com.github.ducknowledges.datastructures.queue;

import com.github.ducknowledges.datastructures.stack.Stack;

public class QueueOnStack<T> {

  private final Stack<T> enqueueStack;
  private final Stack<T> dequeueStack;

  public QueueOnStack() {
    this.enqueueStack = new Stack<>();
    this.dequeueStack = new Stack<>();
  }

  public void enqueue(T item) {
    enqueueStack.push(item);
  }

  public T dequeue() {
    if (enqueueStack.isEmpty() && dequeueStack.isEmpty()) return null;
    if (dequeueStack.isEmpty()) {
      while (!enqueueStack.isEmpty()) {
        T element = enqueueStack.pop();
        dequeueStack.push(element);
      }
    }
    return dequeueStack.pop();
  }

  public int size() {
    return enqueueStack.size() + dequeueStack.size();
  }

  public boolean isEmpty() {
    return enqueueStack.isEmpty() && dequeueStack.isEmpty();
  }

  public Object[] toArray() {
    int length = enqueueStack.size() + dequeueStack.size();
    Object[] array = new Object[length];
    Object[] dequeueArray = dequeueStack.toArray();
    Object[] enqueueArray = enqueueStack.toArray();
    int counter = 0;
    for (Object object: dequeueArray) {
      array[counter] = object;
      counter++;
    }
    counter = length - 1;
    for (Object object: enqueueArray) {
      array[counter] = object;
      counter--;
    }
    return array;
  }

}
