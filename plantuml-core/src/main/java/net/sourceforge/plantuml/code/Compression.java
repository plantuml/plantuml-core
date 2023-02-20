package net.sourceforge.plantuml.code;

public interface Compression {

	/**
	 * Shrinks the given <code>in</code> array with length <code>len</code>
	 * 
	 * @return a newly created array with the compressed data.
	 */
	byte[] compress(final byte[] in);

	/**
	 * Grows the given <code>in</code> array with length <code>len</code> compressed
	 * with the <code>shrink</code> method.
	 * 
	 * @return a newly created array with the expanded data.
	 */
	ByteArray decompress(byte[] in) throws NoPlantumlCompressionException;

}
