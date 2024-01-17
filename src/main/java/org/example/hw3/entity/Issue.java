package org.example.hw3.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
public class Issue {

  public static long sequence = 1L;

  private final long id;
  private final long bookId;
  private final long readerId;

  private  LocalDateTime issued_at;
  private  LocalDateTime returned_at;

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
    this.returned_at=null;
  }
}
