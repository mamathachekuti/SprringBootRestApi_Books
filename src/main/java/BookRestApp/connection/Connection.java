package BookRestApp.connection;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class Connection {
	
	static com.mongodb.MongoClient mongoClient;
	
	@Value("${host}")
	private static String host;
	
	@Value("${port}")
	private static String mongo_port;
	
	@SuppressWarnings("deprecation")
	public static com.mongodb.MongoClient getClient() {
		if (mongoClient == null) {
			if (mongo_port == null) {
				mongo_port = "27017";
			}
			mongoClient = new com.mongodb.MongoClient(host,new Integer(mongo_port));
		}
		return mongoClient;
	}

	public static MongoCollection<Document> getDBConnected(String dbName, String CollectionName) {
		com.mongodb.MongoClient mongoClient = getClient();
		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<org.bson.Document> custCollection = database.getCollection(CollectionName);
		return custCollection;
	}
}
