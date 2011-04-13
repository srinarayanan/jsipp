package jsipp.sip;

import java.text.ParseException;

import javax.sip.message.MessageFactory;
import javax.sip.message.Request;

import org.cafesip.sipunit.SipStack;

public class MessageCreator {
	public MessageCreator(SipStack sipStack) {
		messageFactory = sipStack.getMessageFactory();
	}

	private MessageFactory messageFactory;
	public Request createRequest(String request) {
		try {
			Request sipRequest=messageFactory.createRequest(request);
			return sipRequest;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
