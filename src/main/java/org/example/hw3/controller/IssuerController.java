package org.example.hw3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.hw3.entity.Issue;
import org.example.hw3.entity.IssueRequest;
import org.example.hw3.service.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  private final IssuerService Issueservice;


  @Autowired
  public IssuerController(IssuerService Issueservice) {
    this.Issueservice = Issueservice;
  }

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = Issueservice.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @GetMapping("/{id}")
  public Issue getIssueById(@PathVariable("id") long id) {
    return Issueservice.getIssueById(id);
  }


  @GetMapping("/all")
  public List<Issue> allIssues() {
    return Issueservice.allIssues();
  }

  @PutMapping("/{issueId}")
  public Issue updateIssue(@PathVariable long issueId){
    return  Issueservice.updateIssue(issueId);
  }

}
