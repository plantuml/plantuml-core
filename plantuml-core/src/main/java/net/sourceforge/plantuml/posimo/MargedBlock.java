package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.Positionable;

public class MargedBlock {

	private final Block block;
	private final IEntityImageBlock imageBlock;
	private final double marginDecorator;
	private final XDimension2D imageDimension;

	static private int uid = 1;

	public MargedBlock(StringBounder stringBounder, IEntityImageBlock imageBlock, double marginDecorator, Cluster parent) {
		this.imageBlock = imageBlock;
		this.marginDecorator = marginDecorator;
		this.imageDimension = imageBlock.getDimension(stringBounder);
		this.block = new Block(uid++, imageDimension.getWidth() + 2 * marginDecorator, imageDimension.getHeight() + 2
				* marginDecorator, parent);
	}

	public Block getBlock() {
		return block;
	}

	public double getMarginDecorator() {
		return marginDecorator;
	}

	public IEntityImageBlock getImageBlock() {
		return imageBlock;
	}

	public Positionable getImagePosition() {
		return new Positionable() {

			public XDimension2D getSize() {
				return imageDimension;
			}

			public XPoint2D getPosition() {
				final XPoint2D pos = block.getPosition();
				return new XPoint2D(pos.getX() + marginDecorator, pos.getY() + marginDecorator);
			}

			public void moveSvek(double deltaX, double deltaY) {
				throw new UnsupportedOperationException();
			}
		};
	}

}
