package net.sourceforge.plantuml.code;

public interface Compression {


	/**
	 * Grows the given <code>in</code> array with length <code>len</code> compressed
	 * with the <code>shrink</code> method.
	 * 
	 * @return a newly created array with the expanded data.
	 */
	ByteArray decompress(byte[] in) throws NoPlantumlCompressionException;

}
