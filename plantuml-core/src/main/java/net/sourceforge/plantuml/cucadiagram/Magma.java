package net.sourceforge.plantuml.cucadiagram;

import java.util.List;

import net.atmp.Link;
import net.atmp.LinkArg;
import net.sourceforge.plantuml.baraye.CucaDiagram;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.decoration.LinkDecor;
import net.sourceforge.plantuml.decoration.LinkType;

public class Magma {

	private final CucaDiagram diagram;
	private final List<Entity> standalones;
	private final LinkType linkType = new LinkType(LinkDecor.NONE, LinkDecor.NONE).getInvisible();

	public Magma(CucaDiagram system, List<Entity> standalones) {
		this.diagram = system;
		this.standalones = standalones;
	}

	public void putInSquare() {
		final SquareLinker<Entity> linker = new SquareLinker<Entity>() {
			public void topDown(Entity top, Entity down) {
				diagram.addLink(new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(),
						top, down, linkType, LinkArg.noDisplay(2)));
			}

			public void leftRight(Entity left, Entity right) {
				diagram.addLink(new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(),
						left, right, linkType, LinkArg.noDisplay(1)));
			}
		};
		new SquareMaker<Entity>().putInSquare(standalones, linker);
	}

	public Entity getContainer() {
		final Entity parent = standalones.get(0).getParentContainer();
		if (parent == null)
			return null;

		return parent.getParentContainer();
	}

	public boolean isComplete() {
		final Entity parent = getContainer();
		if (parent == null)
			return false;

		return parent.countChildren() == standalones.size();
	}

	private int squareSize() {
		return SquareMaker.computeBranch(standalones.size());
	}

	private Entity getTopLeft() {
		return standalones.get(0);
	}

	private Entity getBottomLeft() {
		int result = SquareMaker.getBottomLeft(standalones.size());
		return standalones.get(result);
	}

	private Entity getTopRight() {
		final int s = squareSize();
		return standalones.get(s - 1);
	}

	@Override
	public String toString() {
		return standalones.get(0).getParentContainer() + " " + standalones.toString() + " " + isComplete();
	}

	public void linkToDown(Magma down) {
		diagram.addLink(new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(),
				this.getBottomLeft(), down.getTopLeft(), linkType, LinkArg.noDisplay(2)));

	}

	public void linkToRight(Magma right) {
		diagram.addLink(new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(),
				this.getTopRight(), right.getTopLeft(), linkType, LinkArg.noDisplay(1)));
	}

}
