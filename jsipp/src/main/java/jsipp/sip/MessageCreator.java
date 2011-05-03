package jsipp.sip;

import gov.nist.javax.sip.header.ContentType;
import gov.nist.javax.sip.message.MessageFactoryImpl;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.sip.SipStack;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;



public class MessageCreator {

	private static final Logger logger = Logger.getLogger(MessageCreator.class
			.getName());
	MessageFiller me = new SimpleMessageFillerImpl();

	public MessageCreator(SipStack sipStack) {
		messageFactory = new MessageFactoryImpl();
	}

	private MessageFactory messageFactory;

	public Request createRequest(String request) {

		request = request.trim();
		logger.info("After trim=" + request);
		String[] ar = request.split("\n\n");
		String reqString = ar[0] + "\r\n\r\n";
		String filledSring = me.fill(reqString);
		logger.info(filledSring);
		// String []headers=reqString.split("\n");

		try {
			Request sipRequest = messageFactory.createRequest(filledSring);
			if (ar.length == 2) {
				sipRequest.setContent(ar[1], new ContentType("text", "plain"));
			}
			logger.info("Request=" + sipRequest);
			return sipRequest;
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return null;

	}
}
