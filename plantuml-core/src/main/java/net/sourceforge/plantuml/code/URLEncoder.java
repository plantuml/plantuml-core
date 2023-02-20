package net.sourceforge.plantuml.code;

public interface URLEncoder {

	String encode(byte data[]);

	byte[] decode(String s);

}
