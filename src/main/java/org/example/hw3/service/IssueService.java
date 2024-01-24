package org.example.hw3.service;

import org.example.hw3.entity.IssueRequest;
import org.example.hw3.entity.Issue;
import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue addNewIssue(IssueRequest issueRequest);

    Optional<Issue> getIssueById(Long id);

    List<Issue>getAllIssues();

    List<Issue> getIssuesByReaderId(long readerId);

    Issue updateIssue(long issueId);





}
