package net.sourceforge.plantuml.code;

import java.io.IOException;

public interface Transcoder {
	public String encode(String text) throws IOException;

	public String decode(String code) throws NoPlantumlCompressionException;
}
