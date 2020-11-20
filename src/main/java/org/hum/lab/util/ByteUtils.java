package org.hum.lab.util;

public class ByteUtils {

	public static byte[] genNumberCharArray(int num) {
		byte[] bytes = new byte[num];

		for (int i = 1; i <= num; i++) {
			bytes[i - 1] = (byte) ((byte)'0' + (i % 10));
		}

		return bytes;
	}
}
