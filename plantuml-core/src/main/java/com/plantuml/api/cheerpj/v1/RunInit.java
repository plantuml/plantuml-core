package com.plantuml.api.cheerpj.v1;

import java.io.IOException;

import com.plantuml.api.cheerpj.StaticMemory;

import net.sourceforge.plantuml.version.Version;

public class RunInit {

	public static void main(String[] argsArray) throws IOException {
		if (argsArray.length > 0)
			StaticMemory.cheerpjPath = argsArray[0];
		if (argsArray.length > 1)
			StaticMemory.elementIdDebugJava = argsArray[1];
		if (StaticMemory.cheerpjPath.endsWith("/") == false)
			StaticMemory.cheerpjPath = StaticMemory.cheerpjPath + "/";

		System.err.print("PlantUML Version: " + Version.versionString());
		System.err.print("Init ok. cheerpjPath is " + StaticMemory.cheerpjPath);
		if (StaticMemory.elementIdDebugJava != null)
			System.err.print("I will echo debug message to element id " + StaticMemory.elementIdDebugJava);
	}

}
