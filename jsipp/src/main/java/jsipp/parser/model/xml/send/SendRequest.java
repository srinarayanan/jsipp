package jsipp.parser.model.xml.send;

public class SendRequest extends SendAction {

	public SendRequest(String content) {
		super(content);
	}

//	@Override
//	public void execute() {
//
//		transceiver.send(getContent());
//	}

	@Override
	public String toString() {

		return "SendRequest:" + this.content;
	}

}
