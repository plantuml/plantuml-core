package com.plantuml.wasm;

import com.leaningtech.client.Global;

public class Demo {

	public static Object convert(String pathOut, String text) {
		System.err.println("Demo::convert");

		 return Global.JSString("{\"value\":42}");
	}

}
