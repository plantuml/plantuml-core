package net.sourceforge.plantuml;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.sourceforge.plantuml.utils.CharsetUtils.charsetOrDefault;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.code.AsciiEncoder;
import net.sourceforge.plantuml.code.Transcoder;
import net.sourceforge.plantuml.code.TranscoderUtil;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemErrorPreprocessor;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.preproc.Defines;
import net.sourceforge.plantuml.preproc.FileWithSuffix;
import net.sourceforge.plantuml.preproc2.PreprocessorModeSet;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.text.BackSlash;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.TimLoader;
import net.sourceforge.plantuml.utils.LineLocationImpl;
import net.sourceforge.plantuml.utils.StartUtils;
import net.sourceforge.plantuml.version.Version;

public class BlockUml {

	private final List<StringLocated> rawSource;
	private final List<StringLocated> data;
	private List<StringLocated> debug;
	private Diagram system;
	private final Defines localDefines;
	private final ISkinSimple skinParam;
	private final Set<FileWithSuffix> included = new HashSet<>();

	public Set<FileWithSuffix> getIncluded() {
		return Collections.unmodifiableSet(included);
	}

	@Deprecated
	BlockUml(String... strings) {
		this(convert(strings), Defines.createEmpty(), null, null, null);
	}


	public String getFlashData() {
		final StringBuilder sb = new StringBuilder();
		for (StringLocated line : data) {
			sb.append(line.getString());
			sb.append('\r');
			sb.append(BackSlash.CHAR_NEWLINE);
		}
		return sb.toString();
	}

	public static List<StringLocated> convert(String... strings) {
		return convert(Arrays.asList(strings));
	}

	public static List<StringLocated> convert(List<String> strings) {
		final List<StringLocated> result = new ArrayList<>();
		LineLocationImpl location = new LineLocationImpl("block", null);
		for (String s : strings) {
			location = location.oneLineRead();
			result.add(new StringLocated(s, location));
		}
		return result;
	}

	private boolean preprocessorError;

	/**
	 * @deprecated being kept for backwards compatibility, perhaps other projects
	 *             are using this?
	 */
	@Deprecated
	public BlockUml(List<StringLocated> strings, Defines defines, ISkinSimple skinParam, PreprocessorModeSet mode) {
		this(strings, defines, skinParam, mode, charsetOrDefault(mode.getCharset()));
	}

	public BlockUml(List<StringLocated> strings, Defines defines, ISkinSimple skinParam, PreprocessorModeSet mode,
			Charset charset) {
		this.rawSource = new ArrayList<>(strings);
		this.localDefines = defines;
		this.skinParam = skinParam;
		final String s0 = strings.get(0).getTrimmed().getString();
		if (StartUtils.startsWithSymbolAnd("start", s0) == false)
			throw new IllegalArgumentException();

		if (mode == null) {
			this.data = new ArrayList<>(strings);
		} else {
			final TimLoader timLoader = new TimLoader(mode.getImportedFiles(), defines, charset,
					(DefinitionsContainer) mode);
			this.included.addAll(timLoader.load(strings));
			this.data = timLoader.getResultList();
			this.debug = timLoader.getDebug();
			this.preprocessorError = timLoader.isPreprocessorError();
		}
	}


	public Diagram getDiagram() {
		if (system == null) {
			if (preprocessorError)
				system = new PSystemErrorPreprocessor(data, debug);
			else
				system = new PSystemBuilder().createPSystem(data, rawSource,
						skinParam == null ? Collections.<String, String>emptyMap() : skinParam.values());
		}
		return system;
	}

	public final List<StringLocated> getData() {
		return data;
	}


	public long lastModified() {
		return (Version.compileTime() / 1000L / 60) * 1000L * 60 + Version.beta() * 1000L * 3600;
	}

	public boolean isStartDef(String name) {
		final String signature = "@startdef(id=" + name + ")";
		return data.get(0).getString().equalsIgnoreCase(signature);
	}

	public List<String> getDefinition(boolean withHeader) {
		final List<String> result = new ArrayList<>();
		for (StringLocated s : data)
			result.add(s.getString());

		if (withHeader)
			return Collections.unmodifiableList(result);

		return Collections.unmodifiableList(result.subList(1, result.size() - 1));
	}

	public Defines getLocalDefines() {
		return localDefines;
	}

}
