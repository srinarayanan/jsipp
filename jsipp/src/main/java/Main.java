import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import jsipp.parser.SimpleParser;
import jsipp.parser.model.xml.Action;
import jsipp.parser.model.xml.send.SendAction;
import jsipp.sip.Transceiver;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class
			.getName());
	
	public static void main(String argsp[]) throws Exception {
		Transceiver transceiver = init();
		List<Action> actions = new SimpleParser(new File(
				"src/test/java/sip/junit/test/uac.xml")).parser();
		Action ac = actions.get(0);
		SendAction sendAction = (SendAction) ac;
		sendAction.getContent();
		String request = sendAction.getContent();
		logger.info(request);
		transceiver.send(request);
		transceiver.expect();
	}

	private static Transceiver init() throws Exception {
		Properties properties = new Properties();
		// properties1.setProperty("javax.sip.IP_ADDRESS", "127.0.0.1");
		String transport = "tcp";
		String peerHostPort1 = "127.0.0.1:5080";
		properties.setProperty("javax.sip.OUTBOUND_PROXY", peerHostPort1 + "/"
				+ transport);
		properties.setProperty("javax.sip.STACK_NAME", "sender");
		properties.setProperty("sipunit.BINDADDR", "127.0.0.1");
		properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
				"logs/b2buadebug1.txt");
		properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
				"logs/b2bualog1.xml");
		properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");

		return new Transceiver(7070, properties);
	}
}
