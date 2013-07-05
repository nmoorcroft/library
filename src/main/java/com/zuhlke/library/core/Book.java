package com.zuhlke.library.core;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity @Table(name = "books")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5541659969794633170L;

	@Id 
	@GeneratedValue(generator = "book_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "book_id", pkColumnValue = "book")
	@Column(name = "book_id")
	private Long id;
	
	@Basic(optional = false) 
	@Column(name = "title")
	private String title;
	
	@Basic(optional = true) 
	@Column(name = "description")
	private String description;

	@Basic(optional = false) 
	@Column(name = "author")
	private String author;
	
	Book() { }
	
	public Book(String title, String author, String description) {
		this.title = title;
		this.author = author;
		this.description = description;
	}
	
	public Book(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}
	
	protected void setId(Long id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
        return description;
    }
	
	public void setDescription(String description) {
        this.description = description;
    }
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return String.format("id: %s; title: %s; description: %s", getId(), getTitle(), getDescription());
	}
	

}
