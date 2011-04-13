package jsipp.sip;

import javax.sip.SipException;
import javax.sip.message.Request;

public class Transmitter {

	private org.cafesip.sipunit.SipStack sipStack;

	public Transmitter(org.cafesip.sipunit.SipStack sipStack) {
		this.sipStack = sipStack;
	}

	public void send(Request sipRequest) {
		try {
			sipStack.getSipProvider().sendRequest(sipRequest);
		} catch (SipException e) {

			e.printStackTrace();
		}

	}

}
