package com.zuhlke.library.api;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.zuhlke.library.core.Book;
import com.zuhlke.library.core.BookBuilder;
import com.zuhlke.library.core.User;
import com.zuhlke.library.core.UserBuilder;
import com.zuhlke.library.core.UserRole;
import com.zuhlke.library.dao.BookDAO;

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
        when(mockDao.findByTitleOrAuthor("title")).thenReturn(books);
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
        User user = new UserBuilder().role(UserRole.ADMINISTRATOR).build();
        
        // act
        resource.saveBook(user, book);
        
        // assert
        verify(mockDao).save(book);
    }
    
    @Test
    public void shouldNotBeAuthorizedToSave() throws Exception {
        // arrange
        BookResource resource = new BookResource(mockDao);
        User user = new UserBuilder().role(UserRole.USER).build();
        
        // act
        WebApplicationException exception = null;
        try {
            resource.saveBook(user, book);

        } catch (WebApplicationException e) {
            exception = e;
        }
        
        // assert
        verifyZeroInteractions(mockDao);
        assertNotNull(exception);
        assertEquals(exception.getResponse().getStatus(), 401);
    
    }
    
    @Test
    public void shouldDeleteBook() throws Exception {
        // arrange 
        BookResource resource = new BookResource(mockDao);
        User user = new UserBuilder().role(UserRole.ADMINISTRATOR).build();
        
        // act 
        resource.deleteBook(user, 1L);
        
        // assert
        verify(mockDao).delete(1L);

    }
    
    @Test
    public void shouldNotBeAuthorizedToDelete() throws Exception {
        // arrange
        BookResource resource = new BookResource(mockDao);
        User user = new UserBuilder().role(UserRole.USER).build();
        
        // act
        WebApplicationException exception = null;
        try {
            resource.deleteBook(user, 1L);

        } catch (WebApplicationException e) {
            exception = e;
        }
        
        // assert
        verifyZeroInteractions(mockDao);
        assertNotNull(exception);
        assertEquals(exception.getResponse().getStatus(), 401);
    
    }
    

}
