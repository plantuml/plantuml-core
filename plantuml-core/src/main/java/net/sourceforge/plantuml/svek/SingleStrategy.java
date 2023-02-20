package net.sourceforge.plantuml.svek;

public enum SingleStrategy {

	SQUARE, HLINE, VLINE;

//	private Collection<Link> generateLinks(List<ILeaf> standalones) {
//		return putInSquare(standalones);
//	}

//	private Collection<Link> putInSquare(List<ILeaf> standalones) {
//		final List<Link> result = new ArrayList<>();
//		final LinkType linkType = new LinkType(LinkDecor.NONE, LinkDecor.NONE).getInvisible();
//		final int branch = computeBranch(standalones.size());
//		int headBranch = 0;
//		for (int i = 1; i < standalones.size(); i++) {
//			final int dist = i - headBranch;
//			final IEntity ent2 = standalones.get(i);
//			final Link link;
//			if (dist == branch) {
//				final IEntity ent1 = standalones.get(headBranch);
//				link = new Link(ent1, ent2, linkType, Display.NULL, 2);
//				headBranch = i;
//			} else {
//				final IEntity ent1 = standalones.get(i - 1);
//				link = new Link(ent1, ent2, linkType, Display.NULL, 1);
//			}
//			result.add(link);
//		}
//		return Collections.unmodifiableCollection(result);
//	}

	static int computeBranch(int size) {
		final double sqrt = Math.sqrt(size);
		final int r = (int) sqrt;
		if (r * r == size) {
			return r;
		}
		return r + 1;
	}

}
