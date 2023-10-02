package com.github.ducknowledges.datastructures.queue;

public class QueueUtils {

  public static <T> boolean rotate(Queue<T> queue, int count) {
    if (isRotationalQueue(queue) && count != 0) {
      int rotation = calculateRotationCount(queue.size(), count);
      rotateQueue(queue, rotation);
      return true;
    }
    return false;
  }

  private static <T> boolean isRotationalQueue(Queue<T> queue) {
    return queue != null && queue.size() > 1;
  }

  private static int calculateRotationCount(int size, int count) {
    int rotationCount = count % size;
    return rotationCount > 0 ? rotationCount : size + rotationCount;
  }

  private static <T> void rotateQueue(Queue<T> queue, int rotationCount) {
    for (int i = 0; i < rotationCount; i++) {
      T element = queue.dequeue();
      queue.enqueue(element);
    }
  }

}
