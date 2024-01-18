package org.example.hw3.service;



import org.example.hw3.controller.IssueRequest;
import org.example.hw3.entity.Issue;
import org.example.hw3.repository.BookRepository;
import org.example.hw3.repository.IssueRepository;
import org.example.hw3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IssuerService {

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  @Autowired
  public IssuerService(BookRepository bookRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
    this.bookRepository = bookRepository;
    this.readerRepository = readerRepository;
    this.issueRepository = issueRepository;
  }

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }


    if (issueRepository.hasBooksIssued(request.getReaderId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Читатель уже имеет выданные книги");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public Issue getIssueById(long id){
    return issueRepository.getIssueById(id);
  }

  public List<Issue>allIssues(){
    return issueRepository.getAllIssues();
  }


  public List<Issue> getIssuesByReaderId(long readerId) {
    return issueRepository.getIssuesByReaderId(readerId);
  }

  public Issue updateIssue(long issueId) {
    return issueRepository.getUpdateIssue(issueId);
  }
}
