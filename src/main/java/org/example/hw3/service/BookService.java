package org.example.hw3.service;

import org.example.hw3.entity.Book;
import org.example.hw3.repository.BookRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public Book getBookById(long id){
        return bookRepository.getBookById(id);
    }


    public List<Book>allBooks(){
        return bookRepository.getAllBooks();
    }

    public List<Book>deleteBook(long id){
        return bookRepository.deleteBookById(id);
    }

    public List<Book>addBook(Book book){
        return bookRepository.addBook(book);
    }
}
