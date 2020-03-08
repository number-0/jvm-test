package com.shl.jvm.test.domain;

/**
 * @author songhengliang
 * @date 2020/3/8
 */
public class Person {

  public Person(Integer age, String name) {
    this.age = age;
    this.name = name;
  }

  private Integer age;

  private String name;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
