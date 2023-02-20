package net.sourceforge.plantuml.svek;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.BaseFile;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.baraye.EntityFactory;
import net.sourceforge.plantuml.command.Position;
import net.sourceforge.plantuml.cucadiagram.ICucaDiagram;
import net.sourceforge.plantuml.cucadiagram.dot.DotData;
import net.sourceforge.plantuml.cucadiagram.dot.DotSplines;
import net.sourceforge.plantuml.cucadiagram.dot.GraphvizVersion;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.Moveable;
import net.sourceforge.plantuml.klimt.geom.Rankdir;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.skin.UmlDiagramType;
import net.sourceforge.plantuml.style.ISkinParam;

public class DotStringFactory implements Moveable {

	private final Bibliotekon bibliotekon = new Bibliotekon();

	private final ColorSequence colorSequence;
	private final Cluster root;

	private Cluster current;
	private final UmlDiagramType umlDiagramType;
	private final ISkinParam skinParam;
	private final DotMode dotMode;
	private DotSplines dotSplines;

	private final StringBounder stringBounder;

	public DotStringFactory(StringBounder stringBounder, DotData dotData) {
		this.skinParam = dotData.getSkinParam();
		this.umlDiagramType = dotData.getUmlDiagramType();
		this.dotMode = dotData.getDotMode();

		this.colorSequence = new ColorSequence();
		this.stringBounder = stringBounder;
		this.root = new Cluster(dotData.getEntityFactory().getDiagram(), colorSequence, skinParam,
				dotData.getRootGroup());
		this.current = root;
	}

	public DotStringFactory(StringBounder stringBounder, ICucaDiagram diagram) {
		this.skinParam = diagram.getSkinParam();
		this.umlDiagramType = diagram.getUmlDiagramType();
		this.dotMode = DotMode.NORMAL;

		this.colorSequence = new ColorSequence();
		this.stringBounder = stringBounder;
		this.root = new Cluster(diagram, colorSequence, skinParam, diagram.getEntityFactory().getRootGroup());
		this.current = root;
	}

	public void addNode(SvekNode node) {
		current.addNode(node);
	}

	private double getHorizontalDzeta() {
		double max = 0;
		for (SvekLine l : bibliotekon.allLines()) {
			final double c = l.getHorizontalDzeta(stringBounder);
			if (c > max)
				max = c;

		}
		return max / 10;
	}

	private double getVerticalDzeta() {
		double max = 0;
		for (SvekLine l : bibliotekon.allLines()) {
			final double c = l.getVerticalDzeta(stringBounder);
			if (c > max)
				max = c;

		}
		if (root.diagram.getPragma().useKermor())
			return max / 100;
		return max / 10;
	}


	private void manageMinMaxCluster(final StringBuilder sb) {
		final List<String> minPointCluster = new ArrayList<>();
		final List<String> maxPointCluster = new ArrayList<>();
		for (Cluster cluster : bibliotekon.allCluster()) {
			final String minPoint = cluster.getMinPoint(umlDiagramType);
			if (minPoint != null)
				minPointCluster.add(minPoint);

			final String maxPoint = cluster.getMaxPoint(umlDiagramType);
			if (maxPoint != null)
				maxPointCluster.add(maxPoint);

		}
		if (minPointCluster.size() > 0) {
			sb.append("{rank=min;");
			for (String s : minPointCluster) {
				sb.append(s);
				sb.append(" [shape=point,width=.01,label=\"\"]");
				sb.append(";");
			}
			sb.append("}");
			SvekUtils.println(sb);
		}
		if (maxPointCluster.size() > 0) {
			sb.append("{rank=max;");
			for (String s : maxPointCluster) {
				sb.append(s);
				sb.append(" [shape=point,width=.01,label=\"\"]");
				sb.append(";");
			}
			sb.append("}");
			SvekUtils.println(sb);
		}
	}

	private int getMinRankSep() {
		if (umlDiagramType == UmlDiagramType.ACTIVITY) {
			// return 29;
			return 40;
		}
		if (root.diagram.getPragma().useKermor())
			return 40;
		return 60;
	}

	private int getMinNodeSep() {
		if (umlDiagramType == UmlDiagramType.ACTIVITY) {
			// return 15;
			return 20;
		}
		return 35;
	}

	 public GraphvizVersion getGraphvizVersion() {
	 return null;
	 }

	public void openCluster(Entity g, ClusterHeader clusterHeader) {
		this.current = current.createChild(clusterHeader, colorSequence, skinParam, g);
		bibliotekon.addCluster(this.current);
	}

	public void closeCluster() {
		if (current.getParentCluster() == null)
			throw new IllegalStateException();

		this.current = current.getParentCluster();
	}

	public void moveSvek(double deltaX, double deltaY) {
		for (SvekNode sh : bibliotekon.allNodes())
			sh.moveSvek(deltaX, deltaY);

		for (SvekLine line : bibliotekon.allLines())
			line.moveSvek(deltaX, deltaY);

		for (Cluster cl : bibliotekon.allCluster())
			cl.moveSvek(deltaX, deltaY);

	}

	public final Bibliotekon getBibliotekon() {
		return bibliotekon;
	}

	public ColorSequence getColorSequence() {
		return colorSequence;
	}

}
