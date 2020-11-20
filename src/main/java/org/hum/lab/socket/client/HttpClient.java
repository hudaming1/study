package org.hum.lab.socket.client;

import java.io.IOException;
import java.net.UnknownHostException;

import org.hum.lab.socket.common.SocketClientFactory;

public class HttpClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		byte[] resp = SocketClientFactory.sendOnceAndReceive("www.baidu.com", 80, "GET / HTTP/1.1\r\nHost:www.baidu.com\r\nConnection:close\r\n\r\n".getBytes());
		System.out.println(new String(resp));
	}
}
