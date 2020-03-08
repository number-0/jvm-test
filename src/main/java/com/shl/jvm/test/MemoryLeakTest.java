package com.shl.jvm.test;

import com.shl.jvm.test.domain.Person;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * 内存泄漏
 * @author songhengliang
 * @date 2020/3/8
 */
public class MemoryLeakTest {
  static class TestClass{
    private int id;
    private int[] arr;
    private ThreadLocal<TestClass> threadLocal;
    TestClass(int id){
      this.id = id;
      arr = new int[1000000];
      threadLocal = new ThreadLocal<>();
      threadLocal.set(this);
    }

    public void printId(){
      System.out.println(threadLocal.get().id);
    }
  }

  @Test
  public void memoryLeakTest() {
    //-Xms1m -Xmx20m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/workoffice/java/workspace-shl/jvm-test/heap.hprof -Xloggc:/Users/workoffice/java/workspace-shl/jvm-test/gc.log -XX:+UseGCLogFileRotation -XX:GCLogFileSize=20M -XX:NumberOfGCLogFiles=12
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          TestClass t = new TestClass(i);
          t.printId();
          t = null;
        }
      }
    }).start();
  }
}
