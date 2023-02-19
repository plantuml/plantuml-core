package net.sourceforge.plantuml.code;

import java.io.IOException;

public class TranscoderSmart implements Transcoder {

	private final Transcoder zlib = TranscoderImpl.utf8(new AsciiEncoder(), new ArobaseStringCompressor(),
			new CompressionZlib());
	private final Transcoder hexOnly = TranscoderImpl.utf8(new AsciiEncoderHex(), new ArobaseStringCompressor(),
			new CompressionNone());

	public String decode(String code) throws NoPlantumlCompressionException {
		// Work in progress
		// See https://github.com/plantuml/plantuml/issues/117

		if (code.startsWith("~0"))
			return zlib.decode(code.substring(2));


		if (code.startsWith("~h"))
			return hexOnly.decode(code.substring(2));


			return zlib.decode(code);
		// return zlib.decode(code);
	}

}