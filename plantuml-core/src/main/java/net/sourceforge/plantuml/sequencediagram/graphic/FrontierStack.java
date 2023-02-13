package net.sourceforge.plantuml.sequencediagram.graphic;

interface FrontierStack extends Frontier {

	FrontierStack openBar();

	FrontierStack restore();

	FrontierStack closeBar();
}
