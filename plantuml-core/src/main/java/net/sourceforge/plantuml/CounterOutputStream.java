package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.OutputStream;

public class CounterOutputStream extends OutputStream {

	private int length;
	private final OutputStream os;

	public CounterOutputStream(OutputStream os) {
		this.os = os;
	}

	/**
	 * Writes to nowhere
	 */
	@Override
	public void write(int b) throws IOException {
		os.write(b);
		length++;
	}

	/**
	 * Overridden for performance reason
	 */
	@Override
	public void write(byte b[]) throws IOException {
		os.write(b);
		length += b.length;
	}

	/**
	 * Overridden for performance reason
	 */
	@Override
	public void write(byte b[], int off, int len) throws IOException {
		os.write(b, off, len);
		length += len;
	}

	public int getLength() {
		return length;
	}
	
	@Override
	public void flush() throws IOException {
		os.flush();
	}
	
	
	@Override
	public void close() throws IOException {
		os.close();
	}

}
