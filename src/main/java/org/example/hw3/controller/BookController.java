package org.example.hw3.controller;

import org.example.hw3.entity.Book;
import org.example.hw3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/all")
    public List<Book> allBook() {
        return bookService.allBooks();
    }

    @DeleteMapping("/{id}")
    public List<Book> deleteById(@PathVariable("id") long id) {
        return bookService.deleteBook(id);
    }


    @PostMapping
    public List<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
