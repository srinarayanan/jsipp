package jsipp.parser.model.xml;

import java.util.ArrayList;
import java.util.List;

public class Sceneraio {
	List<Action> actions = new ArrayList<Action>();

	public void addAction(Action action) {
		actions.add(action);
	}

	public List<Action> getActions() {

		return actions;
	}
}
