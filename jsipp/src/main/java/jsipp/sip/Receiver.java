package jsipp.sip;

import java.util.logging.Logger;

import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipListener;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;

import jsipp.core.MessageReceiver;

public class Receiver implements SipListener {
	private MessageReceiver messageReceiver;

	public Receiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	private static final Logger logger = Logger.getLogger(Receiver.class
			.getName());

	public void processDialogTerminated(DialogTerminatedEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void processIOException(IOExceptionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void processRequest(RequestEvent arg0) {
		logger.info("processResponse" + arg0.getRequest());
		messageReceiver.receiveRequest(arg0.getRequest());
	}

	public void processResponse(ResponseEvent arg0) {
		logger.info("processResponse" + arg0.getResponse());
		messageReceiver.receiveResponse(arg0.getResponse());

	}

	public void processTimeout(TimeoutEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void processTransactionTerminated(TransactionTerminatedEvent arg0) {
		// TODO Auto-generated method stub

	}

}
