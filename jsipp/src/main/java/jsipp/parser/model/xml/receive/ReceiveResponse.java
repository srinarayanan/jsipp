package jsipp.parser.model.xml.receive;

import jsipp.core.constants.MessageType;

public class ReceiveResponse extends ReceiveAction {

	int code;

	public ReceiveResponse(String response, boolean optional) {
		super(MessageType.RESPONSE);
		this.code = Integer.parseInt(response);
		this.optional = optional;
		setReceive(true);
	}

	@Override
	public String toString() {

		return "ReceiveResponse [ " + code + ", optional " + optional + "]";
	}

	public int getStatusCode() {

		return this.code;
	}

}
