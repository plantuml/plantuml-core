package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.plantuml.security.SFile;

public interface AFile {

	public InputStream openFile();

	public boolean isOk();

	public AParentFolder getParentFile();

	public SFile getUnderlyingFile();

	public SFile getSystemFolder() throws IOException;

}
