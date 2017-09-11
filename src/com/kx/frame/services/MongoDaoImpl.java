package com.kx.frame.services;

import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

@Component("mongoDao")
public class MongoDaoImpl implements MongoDao {

	@Autowired
	private MongoFactory mongoFactory;
	
	private MongoCollection<Document> getCollection(String table){
		MongoDatabase database = mongoFactory.getDatabase();
		MongoCollection<Document> doconn = database.getCollection(table);
		return doconn;
	}
	
	@Override
	public Iterator<Document> find(Bson query, String table) {
		return find(query, table,null ,null);
	}
	
	@Override
	public Iterator<Document> find(Bson query,String table, Integer skip,Integer limit){
		FindIterable<Document> it = getCollection(table).find(query);
		if(skip != null){
			it.skip(skip);
		}
		if(limit != null){
			it.limit(limit);
		}
		return it.iterator();
	}
	
	@Override
	public Iterator<Document> findAll( String table) {
		return findAll(table, null, null);
	}
	
	@Override
	public Iterator<Document> findAll(String table, Integer skip,Integer limit ){
		FindIterable<Document> it = getCollection(table).find();
		if(skip != null){
			it.skip(skip);
		}
		if(limit != null){
			it.limit(limit);
		}
		return it.iterator();
	}
	
	@Override
	public Document findOne(Bson query, String table) {
		return getCollection(table).find(query).first();
	}

	@Override
	public void saveOne(Document obj, String table) {
		getCollection(table).insertOne(obj);
	}

	@Override
	public long count(Bson bson, String table) {
		return getCollection(table).count(bson);
	}

	@Override
	public void deleteAll(Bson query, String table) {
		getCollection(table).deleteMany(query);
	}

	@Override
	public void deleteById(Object id, String table) {
		getCollection(table).deleteOne(new Document("_id", id));
	}

	@Override
	public void saveOrUpdate(Document doc, String table) {
		if(doc.get("_id")==null || getCollection(table).find(new Document("_id",doc.get("_id"))).first() == null){
			getCollection(table).insertOne(doc);
		}else{
			getCollection(table).updateOne(eq("_id",doc.get("_id")), new Document("$set",doc));
		}
	}

}
