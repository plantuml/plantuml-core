package net.sourceforge.plantuml.code;

import java.io.IOException;

public interface Transcoder {

	public String decode(String code) throws NoPlantumlCompressionException;
}
