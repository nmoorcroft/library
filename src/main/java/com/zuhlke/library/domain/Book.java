package com.zuhlke.library.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.google.common.base.Objects;

@Entity @Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable {

	private static final long serialVersionUID = 5541659969794633170L;

	@Id 
	@GeneratedValue(generator = "book_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "book_id", pkColumnValue = "book")
	@Column(name = "book_id")
	private Long id;
	
	@Column(name = "title")
	@Length(max = 500)
	@Basic(optional = false)
	private String title;
	
	@Column(name = "description")
	@Length(max = 2000)
	@Basic(optional = true) 
	private String description;

	@Column(name = "author")
	@Length(max = 255)
	@Basic(optional = false) 
	private String author;
	
	@Column(name = "artwork")
    @Length(max = 255)
	@Basic(optional = true) 
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
		return Objects.toStringHelper(this).toString();
	}
	

}
