package com.shl.jvm.test;

import org.junit.Test;

/**
 * @author songhengliang
 * @date 2020/3/7
 */
public class StackOOMTest {

  //栈调用深度
  private static int count;

  public static void recursion() {
    count++;
    recursion();
  }


  /**
   * -Xss：指定单个线程的最大栈空间
   */
  @Test
  public void stackOOMTest() {
    //-Xss1m

    try {
      recursion();
    } catch (Throwable t) {
      System.out.println("调用最大深入：" + count);
      t.printStackTrace();
    }
  }


}
