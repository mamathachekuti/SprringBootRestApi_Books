package BookRestApp.unittest;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

import org.json.JSONArray;

import org.junit.jupiter.api.Test;

public class BookAppUnitTest {

	@Test
	public void TestGetAllBooks() throws Exception {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/books/")).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		JSONArray jsonArray = new JSONArray(response.body());

		assertTrue(jsonArray.length() >= 1);
	}

	@Test
	public void TestGetAllByIsbn() throws Exception {
		String isbnText = "LNK";
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/books/isbn/?isbn=" + isbnText)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		JSONArray jsonArray = new JSONArray(response.body());
		assertTrue("Should get more then one result",jsonArray.length() >= 1);
	

	}

	@Test
	public void TestAddBook() throws Exception {
		Path tempFile = Files.createTempFile(null, null);
		// Writes a string to the above temporary file
		String JsonString = "  {\r\n" + "    \"isbn\": \"LNK\",\r\n" + "    \"title\": \"Sawant\",\r\n"
				+ "    \"author\": \"ravi@gmail.com\",\r\n" + "    \"date_published\": \"2021-10-07\",\r\n"
				+ "		\"rating\": 10\r\n" + "  }";
		Files.write(tempFile, JsonString.getBytes(StandardCharsets.UTF_8));

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/books/"))
				.header("Content-Type", "application/json").POST(BodyPublishers.ofFile(tempFile)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		assertTrue(new String(response.body()).equals("SuccessFully Added"));

	}
}
