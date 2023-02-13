package net.sourceforge.plantuml.preproc;

import java.io.Closeable;
import java.io.IOException;

import net.sourceforge.plantuml.text.StringLocated;

public interface ReadLine extends Closeable {

	public StringLocated readLine() throws IOException;
}
