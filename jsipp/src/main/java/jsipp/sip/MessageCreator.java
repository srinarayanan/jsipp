package jsipp.sip;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.sip.message.MessageFactory;
import javax.sip.message.Request;

import org.cafesip.sipunit.SipStack;

public class MessageCreator {

	private static final Logger logger = Logger.getLogger(MessageCreator.class
			.getName());
	MessageFiller me = new SimpleMessageFillerImpl();

	public MessageCreator(SipStack sipStack) {
		messageFactory = sipStack.getMessageFactory();
	}

	private MessageFactory messageFactory;

	public Request createRequest(String request) {

		String[] ar = request.split("\n\n");
		String reqString = ar[0];

		String filledSring = me.fill(reqString);
		logger.info(filledSring);
		// String []headers=reqString.split("\n");

		try {
			Request sipRequest = messageFactory.createRequest(filledSring.trim());
			return sipRequest;
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return null;

	}
}
