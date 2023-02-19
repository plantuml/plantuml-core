package net.sourceforge.plantuml.code;

public class CompressionNone implements Compression {

	public byte[] compress(byte[] in) {
		return in;
	}

	public ByteArray decompress(byte[] in) {
		return ByteArray.from(in);
	}

}