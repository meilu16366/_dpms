package com.kx.remote.socket;

public interface SocketConfig {
	/**心跳频率*/
	int BEATHZ = 2000;
	/**重连时间*/
	int RECONNECT = 300;
	/**超时时间*/
	int TIMEOUT = 5000;
	/**重连次数*/
	int RETIMES = 3;
	/***/
	int RESIVTIME = 10000;
}
