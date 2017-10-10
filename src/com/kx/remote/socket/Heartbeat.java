package com.kx.remote.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class Heartbeat {
	/**心跳发送*/
	private static final byte[] SU = {104, 4, 0, 67, 00, 00, 00};
	/**心跳接收*/
	private static final byte[] RU = {104, 4, 0, (byte)131, 00, 00, 00};
	/**连接*/
	private Socket socket;
	/**ip*/
	private String ip;
	/**端口*/
	private int port;
	/**电站id*/
	private int psid;
	/**是否正在心跳*/
	private boolean isBeat = true;
	
	
	public Heartbeat(String ip, int port, int psid) {
		super();
		this.ip = ip;
		this.port = port;
		this.psid = psid;
	}
	
	/**连接是否关闭*/
	public boolean isServerClose() {
		try{
		    return ((this.socket == null) || (this.socket.isClosed()) || (!(this.socket.isConnected())));
		}catch (Exception e){
			return true;
		}
	}
	/**建立连接*/
	public void connect() {
		try {
			if(socket != null) {
				socket.close();
			}
			socket = new Socket();
		    this.socket.connect(new InetSocketAddress(this.ip, this.port), SocketConfig.TIMEOUT);
		    this.socket.setSoTimeout(SocketConfig.RESIVTIME);
		    this.socket.setKeepAlive(true);
		} catch (Exception e) {
			try {
				Thread.sleep(SocketConfig.RECONNECT);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 * 发送心跳
	 */
	public boolean sendBeat(int reConnectCount) {
		try {
			OutputStream out = socket.getOutputStream();
			out.write(SU);
			out.flush();
			return true;
		} catch (Exception e) {
			if(reConnectCount==3) {
				return false;
			}
			connect();
			reConnectCount++;
			return sendBeat(reConnectCount);
		}
	}
	/**接收处理心跳*/
	public boolean receiveBeat() {
		try {
			InputStream in = socket.getInputStream();
			byte[] ru = new byte[7];
			in.read(ru);
			return Arrays.equals(ru, RU);
		} catch (IOException e) {
			return false;
		}
		
	}
	@Override
	public String toString() {
		return "Heartbeat [ip=" + ip + ", port=" + port + ", psid=" + psid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
		result = prime * result + psid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heartbeat other = (Heartbeat) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		if (psid != other.psid)
			return false;
		return true;
	}

	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
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
	public void setPort(int port) {
		this.port = port;
	}
	public int getPsid() {
		return psid;
	}
	public void setPsid(int psid) {
		this.psid = psid;
	}
	public boolean isBeat() {
		return isBeat;
	}
	public void setBeat(boolean isBeat) {
		this.isBeat = isBeat;
	}
	/**
	 * 发送报文
	 * @param message
	 * @return
	 */
	public boolean sendMessage(byte[] message) {
		try {
			if(socket == null) {
				connect();
			}
			OutputStream out = socket.getOutputStream();
			out.write(message);
			out.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * 接收报文
	 * @param ru
	 * @throws IOException 
	 */
	public void receiveMessage(byte[] ru) throws IOException {
		
		InputStream in = socket.getInputStream();
		in.read(ru);	
		
	}

	
}
