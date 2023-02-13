package net.sourceforge.plantuml.code;

import java.io.ByteArrayInputStream;
import java.util.zip.Deflater;

import net.sourceforge.plantuml.code.deflate.ByteBitInputStream;
import net.sourceforge.plantuml.code.deflate.Decompressor;

public class CompressionZlib implements Compression {


	public ByteArray decompress(byte[] input) throws NoPlantumlCompressionException {
		final byte padded[] = new byte[input.length + 256];
		System.arraycopy(input, 0, padded, 0, input.length);

		final ByteBitInputStream inputStream = new ByteBitInputStream(new ByteArrayInputStream(padded));
		try {
			return ByteArray.from(Decompressor.decompress(inputStream));
		} catch (Exception e) {
			throw new NoPlantumlCompressionException(e);
		}
	}

	private byte[] copyArray(final byte[] data, final int len) {
		final byte[] result = new byte[len];
		System.arraycopy(data, 0, result, 0, len);
		return result;
	}

}
