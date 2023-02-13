package net.sourceforge.plantuml.timingdiagram;

import java.util.Objects;

public class TickInPlayer {

	private final Player player;
	private final TimeTick tick;

	public TickInPlayer(Player player, TimeTick tick) {
		this.player = Objects.requireNonNull(player);
		this.tick = tick;
	}

	public final Player getPlayer() {
		return player;
	}

	public final TimeTick getTick() {
		return tick;
	}

}
