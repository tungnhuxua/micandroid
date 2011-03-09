package javacommon.util;

import java.io.IOException;

/**
 * 加密 解密
 * 
 * @author pch 2010.5.24 modify:pch 2010.6.2
 * 
 */
public class EncodeDecodeUtil {

	/**
	 * @param value
	 *            需加密的串
	 * @return 加密后的串
	 */
	public static String base64Encode(String value) {
		String encodeValue = null;
		if (value != null) {
			encodeValue = new sun.misc.BASE64Encoder().encode(value.getBytes());
			return encodeValue;
		}
		return encodeValue;
	}

	/**
	 * @param value
	 *            要解密的串
	 * @return 解密后的串
	 */
	public static String base64Decode(String value) {
		String decodeValue = null;
		if (value != null) {
			try {
				sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
				decodeValue = new String(decoder.decodeBuffer(value));
				return decodeValue;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return decodeValue;
	}

}
