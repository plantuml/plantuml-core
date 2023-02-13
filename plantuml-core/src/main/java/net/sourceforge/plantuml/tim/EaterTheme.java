package net.sourceforge.plantuml.tim;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import net.sourceforge.plantuml.AFile;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.preproc.FileWithSuffix;
import net.sourceforge.plantuml.preproc.ImportedFiles;
import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.preproc.ReadLineReader;
import net.sourceforge.plantuml.preproc2.PreprocessorUtils;
import net.sourceforge.plantuml.security.SURL;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.theme.ThemeUtils;

public class EaterTheme extends Eater {

	private String realName;
	private String name;
	private String from;
	private TContext context;
	private final ImportedFiles importedFiles;

	public EaterTheme(StringLocated s, ImportedFiles importedFiles) {
		super(s);
		this.importedFiles = importedFiles;
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!theme");
		skipSpaces();
		this.name = this.eatAllToEnd();

		final int x = this.name.toLowerCase().indexOf(" from ");
		if (x != -1) {
			this.from = this.name.substring(x + " from ".length());
			this.name = this.name.substring(0, x);
			this.context = context;
		}

		this.realName = context.applyFunctionsAndVariables(memory, getLineLocation(), this.name);

	}

	public final ReadLine getTheme() throws EaterException {
		if (from == null) {
			try {
				final ReadLine reader = ThemeUtils.getReaderTheme(realName);
				if (reader != null)
					return reader;

				final AFile localFile = importedFiles.getAFile(ThemeUtils.getFilename(realName));
				if (localFile != null && localFile.isOk()) {
					final BufferedReader br = localFile.getUnderlyingFile().openBufferedReader();
					if (br != null)
						return ReadLineReader.create(br, "theme " + realName);
				}
			} catch (IOException e) {
				Logme.error(e);
			}
			throw EaterException.located("Cannot load " + realName);

		} else if (from.startsWith("http://") || from.startsWith("https://")) {
			final SURL url = SURL.create(ThemeUtils.getFullPath(from, realName));
			if (url == null)
				throw EaterException.located("Cannot open URL");

			try {
				return PreprocessorUtils.getReaderInclude(url, getLineLocation(), UTF_8);
			} catch (UnsupportedEncodingException e) {
				Logme.error(e);
				throw EaterException.located("Cannot decode charset");
			}
		}

		try {
			final FileWithSuffix file = context.getFileWithSuffix(from, realName);
			final Reader tmp = file.getReader(UTF_8);
			if (tmp == null)
				throw EaterException.located("No such theme " + realName);

			return ReadLineReader.create(tmp, "theme " + realName);
		} catch (IOException e) {
			Logme.error(e);
			throw EaterException.located("Cannot load " + realName);
		}

	}

	public String getName() {
		return name;
	}

}
