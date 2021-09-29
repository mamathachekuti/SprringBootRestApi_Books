package BookRestApp.controller.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import BookRestApp.connection.Connection;
import BookRestApp.domain.Book;



@Repository
public class BookRepository {

	@Value("${dbname}")
	private String dbname;

	@Value("${collectionname}")
	private String collection_name;

	@Autowired
	Connection connection;

	public List<Book> getAll() {
		MongoCollection<Document> bookCollection = Connection.getDBConnected(dbname, collection_name);
		List<Book> response = new ArrayList<Book>();
		FindIterable<Document> findalldoc = bookCollection.find();
		for (Document doc : findalldoc) {
			System.out.println(doc.get("_id").toString()+" "+doc.get("isbn").toString()+" "+ doc.get("title").toString()+" "+ doc.get("author").toString()+" "+ doc.getDate("date_published")+" "+doc.getInteger("rating"));
			Book book = new Book(doc.get("_id").toString(),doc.get("isbn").toString(), doc.get("title").toString(), doc.get("author").toString(), doc.getDate("date_published"),doc.getInteger("rating"));
			response.add(book);
		}
		return response;
	}

	public String save(Book book) {
		MongoCollection<Document> bookCollection = Connection.getDBConnected(dbname, collection_name);


		Document doc1 = new Document();
		doc1.append("isbn", book.getIsbn());
		doc1.append("title", book.getTitle());
		doc1.append("author", book.getAuthor());
		doc1.append("date_published", book.getDate_published());
		doc1.append("rating", book.getRating());


		String response;
		response = ValidateInput(doc1);
		if (response.equals("No Errors")) {
		try {
			bookCollection.insertOne(doc1);
			response = "SuccessFully Added";
		} catch (Exception e) {
			response = "Something went wrong during Insert";
		}
		}
		return response;
	}

private String ValidateInput(Document doc1) {
	 String response_str = "No Errors";
	 for (Entry<String, Object> entry : doc1.entrySet()) {
		 if (entry.getKey().equals("isbn")){
			 if  (entry.getValue() == null || new String((String)entry.getValue() ).equals("") || !new String((String) entry.getValue()).matches("[A-Za-z0-9]+")) {
				 response_str = "Value of isbn is incorrect";
				 break;
			 }
		 }
		 if (entry.getKey().equals("title")){
			 if  (entry.getValue() == null || new String((String)entry.getValue() ).equals("") || !new String((String) entry.getValue()).matches("[A-Za-z0-9]+")) {
				 response_str = "Value of Title  is incorrect";
				 break;
			 }
		 }
		 if (entry.getKey().equals("date_published")){
			 if  (entry.getValue() == null ) {
				 response_str = "Value of date_published  is incorrect";
				 break;
			 }
		 }
		 if (entry.getKey().equals("rating")){
			 if  (entry.getValue() != null) {
				 try {
					 Integer xyz = new Integer((int) entry.getValue());
					 System.out.println("XYZ "+ xyz);
				 }catch(Exception e) {

				 response_str = "Value of rating  is incorrect";
				 break;
				 }
			 }
		 }
//         System.out.println("Key = " + entry.getKey() +
//                          ", Value = " +entry.getValue());
	}

		return response_str;

}

	public List<Book> getAllByIsbn(String isbn) {

		MongoCollection<Document> bookCollection = Connection.getDBConnected(dbname, collection_name);
		List<Book> response = new ArrayList<Book>();
		BasicDBObject filter = new BasicDBObject("isbn",isbn);
		System.out.println(" isbn ----> "+isbn);
		FindIterable<Document> findalldoc = bookCollection.find(filter);
		for (Document doc : findalldoc) {
			Book book = new Book(doc.get("_id").toString(),doc.get("isbn").toString(), doc.get("title").toString(), doc.get("author").toString(), doc.getDate("date_published"),doc.getInteger("rating"));
			response.add(book);
		}
		return response;
	}
}
