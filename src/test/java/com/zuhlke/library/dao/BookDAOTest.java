package com.zuhlke.library.dao;


import com.zuhlke.library.core.Book;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookDAOTest {

    private static SessionFactory sessionFactory;
    
    @BeforeClass
    public static void setUp() {
        Configuration cfg = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        
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
