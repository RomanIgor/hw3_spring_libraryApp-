package org.example.hw3.entity;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "issuerequest")
public class IssueRequest implements Serializable {

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
