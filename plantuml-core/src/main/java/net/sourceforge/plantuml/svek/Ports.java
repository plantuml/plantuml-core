package net.sourceforge.plantuml.svek;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sourceforge.plantuml.utils.SignatureUtils;

import java.util.Objects;

public class Ports {

	private final Map<String, PortGeometry> ids = new HashMap<String, PortGeometry>();

	public static String encodePortNameToId(String portName) {
		return "p" + SignatureUtils.getMD5Hex(portName);
	}

	@Override
	public String toString() {
		return ids.toString();
	}

	public Ports translateY(double deltaY) {
		final Ports result = new Ports();
		for (Map.Entry<String, PortGeometry> ent : ids.entrySet())
			result.ids.put(ent.getKey(), ent.getValue().translateY(deltaY));

		return result;
	}

	public void add(String portName, int score, double position, double height) {
		final String id = encodePortNameToId(Objects.requireNonNull(portName));
		final PortGeometry already = ids.get(id);
		if (already == null || already.getScore() < score)
			ids.put(id, new PortGeometry(id, position, height, score));
	}

	public void addThis(Ports other) {
		for (Entry<String, PortGeometry> ent : other.ids.entrySet()) {
			final String key = ent.getKey();
			final PortGeometry already = ids.get(key);
			if (already == null || already.getScore() < ent.getValue().getScore())
				ids.put(key, ent.getValue());
		}
	}

	public Collection<PortGeometry> getAllPortGeometry() {
		final List<PortGeometry> result = new ArrayList<PortGeometry>(ids.values());
		Collections.sort(result);
		return Collections.unmodifiableCollection(result);
	}

}
