package com.shl.jvm.test.command;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author songhengliang
 * @date 2020/3/7
 */
// TODO: 2020/3/7
public class JmapView {

  @Test
  public void jmapView() {
    //jmap -heap pid 堆使用情况

    for(int i=0; i < Integer.MAX_VALUE; i ++){
      Byte[] arr = new Byte[1*1024*1024];

      try {
        Thread.sleep(1000l);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
