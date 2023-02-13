package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.plantuml.security.SFile;

public class AFileRegular implements AFile {

	private final SFile file;

	@Override
	public String toString() {
		return "AFileRegular::" + file.getAbsolutePath();
	}

	public AFileRegular(SFile file) {
		this.file = file;
	}

	public InputStream openFile() {
		return file.openFile();
	}

	public boolean isOk() {
		return file.exists() && file.isDirectory() == false;
	}

	@Override
	public int hashCode() {
		return file.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AFileRegular == false) {
			return false;
		}
		return this.file.equals(((AFileRegular) obj).file);
	}

	public AParentFolder getParentFile() {
		return new AParentFolderRegular(file.getParentFile());
	}

	public SFile getUnderlyingFile() {
		return file;
	}

	public SFile getSystemFolder() throws IOException {
		return file.getParentFile().getCanonicalFile();
	}

}
