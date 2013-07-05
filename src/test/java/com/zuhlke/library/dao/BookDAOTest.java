package com.zuhlke.library.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zuhlke.library.core.Book;

public class BookDAOTest {

    private static SessionFactory sessionFactory;
    
    @BeforeClass
    @SuppressWarnings("deprecation")
    public static void setUp() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    @Test
    public void shouldFindById() throws Exception {
        // arrange
        BookDAO dao = new BookDAO(sessionFactory);
        
        // act
        Book book = null;
        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        try {
            book = dao.findById(1L);
        
        } finally {
            tx.commit();
        }
    
        // assert
        assertNotNull(book);
        assertEquals(new Long(1), book.getId());
        
    }
    
    @Test
    public void shouldFindByTitle() throws Exception {
        // arrange
        BookDAO dao = new BookDAO(sessionFactory);
        
        // act
        List<Book> books = null;
        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        try {
            books = dao.findByTitle("java");
            
        } finally {
            tx.commit();
        }
        
        // assert
        assertEquals(1, books.size());
        assertEquals(new Long(3), books.get(0).getId());
        
    }
    
}
