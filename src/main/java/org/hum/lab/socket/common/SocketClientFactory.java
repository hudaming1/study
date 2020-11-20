package org.hum.lab.socket.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SocketClientFactory {
	
	public static void sendOnce(String host, int port, byte[] bytes) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		socket.getOutputStream().write(bytes);
		socket.getOutputStream().flush();
		socket.close();
	}

	public static byte[] sendOnceAndReceive(String host, int port, byte[] bytes) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		socket.getOutputStream().write(bytes);
		socket.getOutputStream().flush();
		byte[] buffer = new byte[1024];
		List<byte[]> tempByteList = new ArrayList<byte[]>();
		InputStream inputStream = socket.getInputStream();
		int readLen = -1 ;
		int totalLen = 0;
		while ((readLen = inputStream.read(buffer)) != -1) {
			byte[] temp = new byte[readLen];
			System.arraycopy(buffer, 0, temp, 0, readLen);
			tempByteList.add(temp);
			totalLen += readLen;
		}
		socket.close();
		
		byte[] outBytes = new byte[totalLen + 1];
		int start = 0;
		for (byte[] _bs : tempByteList) {
			System.arraycopy(_bs, 0, outBytes, start, _bs.length);
			start += _bs.length;
		}
		return outBytes;
	}
}
