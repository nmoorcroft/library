package com.zuhlke.library.core;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 5541659969794633170L;

	@Id 
	@GeneratedValue(generator = "book_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "book_id", pkColumnValue = "book")
	@Column(name = "book_id")
	private Long id;
	
	@Basic(optional = false)
	@Length(max = 500)
	@Column(name = "title")
	private String title;
	
	@Basic(optional = true) 
	@Length(max = 2000)
	@Column(name = "description")
	private String description;

	@Basic(optional = false) 
	@Length(max = 255)
	@Column(name = "author")
	private String author;
	
    @Basic(optional = true) 
    @Length(max = 255)
    @Column(name = "artwork")
	private String artwork;
	
	Book() { }
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
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
	
	public String getArtwork() {
        return artwork;
    }
	
	public void setArtwork(String artwork) {
        this.artwork = artwork;
    }
	
	@Override
	public String toString() {
		return String.format("id: %s; title: %s; description: %s; artwork: %s", getId(), getTitle(), getDescription(), getArtwork());
	}
	

}
