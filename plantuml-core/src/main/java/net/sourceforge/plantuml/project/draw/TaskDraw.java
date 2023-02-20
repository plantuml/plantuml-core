package net.sourceforge.plantuml.project.draw;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.project.LabelStrategy;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.lang.CenterBorderColor;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.utils.Direction;

public interface TaskDraw extends UDrawable {

	public TaskDraw getTrueRow();

	public void setColorsAndCompletion(CenterBorderColor colors, int completion, Url url, Display note);

	public Real getY(StringBounder stringBounder);

	public double getY(StringBounder stringBounder, Direction direction);

	public void drawTitle(UGraphic ug, LabelStrategy labelStrategy, double colTitles, double colBars);

	public double getTitleWidth(StringBounder stringBounder);

	public double getFullHeightTask(StringBounder stringBounder);

	public double getHeightMax(StringBounder stringBounder);

	public Task getTask();

	public FingerPrint getFingerPrint(StringBounder stringBounder);

	public FingerPrint getFingerPrintNote(StringBounder stringBounder);

	public double getX1(TaskAttribute taskAttribute);

	public double getX2(TaskAttribute taskAttribute);

}
