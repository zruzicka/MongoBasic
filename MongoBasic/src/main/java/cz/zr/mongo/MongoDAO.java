package cz.zr.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {

	private final String connectionString;

	/**
	 * @param connectionString
	 */
	public MongoDAO(String connectionString) {
		this.connectionString = connectionString;
	}

	/**
	 * This method <fill in description>
	 * @return
	 */
	private MongoClient initMongoClient() {
		if (connectionString == null || connectionString.isEmpty()) {
			throw new IllegalArgumentException();
		}
		MongoClientURI clientUri = new MongoClientURI(connectionString);
		return new MongoClient(clientUri);
	}

	/**
	 *	This method <fill in description>
	 *
	 */
	private MongoCollection<Document> prepareCollection(MongoClient client) {
		MongoDatabase database = client.getDatabase("db01");
		return database.getCollection("collection01");
	}

	public int countRecords() {
		return getRecords().size();
	}

	public void insert(Document document) {
		try (MongoClient client = initMongoClient()) {
			MongoCollection<Document> collection = prepareCollection(client);
			collection.insertOne(document);
		} catch (Exception e) {
			// FIXME: log the exception
			e.printStackTrace();
		}
	}

	public List<Document> getRecords() {
		try (MongoClient client = initMongoClient()) {
			MongoCollection<Document> collection = prepareCollection(client);
			return collection.find().into(new ArrayList<Document>());
		} catch (Exception e) {
			// FIXME: log the exception
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
