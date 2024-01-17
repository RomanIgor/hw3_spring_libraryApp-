package org.example.hw3.entity;


import lombok.Data;
import lombok.Getter;

/**
 * Запрос на выдачу
 */

@Data
public class IssueRequest {

  public IssueRequest(long readerId, long bookId) {
    this.readerId = readerId;
    this.bookId = bookId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  /**
   * Идентификатор читателя
   */
  private long readerId;

  /**
   * Идентификатор книги
   */
  private long bookId;

}
