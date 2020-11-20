package org.hum.lab.socket.demo;

import java.io.IOException;
import java.net.UnknownHostException;

import org.hum.lab.socket.common.SocketClientFactory;
import org.hum.lab.util.ByteUtils;

public class SocketStickyClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		SocketClientFactory.sendOnce("localhost", 10086, ByteUtils.genNumberCharArray(1024));
		System.out.println("over");
	}
}
