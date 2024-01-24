//package org.example.hw3.controller;
//
//
//
//import org.example.hw3.entity.Book;
//import org.example.hw3.entity.Issue;
//import org.example.hw3.entity.Reader;
//import org.example.hw3.service.BookService;
//import org.example.hw3.service.IssueService;
//import org.example.hw3.service.ReaderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class UiController {
//
//    private BookService bookService;
//    private ReaderService readerService;
//    private IssueService issueService;
//
//    @Autowired
//    public UiController(BookService bookService, ReaderService readerService, IssueService issueService) {
//        this.bookService = bookService;
//        this.readerService = readerService;
//        this.issueService = issueService;
//    }
//
//    @GetMapping("/ui/books")
//    public String getBooks(Model model) {
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("books", books);
//        return "books";
//    }
//
//    @GetMapping("/ui/readers")
//    public String getReaders(Model model) {
//        List<Reader> readers = readerService.getAllReaders();
//        model.addAttribute("readers", readers);
//        return "readers";
//    }
//
//    @GetMapping("/ui/issues")
//    public String getIssues(Model model) {
//        List<Issue> issues = issueService.getAllIssues();
//        model.addAttribute("issues", issues);
//        return "issues";
//    }
//
//    @GetMapping("/ui/reader/{id}")
//    public String getReaderDetails(@PathVariable long id, Model model) {
//        Optional<Reader> reader = readerService.getReaderById(id);
//        if (reader == null) {
//            return "redirect:/ui/readers"; // Redirect to the readers' list page
//        }
//
//        List<Issue> issues = issueService.getIssuesByReaderId(id);
//        model.addAttribute("reader", reader);
//        model.addAttribute("issues", issues);
//        return "readerDetails";
//    }
//
//}
