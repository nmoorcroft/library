package com.zuhlke.library.api;

import com.zuhlke.library.core.Book;
import com.zuhlke.library.core.BookBuilder;
import com.zuhlke.library.dao.BookDAO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookResourceTest {

    final List<Book> books = Arrays.asList(
        new BookBuilder().id(1L).title("book1").build(),
        new BookBuilder().id(2L).title("book2").build(),
        new BookBuilder().id(3L).title("book3").build()
    );
    
    final Book book = new BookBuilder().id(1L).title("book title").build();
    final BookDAO mockDao = Mockito.mock(BookDAO.class);
    
    @Test
    public void shouldGetAllBooks() throws Exception {
        // arrange
        when(mockDao.findAll()).thenReturn(books);
        BookResource resource = new BookResource(mockDao);
        
        // act 
        List<Book> result = resource.getBooks(null);
        
        // assert
        Assert.assertThat(books, is(result));
        
    }
    
    @Test
    public void shouldGetBooksByQuery() throws Exception {
        // arrange
        when(mockDao.findByTitle("title")).thenReturn(books);
        BookResource resource = new BookResource(mockDao);
        
        // act 
        List<Book> result = resource.getBooks("title");
        
        // assert
        Assert.assertThat(books, is(result));
        
    }
    
    @Test
    public void shouldGetBookById() throws Exception {
        // arrange
        when(mockDao.findById(1L)).thenReturn(book);
        BookResource resource = new BookResource(mockDao);
        
        // act 
        Book result = resource.getBook(1L);
        
        // assert
        Assert.assertThat(book, is(result));
        
    }
    
    @Test
    public void shouldSaveBook() throws Exception {
        // arrange
        BookResource resource = new BookResource(mockDao);
        
        // act
        resource.saveBook(book);
        
        // assert
        verify(mockDao).save(book);
    }
    
    @Test
    public void shouldDeleteBook() throws Exception {
        // arrange 
        BookResource resource = new BookResource(mockDao);
        
        // act 
        resource.deleteBook(1L);
        
        // assert
        verify(mockDao).delete(1L);

    }
    
}
