package org.example.hw3.entity;

import lombok.Data;


@Data
//@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private  String name;

  public Reader(String name) {
    this();
    this.name = name;
  }

  public Reader() {
    this.id = sequence++;
  }

}
