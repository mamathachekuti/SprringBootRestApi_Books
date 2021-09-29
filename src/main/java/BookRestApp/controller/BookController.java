package BookRestApp.controller;

import java.util.List;
//import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookRestApp.controller.service.BookService;
import BookRestApp.domain.Book;

@RestController
@RequestMapping("/books/")
public class BookController {

	@Autowired
	BookService service;

	@GetMapping("/")
	public List<Book> getAll(){
		return service.getAll();
	}

	@GetMapping("/isbn/")
	public List<Book> getAllByIsbn(@PathParam("isbn") String isbn){
		return service.getAllByIsbn(isbn);
	}

	@PostMapping("/")
	public String addBook(@RequestBody Book  book){
		return service.addBook(book);
	}

}
