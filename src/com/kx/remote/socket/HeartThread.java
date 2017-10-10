package com.kx.remote.socket;

import org.apache.log4j.Logger;

public class HeartThread implements Runnable {

	private static final Logger logger = Logger.getLogger(HeartThread.class);
	
	private Heartbeat hb;
	
	public HeartThread(Heartbeat hb) {
		this.hb = hb;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(hb.isBeat()) {
					synchronized(hb) {
						boolean success = hb.sendBeat(0) && hb.receiveBeat();
						if(!success) {
							//logger.info("心跳连接断开："+hb);
						}
					}
				}
			}catch(Exception e) {
				logger.error("心跳线程出错:"+hb);
				e.printStackTrace();
				try {
					Thread.sleep(SocketConfig.RECONNECT);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}finally {
				try {
					Thread.sleep(SocketConfig.BEATHZ);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
