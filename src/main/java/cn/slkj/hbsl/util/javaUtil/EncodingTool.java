package cn.slkj.hbsl.util.javaUtil;

import java.io.UnsupportedEncodingException;

public class EncodingTool {
	public static String encodeStr(String str) {
		if (str == null && str.length() == 0) {
			return null;
		}
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
