package net.sourceforge.plantuml.svek.image;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class SmallestEnclosingCircle {

	private final List<XPoint2D> all = new ArrayList<>();
	private Circle lastSolution;

	public void append(XPoint2D pt) {
		if (all.contains(pt) == false)
			all.add(pt);

		this.lastSolution = null;
	}

	public Circle getCircle() {
		if (lastSolution == null)
			lastSolution = findSec(all.size(), all, 0, new ArrayList<>(all));

		return lastSolution;
	}

	private Circle findSec(int n, List<XPoint2D> p, int m, List<XPoint2D> b) {
		Circle sec = new Circle();

		// Compute the Smallest Enclosing Circle defined by B
		if (m == 1)
			sec = new Circle(b.get(0));
		else if (m == 2)
			sec = new Circle(b.get(0), b.get(1));
		else if (m == 3)
			return Circle.getCircle(b.get(0), b.get(1), b.get(2));

		// Check if all the points in p are enclosed
		for (int i = 0; i < n; i++)
			if (sec.isOutside(p.get(i))) {
				// Compute B <--- B union P[i].
				b.set(m, p.get(i));
				// Recurse
				sec = findSec(i, p, m + 1, b);
			}

		return sec;
	}

}
