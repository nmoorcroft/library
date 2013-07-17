package com.zuhlke.library.book;

import java.util.List;

import com.zuhlke.library.domain.Book;

public interface BookService {

    Book getBook(long id);
    List<Book> findBooks(String query);
    void saveBook(Book book);
    void deleteBook(long id);
    
}
