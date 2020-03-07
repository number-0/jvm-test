package com.shl.jvm.test;

import org.junit.Test;

/**
 * @author songhengliang
 * @date 2020/3/7
 */
public class GcViewTest {
  /*
    -XX:+PrintGC 遇到gc就会打印日志
        [GC (Allocation Failure)  2350K->2249K(5952K), 0.0008615 secs]
        [Full GC (Allocation Failure)  2249K->1867K(5952K), 0.0092248 secs]
        [GC (CMS Initial Mark)  5963K(10116K), 0.0001315 secs]
        [GC (CMS Final Remark)  5997K(10116K), 0.0022743 secs]

    -XX:+PrintGCDetails 遇到gc会打印更详细的日志
        [GC (Allocation Failure) [ParNew: 20K->5K(1088K), 0.0003592 secs][CMS[CMS-concurrent-mark: 0.002/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        [Full GC (Allocation Failure) [CMS: 1824K->1769K(4096K), 0.0062138 secs] 1824K->1769K(5504K), [Metaspace: 4887K->4887K(1056768K)], 0.0062590 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        Heap
         par new generation   total 1856K, used 405K [0x00000007bfa00000, 0x00000007bfc00000, 0x00000007bfc00000)
          eden space 1664K,  24% used [0x00000007bfa00000, 0x00000007bfa65670, 0x00000007bfba0000)
          from space 192K,   0% used [0x00000007bfba0000, 0x00000007bfba0000, 0x00000007bfbd0000)
          to   space 192K,   0% used [0x00000007bfbd0000, 0x00000007bfbd0000, 0x00000007bfc00000)
         concurrent mark-sweep generation total 4096K, used 1769K [0x00000007bfc00000, 0x00000007c0000000, 0x00000007c0000000)
         Metaspace       used 4955K, capacity 5290K, committed 5504K, reserved 1056768K
          class space    used 575K, capacity 594K, committed 640K, reserved 1048576K

    -XX:+PrintCommandLineFlags 打印jvm参数
        -XX:InitialHeapSize=5242880 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=6991872 -XX:MaxTenuringThreshold=6 -XX:OldPLABSize=16 -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseParNewGC

    -XX:+PrintHeapAtGC 打印gc前后堆信息
        {Heap before GC invocations=5 (full 0): //"invocations==5"在gc进行了5次gc；"full 0"在gc之前发生0次full gc
         par new generation   total 1856K, used 1223K [0x00000007bec00000, 0x00000007bee00000, 0x00000007bf2a0000) //新生代总共1856K，使用1223K，剩余633K
          eden space 1664K,  63% used [0x00000007bec00000, 0x00000007bed08460, 0x00000007beda0000) //eden1664K，63%使用
          from space 192K,  86% used [0x00000007bedd0000, 0x00000007bedf9928, 0x00000007bee00000)
          to   space 192K,   0% used [0x00000007beda0000, 0x00000007beda0000, 0x00000007bedd0000)
         concurrent mark-sweep generation total 4096K, used 1124K [0x00000007bf2a0000, 0x00000007bf6a0000, 0x00000007c0000000) //老年代总共4096K，使用1124K，剩余2972K
         Metaspace       used 4890K, capacity 5232K, committed 5504K, reserved 1056768K
          class space    used 567K, capacity 595K, committed 640K, reserved 1048576K
        [GC (Allocation Failure) [ParNew: 1223K->91K(1856K), 0.0008716 secs][CMS: 2198K->1861K(4096K), 0.0090588 secs] 2347K->1861K(5952K), [Metaspace: 4890K->4890K(1056768K)], 0.0100784 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
        Heap after GC invocations=6 (full 1):
         par new generation   total 1920K, used 0K [0x00000007bec00000, 0x00000007bee10000, 0x00000007bf2a0000)
          eden space 1728K,   0% used [0x00000007bec00000, 0x00000007bec00000, 0x00000007bedb0000)
          from space 192K,   0% used [0x00000007bedb0000, 0x00000007bedb0000, 0x00000007bede0000)
          to   space 192K,   0% used [0x00000007bede0000, 0x00000007bede0000, 0x00000007bee10000)
         concurrent mark-sweep generation total 4096K, used 1861K [0x00000007bf2a0000, 0x00000007bf6a0000, 0x00000007c0000000)
         Metaspace       used 4890K, capacity 5232K, committed 5504K, reserved 1056768K
          class space    used 567K, capacity 595K, committed 640K, reserved 1048576K
        }
    -XX:+HeapDumpOnOutOfMemoryError 堆oom时导出整个堆信息
    -XX:HeapDumpPath=d:/Test03.dump 堆oom时导出整个堆信息存放的路径
    -Xloggc:/home/shl/gc.log gc日志路径
    -XX:-UseGCLogFileRotation gc日志滚动
	  -XX:GCLogFileSize=1024K gc日志文件大小
	  -XX:NumberOfGCLogFiles=12 gc日志文件数量
   */

  @Test
  public void test() {
      //-Xms5m -Xmx20m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC

//    //查看GC信息
//    System.out.println();
//    //最大内存
//    System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
//    //空闲内存，最初的时候这个空闲内存，就是初始化内存
//    System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
//    //初始化内存：当内存不够用的时候，初始化内存会变大，但不会超出最大内存
//    System.out.println("total memory:" + Runtime.getRuntime().totalMemory());

    byte[] b1 = new byte[1*1024*1024];  //实例化了一个对象，程序允许的时候，就要分配1m的内存
    System.out.println("分配了1M");


//    System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
//    System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
//    System.out.println("total memory:" + Runtime.getRuntime().totalMemory());

    byte[] b2 = new byte[4*1024*1024];
//    System.out.println("分配了4M");
//    System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
//    System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
//    System.out.println("total memory:" + Runtime.getRuntime().totalMemory());
  }

  @Test
  public void test2() {
//    used 405K [0x00000007bfa00000, 0x00000007bfc00000, 0x00000007bfc00000)
    long a = 0x00000007bfa00000L;
    long b = 0x00000007bfc00000L;
    System.out.println((b - a) / 1024 + "K");
  }
}
