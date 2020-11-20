package org.hum.lab.socket;

import java.io.IOException;
import java.net.UnknownHostException;

import org.hum.lab.socket.common.SocketClient;

public class HttpClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		byte[] resp = SocketClient.sendOnce("www.baidu.com", 80, "GET / HTTP/1.1\r\nHost:www.baidu.com\r\nConnection:close\r\n\r\n".getBytes());
		System.out.println(new String(resp));
	}
}
