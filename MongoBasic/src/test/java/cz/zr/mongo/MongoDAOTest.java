package cz.zr.mongo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

public class MongoDAOTest {

	String connectionString = prepareConnectionString();
	MongoDAO dao = new MongoDAO(connectionString);

	/**
	 * @return  */
	private String prepareConnectionString() {
		String user = System.getProperty("MONGO_USER");
		String pass = System.getProperty("MONGO_PASS");
		Assert.assertNotNull("User cannot be null. ", user);
		Assert.assertNotNull("Pass cannot be null. ", pass);
		return String.format("mongodb://%s:%s@ds253959.mlab.com:53959/db01", user, pass);
	}

	/** */
	@Test
	public void testCountRecords() {
		int size = dao.countRecords();
		System.out.println(size);
		Assert.assertTrue(size > 0);
	}

	/** */
	@Test
	public void testInsert() {
		Map<String, Object> record = new HashMap<>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.put("date", date);
		record.put("localDateFormat", formatter.format(date));
		record.put("class", getClass().getSimpleName());
		Document document = new Document(record);
		System.out.println(document);
		dao.insert(document);;
	}

	/** */
	@Test
	public void testGetRecords() {
		List<Document> records = dao.getRecords();
		System.out.println(records);
		Assert.assertNotNull(records);
	}

}
