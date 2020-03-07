package com.shl.jvm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.junit.Test;

/**
 * @author songhengliang
 * @date 2020/3/7
 */
public class HeapOOMTest {

  @Test
  public void oomTest() {
    //-Xms1m -Xmx1m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC

    List<Byte[]> list = new ArrayList();

    //最大堆内存才1m，需要5m的内存，所以oom了
    for(int i=0; i < 5; i ++){
      list.add(new Byte[1*1024*1024]);
    }
  }

}
