package com.zuhlke.library.api;

import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.zuhlke.library.core.Book;
import com.zuhlke.library.core.User;
import com.zuhlke.library.core.UserRole;
import com.zuhlke.library.dao.BookDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

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
	        return dao.findByTitleOrAuthor(query);
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
    public void saveBook(@Auth User user, Book book) {
        if (user.getRole() != UserRole.ADMINISTRATOR) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        dao.save(book);
    }

    @DELETE @Path("/{id}") 
    @UnitOfWork
    public void deleteBook(@Auth User user, @PathParam("id") Long id) {
        if (user.getRole() != UserRole.ADMINISTRATOR) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        dao.delete(id);
    }

}

