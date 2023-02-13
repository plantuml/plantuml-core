package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Objects;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;

public class FtileDecorateIn extends FtileDecorate {

	final private LinkRendering linkRendering;

	public FtileDecorateIn(final Ftile ftile, final LinkRendering linkRendering) {
		super(ftile);
		this.linkRendering = Objects.requireNonNull(linkRendering);
	}

	public LinkRendering getInLinkRendering() {
		return linkRendering;
	}

}
