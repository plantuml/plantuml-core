package com.plantuml.wasm.v1;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import com.plantuml.wasm.JsonResult;
import com.plantuml.wasm.StringBounderCanvas;
import com.plantuml.wasm.WasmLog;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.BlockUmlBuilder;
import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.preproc.Defines;
import net.sourceforge.plantuml.ugraphic.g2d.UGraphicG2d;

public class Canvas {

	public static Frame frame;
	public static Graphics2D g2d;
	private static int frameWidth;
	private static int frameHeight;

	public static Object initCanvas(int width, int height) {
		final long start = System.currentTimeMillis();
		WasmLog.start = start;
		WasmLog.log("initCanvas");
		if (g2d == null) {
			frameWidth = width;
			frameHeight = height;
			frame = new Frame();
			frame.setUndecorated(true);
			frame.setSize(frameWidth, frameHeight);
			frame.setLayout(null);
			frame.setVisible(true);
			g2d = (Graphics2D) frame.getGraphics();
			WasmLog.log("initCanvas done = " + frame);
			return 45;
		}
		WasmLog.log("initCanvas skipped because it has already been done");
		return 47;
	}

	public static Object convert(String mode, String text) {
		final long start = System.currentTimeMillis();
		WasmLog.start = start;

		try {
			text = cleanText(text);

			final BlockUmlBuilder builder = new BlockUmlBuilder(Collections.<String>emptyList(), UTF_8,
					Defines.createEmpty(), new StringReader(text), null, "string");
			List<BlockUml> blocks = builder.getBlockUmls();

			if (blocks.size() == 0)
				return JsonResult.noDataFound(start);

			WasmLog.log("...loading data...");

			final Diagram system = blocks.get(0).getDiagram();
			if (system instanceof PSystemError) {
				final ErrorUml error = ((PSystemError) system).getFirstError();
				WasmLog.log("[" + error.getPosition() + "] " + error.getError());
				return JsonResult.fromError(start, (PSystemError) system);
			}

			WasmLog.log("...processing...");

			final HColor back = HColors.simple(Color.WHITE);
			final StringBounder stringBounder = new StringBounderCanvas(g2d);
			final UGraphicG2d ug = new UGraphicG2d(back, ColorMapper.IDENTITY, stringBounder, g2d, 1.0, FileFormat.PNG);
			WasmLog.log("...cleaning...");
			ug.apply(back).apply(back.bg()).draw(new URectangle(frameWidth, frameHeight));
			WasmLog.log("...drawing...");

			system.exportDiagramGraphic(ug);

			WasmLog.log("done!");

			return JsonResult.ok(start, null, system);

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
