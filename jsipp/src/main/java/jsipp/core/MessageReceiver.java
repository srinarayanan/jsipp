package jsipp.core;

import gov.nist.javax.sip.message.SIPResponse;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import javax.sip.header.FromHeader;
import javax.sip.header.ToHeader;
import javax.sip.message.Message;
import javax.sip.message.Request;
import javax.sip.message.Response;

import jsipp.core.sip.SipResponse;
import jsipp.parser.model.xml.receive.ReceiveAction;
import jsipp.parser.model.xml.receive.ReceiveResponse;
import jsipp.sip.SimpleMessageFillerImpl;

public class MessageReceiver {

	private static final Logger logger = Logger.getLogger(MessageReceiver.class
			.getName());
	private Queue<ReceiveAction> receiveActions = new ConcurrentLinkedQueue<ReceiveAction>();
	private List<SipResponse> revceivedSipMessages = new LinkedList<SipResponse>();
	private MessageSender messageSender;
	private boolean listen;

	public void receiveRequest(Request request) {
		// TODO Auto-generated method stub

	}

	public synchronized void receiveResponse(Response response) {
		logger.info(">> receiveResponse");
		if (!isAlreadyReceived(response)) {
			ReceiveAction receiveAction = receiveActions.peek();
			while (receiveActions != null && receiveAction.isOptional()) {
				receiveActions.remove();
				receiveAction = receiveActions.peek();
			}
			if (receiveActions == null || !receiveAction.isResponse()) {
				// unexpected response received
			} else {
				ReceiveResponse receiveResponse = (ReceiveResponse) receiveAction;
				if (receiveResponse.getStatusCode() == response.getStatusCode()) {
					logger.info("Match - remove and proceed");
					receiveActions.remove(receiveResponse);
					addToReceived(response);
					messageSender.proceed();
				} else {
					// diff respose code response received
				}
			}
		}
		logger.info(" receiveResponse >>");

	}

	private void addToReceived(Response response) {
		SipResponse sipResponse = new SipResponse(response);
		if (!revceivedSipMessages.contains(sipResponse)) {
			SimpleMessageFillerImpl.setLastFrom(response.getHeader(FromHeader.NAME).toString());
			SimpleMessageFillerImpl.setLastTo(response.getHeader(ToHeader.NAME).toString());
			revceivedSipMessages.add(sipResponse);
		}

	}

	/**
	 * check retx
	 * 
	 * @param response
	 * @return
	 */
	private boolean isAlreadyReceived(Response response) {
		boolean rec = revceivedSipMessages.contains(new SipResponse(response));
		logger.info(">>isAlreadyReceived =" + rec);
		return rec;

	}

	public void enque(ReceiveAction action) {
		receiveActions.add(action);

	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;

	}

	@Override
	public String toString() {

		return "MessageReceiver =  " + receiveActions;
	}

	public void listen() {
		this.listen = true;

	}

	public void unListen() {
		this.listen = false;

	}

}
