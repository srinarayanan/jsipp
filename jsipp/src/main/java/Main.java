import java.util.logging.Logger;

import jsipp.core.ScriptPlayer;
import jsipp.core.config.Configurations;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String argsp[]) throws Exception {

		// Action ac = actions.get(0);
		// SendAction sendAction = (SendAction) ac;
		// sendAction.getContent();
		// String request = sendAction.getContent();
		// logger.info(request);
		// transceiver.send(request);
		// transceiver.expect();

		ScriptPlayer scriptPlayer = new ScriptPlayer(Configurations.getUacXML());
		scriptPlayer.play();
	}

}
