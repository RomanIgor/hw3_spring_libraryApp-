package org.example.hw3.repository;


import org.example.hw3.entity.Issue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {
  @Value("${application.max-allowed-books}")
  private int maxAllowedBooks;
  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    issues.add(issue);
  }

  public Issue getIssueById(long id) {
    return issues.stream().filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
  }

  public List<Issue> getAllIssues(){
    return List.copyOf(issues);
  }

  public boolean hasBooksIssued(long readerId) {
    long count = issues.stream().filter(issue -> issue.getReaderId() == readerId).count();
    return count >= maxAllowedBooks;
  }

  public List<Issue> getIssuesByReaderId(long readerId) {
    return issues.stream()
            .filter(issue -> issue.getReaderId() == readerId)
            .collect(Collectors.toList());
  }

  public Issue getUpdateIssue(long issueId) {
    Issue exist = getIssueById(issueId);
    if(exist != null){
      exist.setReturned_at(LocalDateTime.now());
    }return exist;
  }
}
