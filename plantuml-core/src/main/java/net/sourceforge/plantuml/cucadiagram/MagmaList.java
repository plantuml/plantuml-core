package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.baraye.Entity;

public class MagmaList {

	private final List<Magma> all = new ArrayList<>();

	public void add(Magma magma) {
		all.add(magma);
	}

	public MagmaList getMagmas(Entity group) {
		final MagmaList result = new MagmaList();
		for (Magma m : all) {
			if (m.getContainer() == group) {
				result.add(m);
			}
		}
		return result;
	}

	public int size() {
		return all.size();
	}

	public void putInSquare() {
		final SquareLinker<Magma> linker = new SquareLinker<Magma>() {
			public void topDown(Magma top, Magma down) {
				top.linkToDown(down);
			}

			public void leftRight(Magma left, Magma right) {
				left.linkToRight(right);
			}
		};
		new SquareMaker<Magma>().putInSquare(all, linker);

	}

}
