package net.sourceforge.plantuml.code;

public class AsciiEncoderHex implements URLEncoder {

	public String encode(byte data[]) {
		if (data == null) {
			return "";
		}
		final StringBuilder result = new StringBuilder(data.length * 2);
		for (byte b : data) {
			final String val = Integer.toHexString(b & 0xFF);
			if (val.length() == 1) {
				result.append("0");
			}
			result.append(val);
		}
		return result.toString();
	}

	public byte[] decode(String s) {
		final byte result[] = new byte[s.length() / 2];
		for (int i = 0; i < result.length; i++) {
			result[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
		}
		return result;
	}
}
