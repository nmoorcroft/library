package com.zuhlke.library.book;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.zuhlke.library.domain.Book;
import com.zuhlke.library.domain.BookBuilder;
import com.zuhlke.library.domain.User;
import com.zuhlke.library.domain.UserBuilder;
import com.zuhlke.library.domain.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {

    final List<Book> books = Arrays.asList(
        new BookBuilder().id(1L).title("book1").build(),
        new BookBuilder().id(2L).title("book2").build(),
        new BookBuilder().id(3L).title("book3").build()
    );
    
    final Book book = new BookBuilder().id(1L).title("book title").build();

    @Mock BookService mockBookService;
    @InjectMocks BookResource resource = new BookResource();
    
    @Test
    public void shouldGetAllBooks() throws Exception {
        when(mockBookService.findBooks(anyString())).thenReturn(books);
        List<Book> result = resource.getBooks(null);
        assertThat(books, is(result));
    }
    
    @Test
    public void shouldGetBooksByQuery() throws Exception {
        when(mockBookService.findBooks("title")).thenReturn(books);
        List<Book> result = resource.getBooks("title");
        assertThat(books, is(result));
    }
    
    @Test
    public void shouldGetBookById() throws Exception {
        when(mockBookService.getBook(1L)).thenReturn(book);
        Book result = resource.getBook(1L);
        assertThat(book, is(result));
    }
    
    @Test
    public void shouldSaveBook() throws Exception {
        User user = new UserBuilder().role(UserRole.ADMINISTRATOR).build();
        resource.saveBook(user, book);
        verify(mockBookService).saveBook(book);
    }
    
    @Test
    public void shouldNotBeAuthorizedToSave() throws Exception {
        User user = new UserBuilder().role(UserRole.USER).build();
        WebApplicationException exception = null;
        try {
            resource.saveBook(user, book);

        } catch (WebApplicationException e) {
            exception = e;
        }
        
        verify(mockBookService, never()).saveBook(book);
        assertNotNull(exception);
        assertEquals(exception.getResponse().getStatus(), 401);
    
    }
    
    @Test
    public void shouldDeleteBook() throws Exception {
        User user = new UserBuilder().role(UserRole.ADMINISTRATOR).build();
        resource.deleteBook(user, 1L);
        verify(mockBookService).deleteBook(1L);
    }
    
    @Test
    public void shouldNotBeAuthorizedToDelete() throws Exception {
        User user = new UserBuilder().role(UserRole.USER).build();
        WebApplicationException exception = null;
        try {
            resource.deleteBook(user, 1L);

        } catch (WebApplicationException e) {
            exception = e;
        }
        
        verify(mockBookService, never()).deleteBook(1L);
        assertNotNull(exception);
        assertEquals(exception.getResponse().getStatus(), 401);
    
    }
    

}

