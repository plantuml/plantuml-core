package net.sourceforge.plantuml.sequencediagram.graphic;

interface Frontier {

	double getFreeY(ParticipantRange range);

	Frontier add(double delta, ParticipantRange range);
}
