package org.example.hw3.entity;



import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "reader")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "reader")
  private List<Issue> issues;

  public Reader(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  public Reader() {
  }
}
