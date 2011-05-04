package jsipp.core;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import jsipp.core.config.Configurations;
import jsipp.parser.SimpleParser;
import jsipp.parser.model.xml.Action;
import jsipp.parser.model.xml.receive.ReceiveAction;
import jsipp.parser.model.xml.send.SendAction;
import jsipp.sip.Transceiver;

public class Script {

	private static final Logger logger = Logger.getLogger(Script.class
			.getName());
	private List<Action> actions;
	/**
	 * The message receiver gets notified of SIP messages
	 */
	private MessageReceiver messageReceiver = new MessageReceiver();
	private MessageSender messageSender;

	private Transceiver transceiver;

	public Script(String scriptFileName) throws Exception {
		this.actions = new SimpleParser(new File(scriptFileName)).parser();
		this.transceiver = new Transceiver(7070,
				Configurations.getTxProperties(), messageReceiver);
		this.messageSender = new MessageSender(this.transceiver);

		this.messageReceiver.setMessageSender(this.messageSender);
		this.messageSender.setMessageReceiver(this.messageReceiver);
	}

	public void play() {

		for (Action action : actions) {
			if (action.isReceive()) {
				messageReceiver.enque((ReceiveAction) action);
			} else {
				messageSender.enque((SendAction) action);
			}
		}
		logger.info("" + messageReceiver);
		logger.info("" + messageSender);
		messageSender.proceed();

	}

}
