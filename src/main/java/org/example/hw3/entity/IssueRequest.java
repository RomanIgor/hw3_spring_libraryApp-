package org.example.hw3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "issuerequest")
public class IssueRequest {

  @Id
  private long readerId;
  @Id
  private long bookId;

  public IssueRequest(long readerId, long bookId) {
    this.readerId = readerId;
    this.bookId = bookId;
  }

  public IssueRequest() {
  }
}
