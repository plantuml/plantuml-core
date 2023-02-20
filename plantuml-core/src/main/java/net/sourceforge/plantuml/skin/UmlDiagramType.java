package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.style.SName;

public enum UmlDiagramType {
	SEQUENCE, STATE, CLASS, OBJECT, ACTIVITY, DESCRIPTION, COMPOSITE, FLOW, TIMING, BPM, NWDIAG, MINDMAP, WBS, WIRE,
	HELP, GANTT, SALT, JSON, GIT, BOARD, YAML, HCL, EBNF, REGEX;

	public SName getStyleName() {
		if (this == SEQUENCE)
			return SName.sequenceDiagram;

		if (this == STATE)
			return SName.stateDiagram;

		if (this == CLASS)
			return SName.classDiagram;

		if (this == OBJECT)
			return SName.objectDiagram;

		if (this == ACTIVITY)
			return SName.activityDiagram;

		if (this == DESCRIPTION)
			return SName.componentDiagram;

		if (this == COMPOSITE)
			return SName.componentDiagram;

		if (this == MINDMAP)
			return SName.mindmapDiagram;

		if (this == WBS)
			return SName.wbsDiagram;

		if (this == GANTT)
			return SName.ganttDiagram;

		if (this == SALT)
			return SName.saltDiagram;

		if (this == YAML)
			return SName.yamlDiagram;

		if (this == HCL)
			return SName.yamlDiagram;

		if (this == JSON)
			return SName.jsonDiagram;

		if (this == TIMING)
			return SName.timingDiagram;

		if (this == NWDIAG)
			return SName.nwdiagDiagram;

		if (this == EBNF)
			return SName.ebnf;

		if (this == REGEX)
			return SName.regex;

		return SName.activityDiagram;
	}
}
