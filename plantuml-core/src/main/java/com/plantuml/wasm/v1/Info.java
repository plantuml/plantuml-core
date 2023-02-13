package com.plantuml.wasm.v1;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import com.plantuml.wasm.JsonResult;
import com.plantuml.wasm.WasmLog;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.BlockUmlBuilder;
import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.preproc.Defines;

public class Info {

	public static Object info(String text) {
		final long start = System.currentTimeMillis();
		WasmLog.start = start;
		WasmLog.log("Starting processing");

		try {
			text = cleanText(text);
			final BlockUmlBuilder builder = new BlockUmlBuilder(Collections.<String>emptyList(), UTF_8,
					Defines.createEmpty(), new StringReader(text), null, "string");
			List<BlockUml> blocks = builder.getBlockUmls();

			if (blocks.size() == 0)
				return JsonResult.noDataFound(start);

			final Diagram system = blocks.get(0).getDiagram();
			if (system instanceof PSystemError) {
				final ErrorUml error = ((PSystemError) system).getFirstError();
				WasmLog.log("[" + error.getPosition() + "] " + error.getError());
				return JsonResult.fromError(start, (PSystemError) system);
			}

//			WasmLog.log("...processing...");
//
//			final FileOutputStream fos = new FileOutputStream(new File(pathOut));
//			WasmLog.log("...loading data...");
//
//			final ImageData imageData = system.exportDiagram(fos, 0, format);
//
//			WasmLog.log("Done!");
//			fos.close();
//
//			return JsonResult.ok(start, imageData, system);
			return null;

		} catch (Throwable t) {
			WasmLog.log("Fatal error " + t);
			return JsonResult.fromCrash(start, t);
		}
	}

	private static String cleanText(String text) {
		if (text.endsWith("\n") == false)
			text = text + "\n";
		if (text.endsWith("@start") == false)
			text = "@startuml\n" + text + "@enduml\n";

		return text;
	}

}
