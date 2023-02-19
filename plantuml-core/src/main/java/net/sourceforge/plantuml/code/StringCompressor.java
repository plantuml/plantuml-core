package net.sourceforge.plantuml.code;

import java.io.IOException;

public interface StringCompressor {

	String compress(String s) throws IOException;

	String decompress(String compressed) throws NoPlantumlCompressionException;

}