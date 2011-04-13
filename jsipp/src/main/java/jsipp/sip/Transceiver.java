package jsipp.sip;

import java.util.Properties;

import javax.sip.message.Request;

import org.cafesip.sipunit.SipPhone;
import org.cafesip.sipunit.SipStack;

public class Transceiver {
	private SessionTracker s;
	private MessageCreator messageCreator;
	int localPort;
	private SipStack sipStack;
	private Transmitter transmitter;
	private Receiver receiver;
	SipPhone sipPhone;

	public Transceiver(int localPort,Properties properties) throws Exception {
		this.localPort = localPort;
		
		init(properties);

		transmitter = new Transmitter(sipStack);
		messageCreator = new MessageCreator(sipStack);
	}

	public void init(Properties properties) throws Exception {

		sipStack = new SipStack(SipStack.PROTOCOL_TCP, localPort, properties);
	}

	public void send(String request) {
		Request sipRequest = messageCreator.createRequest(request);
		transmitter.send(sipRequest);
	}

}
