package jsipp.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import jsipp.parser.model.xml.send.SendAction;
import jsipp.sip.Transceiver;

public class MessageSender {

	private static final Logger logger = Logger.getLogger(MessageSender.class
			.getName());
	private Queue<SendAction> sendActions = new ConcurrentLinkedQueue<SendAction>();
	private Transceiver transceiver;
	private MessageReceiver messageReceiver;

	public MessageSender(Transceiver transceiver) {
		this.transceiver = transceiver;
	}

	public void enque(SendAction action) {
		sendActions.add(action);

	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;

	}

	@Override
	public String toString() {

		return "MessageSender = " + sendActions;
	}

	public void send() {

		SendAction sendAction = sendActions.poll();
		if (sendAction != null) {
			messageReceiver.listen();
			transceiver.send(sendAction.getContent());
		} else {

		}

	}

	public boolean proceed() {
		if (sendActions.isEmpty()) {
			logger.info("DONE");
			return false;
		} else {
			logger.info("processing next");
			send();
			return true;
		}

	}
}
