package BookRestApp.controller.service;

import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import BookRestApp.controller.repository.BookRepository;
import BookRestApp.domain.Book;


@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public List<Book> getAll() {

		return bookRepository.getAll();
	}

	public String addBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> getAllByIsbn(String isbn) {
		return bookRepository.getAllByIsbn(isbn);
	}

}
