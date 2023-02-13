package com.plantuml.wasm.v1;

import java.io.IOException;

import com.plantuml.wasm.Memory;

import net.sourceforge.plantuml.version.Version;

public class RunInit {

	public static void main(String[] argsArray) throws IOException {
		Memory.cheerpjPath = argsArray[0];
		System.err.print("PlantUML Version: " + Version.versionString());
		System.err.print("Init ok. cheerpjPath is " + Memory.cheerpjPath);
	}

}
