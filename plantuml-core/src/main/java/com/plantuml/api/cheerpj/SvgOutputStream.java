package com.plantuml.api.cheerpj;

import java.io.IOException;
import java.io.OutputStream;

public class SvgOutputStream extends OutputStream {

	private final StringBuilder sb = new StringBuilder();

	@Override
	public void write(int data) throws IOException {
		if (data > 0 && data < 255)
			sb.append((char) data);
	}

	@Override
	public String toString() {
		return sb.toString();
	}

}