package net.sourceforge.plantuml.command;

import java.util.Objects;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.Log;
import net.sourceforge.plantuml.version.Version;

public class ProtectedCommand<S extends Diagram> implements Command<S> {

	private final Command<S> cmd;

	public ProtectedCommand(Command<S> cmd) {
		this.cmd = Objects.requireNonNull(cmd);
	}

	public CommandExecutionResult execute(S system, BlocLines lines) {
		try {
			// WasmLog.log("...running " + cmd.getClass().getName() + " ...");
			final CommandExecutionResult result = cmd.execute(system, lines);
			// if (result.isOk()) {
			// // TRACECOMMAND
			// System.err.println("CMD = " + cmd.getClass());
			// }
			return result;
		} catch (Throwable t) {
			Log.error("Error " + t);
			Logme.error(t);
			String msg = "You should send a mail to plantuml@gmail.com or post to https://plantuml.com/qa with this log (V"
					+ Version.versionString() + ")";
			Log.error(msg);
			msg += " " + t.toString();
			return CommandExecutionResult.error(msg, t);
		}
	}

	public CommandControl isValid(BlocLines lines) {
		return cmd.isValid(lines);
	}

	public String[] getDescription() {
		return cmd.getDescription();
	}

}
