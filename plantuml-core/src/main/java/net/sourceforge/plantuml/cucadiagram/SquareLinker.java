package net.sourceforge.plantuml.cucadiagram;

interface SquareLinker<O extends Object> {

	public void leftRight(O left, O right);

	public void topDown(O top, O down);

}
