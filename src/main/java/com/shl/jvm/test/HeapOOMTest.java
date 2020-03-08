package com.shl.jvm.test;

import com.shl.jvm.test.domain.Person;
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
  public void oomTest1() {
    //-Xms1m -Xmx20m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/workoffice/java/workspace-shl/jvm-test/heap.dump -Xloggc:/Users/workoffice/java/workspace-shl/jvm-test/gc.log -XX:+UseGCLogFileRotation -XX:GCLogFileSize=20M -XX:NumberOfGCLogFiles=12


    List<Byte[]> list = new ArrayList();

    //最大堆内存才1m，需要5m的内存，所以oom了
    for(int i=0; i < 40; i ++){
      list.add(new Byte[1*1024*1024]);
    }
  }
}
