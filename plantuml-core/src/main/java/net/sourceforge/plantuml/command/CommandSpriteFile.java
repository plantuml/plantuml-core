package net.sourceforge.plantuml.command;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.FileUtils;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.sprite.Sprite;
import net.sourceforge.plantuml.sprite.SpriteImage;
import net.sourceforge.plantuml.sprite.SpriteSvg;
import net.sourceforge.plantuml.utils.LineLocation;
import net.sourceforge.plantuml.utils.Log;

public class CommandSpriteFile extends SingleLineCommand2<TitledDiagram> {

	public static final CommandSpriteFile ME = new CommandSpriteFile();

	private CommandSpriteFile() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSpriteFile.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("sprite"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("\\$?"), //
				new RegexLeaf("NAME", "([-%pLN_]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("FILE", "([^<>%g#]*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram system, LineLocation location, RegexResult arg) {
		return CommandExecutionResult.ok();
	}

}
