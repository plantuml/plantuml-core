// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.file;

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
