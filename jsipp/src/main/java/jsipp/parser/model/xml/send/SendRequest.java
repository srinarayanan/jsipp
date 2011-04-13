package jsipp.parser.model.xml.send;

public class SendRequest extends SendAction {

	public SendRequest(String content) {
		super(content);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {

		return "SendRequest:" + this.content;
	}

}
