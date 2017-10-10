package com.kx.remote.socket;


import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;


@Component
public class RemoteControl {
	
	private static final Logger logger = Logger.getLogger(RemoteControl.class);
	
	@Autowired
	private MongoDao mongoDao;
	
	/**开机选择*/
	public boolean chooseOpen(String _id) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 45, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,1f,0);
			byte[] message = remote.getYKMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("开机选择失败！："+_id);
		}
		return false;
	}
	/**开机执行*/
	public boolean excutOpen(String _id) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 45, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,1f,1);
			byte[] message = remote.getYKMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("开机执行失败！："+_id);
		}
		return false;
	}
	
	/**关机选择*/
	public boolean chooseClose(String _id) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 45, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,0f,0);
			byte[] message = remote.getYKMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("关机选择失败！："+_id);
		}
		return true;
	}
	/**关机执行*/
	public boolean excutClose(String _id) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 45, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,0f,1);
			byte[] message = remote.getYKMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("关机执行失败："+_id);
		}
		return false;
	}
	/**遥调选择*/
	public boolean chooseAdjust(String _id,float value) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 50, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,value,0);
			byte[] message = remote.getYTMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("遥调选择失败："+_id);
		}
		return false;
	}
	/**遥调执行*/
	public boolean excutAdjust(String _id,float value) {
		try {
			byte[] su = {104, 19, 0, 0, 0, 0, 0, 50, 1, 6, 0, 1, 0 };
			RemoteBean remote = getRemotBean(_id,su,value,1);
			byte[] message = remote.getYTMessage();
			return isSuccess(_id,message);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("遥调执行失败："+_id);
		}
		return false;
	}
	
	/**
	 * 发送并接收遥控遥调报文，判断是否成功
	 * @param _id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	private boolean isSuccess(String _id,byte[] message) throws Exception {
		Heartbeat hb = HeartbeatHandler.getHeartbeat(_id);
		if(hb==null) {
			return false;
		}
		try {
			hb.setBeat(false);
			Thread.sleep(300);
			if(hb.sendMessage(message)) {
				byte[] ru = new byte[message.length];
				Thread.sleep(300);
				hb.receiveMessage(ru);
				return Arrays.equals(message, ru);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			hb.setBeat(true);
		}
		return false;
	}
	
	/**
	 * 获得控制对象
	 * @param _id id
	 * @param message 报文
	 * @param value 值
	 * @param way 0 : 选择 1 : 执行
	 * @return
	 */
	private RemoteBean getRemotBean(String _id,byte[] message,Float value,int way) {
		Document doc = mongoDao.findOne(new Document("_id",_id), MongoDef.MEASURE);
		int station = NumberUtils.toInt(doc.get("station")+"");
		int point = NumberUtils.toInt(doc.get("point")+"");
		RemoteBean remote = new RemoteBean();
		remote.setPoint(point);
		remote.setStation(station);
		remote.setWay(way);
		remote.setValue(value);
		remote.setMessage(message);
		return remote;
	}
	
	private class RemoteBean{
		/**站号*/
		private int station;
		/**点号*/
		private int point;
		/**选择or执行*/
		private int way;
		/**遥调值*/
		private Float value;
		/**报文*/
		private byte[] message;

		public byte[] getYTMessage() {
			message = ArrayUtils.addAll(message, toHH((short) station));
			message = ArrayUtils.addAll(message, toHH((short) point));
			message = ArrayUtils.addAll(message, toHH((short) value.floatValue()));
			message = ArrayUtils.add(message, (byte)way);
			return message;
		}
		
		public byte[] getYKMessage() {
			message = ArrayUtils.addAll(message, toHH((short) station));
			message = ArrayUtils.addAll(message, toHH((short) point));
			message = ArrayUtils.add(message, (byte)value.floatValue());
			message = ArrayUtils.add(message, (byte)way);
			return message;
		}
		public void setMessage(byte[] message) {
			this.message = message;
		}

		public void setStation(int station) {
			this.station = station;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public void setValue(Float value) {
			this.value = value;
		}

		public void setWay(int way) {
			this.way = way;
		}

	}
	public static byte[] toHH(short n){
	    byte[] b = new byte[2];
	    b[1] = (byte)(n & 0xFF);
	    b[0] = (byte)(n >> 8 & 0xFF);
	    return b;
	}
}
