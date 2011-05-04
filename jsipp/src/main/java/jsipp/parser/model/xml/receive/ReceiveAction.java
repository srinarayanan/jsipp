package jsipp.parser.model.xml.receive;

import jsipp.core.constants.MessageType;
import jsipp.parser.model.xml.Action;

public abstract class ReceiveAction extends Action {
	private MessageType messageType;

	public ReceiveAction(MessageType messageType) {
		this.messageType = messageType;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	boolean optional = false;
	boolean isResponse;

	public boolean isOptional() {

		return optional;
	}

	public boolean isResponse() {
		return this.messageType == MessageType.RESPONSE;
	}
}
