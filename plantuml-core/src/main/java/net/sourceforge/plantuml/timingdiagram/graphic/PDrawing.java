package net.sourceforge.plantuml.timingdiagram.graphic;

import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.timingdiagram.ChangeState;
import net.sourceforge.plantuml.timingdiagram.TimeConstraint;
import net.sourceforge.plantuml.timingdiagram.TimeProjected;

public interface PDrawing extends TimeProjected {

	public double getFullHeight(StringBounder stringBounder);

	public void addChange(ChangeState change);

	public TextBlock getPart1(double fullAvailableWidth);

	public UDrawable getPart2();

	public void setInitialState(String initialState, Colors initialColors);

	public void addConstraint(TimeConstraint constraint);

}
