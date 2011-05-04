package jsipp.parser.model.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Sceneraio {
	private static Logger logger = Logger.getLogger(Sceneraio.class.getName());
	List<Action> actions = new ArrayList<Action>();
	public Sceneraio()  {
	}

	public void addAction(Action action) {
	
		actions.add(action);
	}

	public List<Action> getActions() {

		return actions;
	}

	
}
