package net.sourceforge.plantuml.cucadiagram;

public class UnparsableGraphvizException extends RuntimeException {

	private final String graphvizVersion;
	private final String svg;
	private final String diagramSource;

	public UnparsableGraphvizException(Exception cause, String graphvizVersion, String svg, String diagramSource) {
		super(cause);
		this.graphvizVersion = graphvizVersion;
		this.svg = svg;
		this.diagramSource = diagramSource;
	}

	public String getGraphvizVersion() {
		return graphvizVersion;
	}

	public final String getDebugData() {
		return "SVG=" + svg + "\r\nDIAGRAM=" + diagramSource;
	}

}
