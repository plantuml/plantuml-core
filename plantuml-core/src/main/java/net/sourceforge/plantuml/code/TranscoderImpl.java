package net.sourceforge.plantuml.code;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

public class TranscoderImpl implements Transcoder {

	static enum Format {
		UTF8, UPF9;
	}

	private final Compression compression;
	private final URLEncoder urlEncoder;
	private final StringCompressor stringCompressor;
	private final Format format;

	private TranscoderImpl(URLEncoder urlEncoder, StringCompressor stringCompressor, Compression compression,
			Format format) {
		this.compression = compression;
		this.urlEncoder = urlEncoder;
		this.stringCompressor = stringCompressor;
		this.format = format;
	}

	public static Transcoder utf8(URLEncoder urlEncoder, StringCompressor stringCompressor, Compression compression) {
		return new TranscoderImpl(urlEncoder, stringCompressor, compression, Format.UTF8);
	}


	public String encode(String text) throws IOException {
		final String stringAnnoted = stringCompressor.compress(text);
		final byte[] data;
			data = stringAnnoted.getBytes(UTF_8);

		final byte[] compressedData = compression.compress(data);

		return urlEncoder.encode(compressedData);
	}

	public String decode(String code) throws NoPlantumlCompressionException {
		try {
			final byte compressedData[] = urlEncoder.decode(code);
			final ByteArray data = compression.decompress(compressedData);
			final String string;
				string = data.toUFT8String();
			return stringCompressor.decompress(string);
		} catch (Exception e) {
			// System.err.println("Cannot decode string");
			throw new NoPlantumlCompressionException(e);
		}
	}

}
