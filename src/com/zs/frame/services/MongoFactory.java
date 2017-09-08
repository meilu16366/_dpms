package com.zs.frame.services;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class MongoFactory {
	
	private String ip;
	
	private int port;
	
	private String databaseName;
	
	private MongoClient client;
	
	private MongoDatabase database;
	
	public void init(){
		client = new MongoClient(ip, port);
		database = client.getDatabase(databaseName);
	}
	
	
	public MongoDatabase getDatabase(){
		return database;
	}
	

	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}


	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
}
