package net.sourceforge.plantuml.svek;

import java.util.Objects;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.style.ISkinParam;

public abstract class AbstractEntityImage extends AbstractTextBlock implements IEntityImage {

	private final Entity entity;
	private final ISkinParam skinParam;

	public AbstractEntityImage(Entity entity, ISkinParam skinParam) {
		this.entity = Objects.requireNonNull(entity);
		this.skinParam = Objects.requireNonNull(skinParam);
	}

	@Override
	public boolean isHidden() {
		return entity.isHidden();
	}

	protected final Entity getEntity() {
		return entity;
	}

	protected final ISkinParam getSkinParam() {
		return skinParam;
	}

	@Override
	public final HColor getBackcolor() {
		return skinParam.getBackgroundColor();
	}

	protected final Stereotype getStereo() {
		return entity.getStereotype();
	}

	@Override
	public Margins getShield(StringBounder stringBounder) {
		return Margins.NONE;
	}

	@Override
	public double getOverscanX(StringBounder stringBounder) {
		return 0;
	}

}
