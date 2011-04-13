package jsipp.parser.model.xml.constants;

import java.util.HashMap;
import java.util.Map;

public enum SippActions {
	SEND("send"), //
	RECV("recv"), //
	PAUSE("pause"), //
	NOT_SUPPORTED("NOTsupported");

	private String elements;

	public String getElements() {
		return elements;
	}

	private SippActions(String ele) {
		this.elements = ele;
	}

	private static Map<String, SippActions> map = new HashMap<String, SippActions>();
	static {
		for (SippActions e : SippActions.values())
			map.put(e.getElements(), e);
	}

	public static SippActions get(String value) {
		SippActions actions = map.get(value);
		return actions != null ? actions : NOT_SUPPORTED;

	}
}
