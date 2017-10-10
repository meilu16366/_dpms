package com.kx.remote.socket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.Document;

import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;
import com.kx.frame.services.ServiceBean;


public class HeartbeatHandler {
	private static final Logger logger = Logger.getLogger(HeartbeatHandler.class);
	private static MongoDao mongoDao = ServiceBean.getBean("mongoDao");
	private static Map<Heartbeat,Heartbeat> hearts = new HashMap<Heartbeat, Heartbeat>();
	//初始化所有心跳点
	//获取IP端口
	public static void initIpAndPort() {
		BsonArray arr = new BsonArray();
		arr.add(new BsonString("YK"));
		arr.add(new BsonString("YT"));
		Document doc = new Document("mutype",new Document("$in",arr));
		Iterator<Document> dos = mongoDao.find(doc,MongoDef.MEASURE);
		for(;dos.hasNext();) {
			Document docu = dos.next();
			try {
				String ip = docu.get("ip")+"";
				int port = NumberUtils.toInt(docu.get("port")+"");
				int psid = NumberUtils.toInt(docu.get("psid")+"");
				Heartbeat hb = new Heartbeat(ip,port,psid);
				hearts.put(hb,hb);
			}catch(Exception e) {
				logger.info("初始化心跳对象出错："+docu);
				e.printStackTrace();
			}
		}
		start();
		
	}
	
	private static void start() {
		if(hearts.size()>0) {
			ExecutorService excpool = Executors.newFixedThreadPool(hearts.size());
			for(Heartbeat heart : hearts.keySet()) {
				excpool.execute(new HeartThread(heart));
			}
		}
	}
	/**
	 * 获得对应心跳
	 * @param ip
	 * @param port
	 * @param psid
	 * @return
	 */
	public static Heartbeat getHeartbeat(String ip,int port,int psid) {
		return hearts.get(new Heartbeat(ip,port,psid));
	}
	/**
	 * 获得对应心跳
	 * @param _id
	 * @return
	 */
	public static Heartbeat getHeartbeat(String _id) {
		Document docu = mongoDao.findOne(new Document("_id",_id), MongoDef.MEASURE);
		String ip = docu.get("ip")+"";
		int port = NumberUtils.toInt(docu.get("port")+"");
		int psid = NumberUtils.toInt(docu.get("psid")+"");
		return hearts.get(new Heartbeat(ip,port,psid));
	}
	
}
