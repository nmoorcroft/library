package com.zuhlke.library.api;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.zuhlke.library.core.Book;
import com.zuhlke.library.dao.BookDAO;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	final Logger logger = LoggerFactory.getLogger(BookResource.class);
	
	private BookDAO dao;
	
	public BookResource(BookDAO dao) {
	    this.dao = dao;
    }
	
	@GET 
	@UnitOfWork
	@CacheControl(noCache = true, mustRevalidate = true)
	public List<Book> getBooks(@QueryParam("q") String query) {
	    if (isNullOrEmpty(query)) {
	        return dao.findAll();
	    } else {
	        return dao.findByTitle(query);
	    }
	}
	
	@GET @Path("/{id}") 
	@UnitOfWork
    @CacheControl(noCache = true, mustRevalidate = true)
    public Book getBook(@PathParam("id") Long id) {
        Book book = dao.findById(id);
        if (book != null) return book;
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
	
    @POST 
    @UnitOfWork 
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveBook(Book book) {
        dao.save(book);
    }

    @DELETE @Path("/{id}") 
    @UnitOfWork
    public void deleteBook(@PathParam("id") Long id) {
        dao.delete(id);
    }

}

