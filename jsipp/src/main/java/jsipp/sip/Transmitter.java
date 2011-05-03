package jsipp.sip;

import javax.sip.SipException;
import javax.sip.SipProvider;
import javax.sip.message.Request;

public class Transmitter {

	private SipProvider sipProvider;

	public Transmitter(SipProvider sipProvider) {
		this.sipProvider = sipProvider;
	}

	public void send(Request sipRequest) {

		// return sipPhone.sendRequestWithTransaction(sipRequest, false, null);
		// sipStack.
		try {
			sipProvider.sendRequest(sipRequest);
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
