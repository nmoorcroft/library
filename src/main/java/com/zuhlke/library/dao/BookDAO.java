package com.zuhlke.library.dao;

import static org.hibernate.criterion.Restrictions.disjunction;
import static org.hibernate.criterion.Restrictions.ilike;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import com.zuhlke.library.core.Book;

public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Book findById(long id) {
        return get(id);
    }
    
    public long save(Book book) {
        return persist(book).getId();
    }
    
    public void delete(long id) {
        currentSession().delete(get(id));
    }
    
    public List<Book> findAll() {
        return list(criteria().addOrder(Order.asc("title")));
    }
    
    public List<Book> findByTitleOrAuthor(String query) {
        return list(criteria()
                .add(disjunction()
                    .add(ilike("title", query, MatchMode.ANYWHERE))
                    .add(ilike("author", query, MatchMode.ANYWHERE)))
                .addOrder(Order.asc("title")));
    }
    
    
}   


