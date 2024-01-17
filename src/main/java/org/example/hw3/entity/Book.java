package org.example.hw3.entity;


import lombok.Data;

@Data

public class Book {

  private static long sequence = 1L;
  private long id;
  private  String name;

    public Book(String name) {
        this();
        this.name = name;
    }
    public Book() {
        this.id = sequence++;
    }

}
