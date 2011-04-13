package jsipp.sip;

import java.util.Properties;

import javax.sip.message.Request;

import org.cafesip.sipunit.SipPhone;
import org.cafesip.sipunit.SipStack;

public class Transceiver {
	SessionTracker s;
	MessageCreator messageCreator;
	int localPort;

	public Transceiver(int localPort) throws Exception {
		this.localPort = localPort;
		Properties properties = new Properties();
		// properties1.setProperty("javax.sip.IP_ADDRESS", "127.0.0.1");
		String transport = "udp";
		String peerHostPort1 = "127.0.0.1:5070";
		properties.setProperty("javax.sip.OUTBOUND_PROXY", peerHostPort1 + "/"
				+ transport);
		properties.setProperty("javax.sip.STACK_NAME", "sender");
		properties.setProperty("sipunit.BINDADDR", "127.0.0.1");
		properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
				"logs/b2buadebug1.txt");
		properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
				"logs/b2bualog1.xml");
		properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
		init(properties);

		transmitter = new Transmitter(sipStack);
		messageCreator = new MessageCreator(sipStack);
	}

	private SipStack sipStack;
	private Transmitter transmitter;
	private Receiver receiver;
	SipPhone sipPhone;

	public void init(Properties properties) throws Exception {

		sipStack = new SipStack(SipStack.PROTOCOL_TCP, localPort, properties);
	}

	public void send(String request) {
		Request sipRequest = messageCreator.createRequest(request);
		transmitter.send(sipRequest);
	}

}
