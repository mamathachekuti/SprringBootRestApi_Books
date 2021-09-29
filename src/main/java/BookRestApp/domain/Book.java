package BookRestApp.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Book {
	
	
	public Book() {
		super();
	}
	private String id;
	@NotBlank(message = "Isbn cannot be Blank")
	@NotNull(message = "Isbn cannot be Null")
	private String isbn ;
	@NotBlank(message = "Title cannot be Blank")
	@NotNull(message = "Title cannot be Null")
	private String title ;
	@NotBlank(message = "Author cannot be Blank")
	@NotNull(message = "Author cannot be Null")
	private String author ;
	@DateTimeFormat()
	@NotBlank(message = "Author cannot be Blank")
	@NotNull(message = "Author cannot be Null")
	private Date date_published;
	@NotBlank
	@NumberFormat
	private Integer rating ;
	
	public Book(String id, String isbn, String title, String author, Date date_published, Integer rating) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.date_published = date_published;
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate_published() {
		return date_published;
	}

	public void setDate_published(Date date_published) {
		this.date_published = date_published;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
