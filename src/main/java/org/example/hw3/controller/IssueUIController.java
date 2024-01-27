package org.example.hw3.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Issue")
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

    @Operation(summary = "Show issue form", description = "Return a html page with a simple form that contains all  Books and Readers")
    @GetMapping("/add")
    public String showAddIssueForm(Model model) {
        List<Book> availableBooks = bookService.getAllBooks();
        List<Reader> availableReaders = readerService.getAllReaders();

        model.addAttribute("issueRequest", new IssueRequest());
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("availableReaders", availableReaders);
        return "addIssue";
    }

    @Operation(summary = "Add a new issue")
    @PostMapping("/add")
    public String addIssue(@ModelAttribute IssueRequest issueRequest) {
        issueService.addNewIssue(issueRequest);
        return "redirect:/ui/issue/all";
    }


    @Operation(summary = "Show all issues")
    @GetMapping("/all")
    public String showAllIssues(Model model) {
        List<Issue> allIssues = issueService.getAllIssues();

        model.addAttribute("allIssues", allIssues);

        return "issues";
    }



    @Operation(summary = "Show reader issues form")
    @GetMapping("/issue")
    public String showReaderIssuesForm(Model model) {
        List<Reader> availableReaders = readerService.getAllReaders();
        model.addAttribute("availableReaders", availableReaders);
        model.addAttribute("issueRequest", new IssueRequest());

        return "selectReaderForIssues";
    }

    @Operation(summary = "Show reader issues")
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

