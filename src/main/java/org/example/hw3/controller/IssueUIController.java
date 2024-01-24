package org.example.hw3.controller;
import org.example.hw3.entity.Book;
import org.example.hw3.entity.Issue;
import org.example.hw3.entity.IssueRequest;
import org.example.hw3.entity.Reader;
import org.example.hw3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ui/issue")
public class IssueUIController {

    private final IssueService issueService;

    private final BookService bookService;
    private final ReaderService readerService;

    @Autowired
    public IssueUIController(IssueService issueService,  BookService bookService, ReaderService readerService) {
        this.issueService = issueService;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @GetMapping("/add")
    public String showAddIssueForm(Model model) {
        List<Book> availableBooks = bookService.getAllBooks();
        List<Reader> availableReaders = readerService.getAllReaders();

        model.addAttribute("issueRequest", new IssueRequest());
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("availableReaders", availableReaders);
        return "addIssue";
    }

    @PostMapping("/add")
    public String addIssue(@ModelAttribute IssueRequest issueRequest) {
        issueService.addNewIssue(issueRequest);
        return "redirect:/ui/issue/all";
    }


    @GetMapping("/all")
    public String showAllIssues(Model model) {
        List<Issue> allIssues = issueService.getAllIssues();

        model.addAttribute("allIssues", allIssues);

        return "issues";
    }


    @GetMapping("/issue")
    public String showReaderIssuesForm(Model model) {
        List<Reader> availableReaders = readerService.getAllReaders();
        model.addAttribute("availableReaders", availableReaders);
        model.addAttribute("issueRequest", new IssueRequest());

        return "selectReaderForIssues";
    }

    @PostMapping("/issue")
    public String showReaderIssues(@ModelAttribute IssueRequest issueRequest, Model model) {
        long readerId = issueRequest.getReaderId();

        Optional<Reader> readerOptional = readerService.getReaderById(readerId);

        if (!readerOptional.isPresent()) {
            return "redirect:/error";
        }

        Reader reader = readerOptional.get();
        List<Issue> issues = issueService.getIssuesByReaderId(readerId);

        model.addAttribute("reader", reader);
        model.addAttribute("issues", issues);

        return "readerIssues";
    }



}

