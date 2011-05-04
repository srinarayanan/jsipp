package jsipp.parser.model.xml.receive;

import jsipp.core.constants.MessageType;

public class ReceiveRequest extends ReceiveAction {
	private String method;

	public ReceiveRequest(String request, boolean optional) {
		super(MessageType.REQUEST);
		this.method = request;
		this.optional = optional;
		setReceive(true);
	}

	@Override
	public String toString() {

		return "ReceiveRequest [ " + method + "]";
	}
}
