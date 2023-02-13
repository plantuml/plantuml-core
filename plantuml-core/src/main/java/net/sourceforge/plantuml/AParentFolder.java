package net.sourceforge.plantuml;

import java.io.IOException;

public interface AParentFolder {

	public AFile getAFile(String nameOrPath) throws IOException;

}
