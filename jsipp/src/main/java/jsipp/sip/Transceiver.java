package jsipp.sip;

import java.util.Properties;

import javax.sip.ListeningPoint;
import javax.sip.SipFactory;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.message.Request;

import jsipp.core.MessageReceiver;

public class Transceiver {
	private SessionTracker s;
	private MessageCreator messageCreator;
	int localPort;
	private SipStack sipStack;
	private Transmitter transmitter;
	private Receiver receiver;
	private Properties properties;
	private SipProvider sipProvider;

	public Transceiver(int localPort, Properties properties, MessageReceiver messageReceiver)
			throws Exception {
		this.localPort = localPort;
		this.properties = properties;
		receiver = new Receiver(messageReceiver);
		init();

	}

	public void init() throws Exception {

		sipStack = SipFactory.getInstance().createSipStack(properties);
		ListeningPoint tcp = sipStack.createListeningPoint("127.0.0.1",
				localPort, ListeningPoint.TCP);

		sipProvider = sipStack.createSipProvider(tcp);

		sipProvider.addSipListener(receiver);
		transmitter = new Transmitter(sipProvider);
		messageCreator = new MessageCreator(sipStack);

	}

	public void send(String request) {
		Request sipRequest = messageCreator.createRequest(request);
		transmitter.send(sipRequest);
	}

	public void expectResp(String string) {

	}

	public void expect() {

		long arg1 = 10;

	}
}
