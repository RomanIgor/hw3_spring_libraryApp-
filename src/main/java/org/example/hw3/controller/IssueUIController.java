package org.example.hw3.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Show issue form", description = "Display the form to add a new issue.")
    @GetMapping("/add")
    public String showAddIssueForm(Model model) {
        List<Book> availableBooks = bookService.getAllBooks();
        List<Reader> availableReaders = readerService.getAllReaders();

        model.addAttribute("issueRequest", new IssueRequest());
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("availableReaders", availableReaders);
        return "addIssue";
    }


    @Operation(summary = "Add a new issue", description = "Add a new issue based on the provided request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add")
    public String addIssue(@ModelAttribute IssueRequest issueRequest) {
        issueService.addNewIssue(issueRequest);
        return "redirect:/ui/issue/all";
    }



    @Operation(summary = "Show all issues", description = "Display a html page that contains a table with all issues.")
    @GetMapping("/all")
    public String showAllIssues(Model model) {
        List<Issue> allIssues = issueService.getAllIssues();
        model.addAttribute("allIssues", allIssues);

        return "issues";
    }

    @Operation(summary = "Show reader issues form", description = "Display the form to select a reader for issues.")
    @GetMapping("/issue")
    public String showReaderIssuesForm(Model model) {
        List<Reader> availableReaders = readerService.getAllReaders();
        model.addAttribute("availableReaders", availableReaders);
        model.addAttribute("issueRequest", new IssueRequest());

        return "selectReaderForIssues";
    }

    @Operation(summary = "Show reader issues", description = "Display issues associated with a specific reader.")
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

