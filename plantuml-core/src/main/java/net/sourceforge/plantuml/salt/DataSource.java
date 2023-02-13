package net.sourceforge.plantuml.salt;

import java.util.Iterator;

public interface DataSource extends Iterator<Terminated<String>> {

	Terminated<String> peek(int i);
}
