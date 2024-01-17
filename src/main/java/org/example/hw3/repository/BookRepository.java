package org.example.hw3.repository;

import org.example.hw3.entity.Book;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

  private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
        books.add(new Book("clean code"));
        books.add(new Book("book 2"));
        books.add(new Book("book 3"));

    }

    public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public List<Book> getAllBooks(){
    return List.copyOf(books);
  }

  public List<Book> deleteBookById(long id){

    books.removeIf(book -> book.getId()==id);
    return books;

  }

  public List<Book>addBook(Book book){
    books.add(book);
    return books;
  }

}
