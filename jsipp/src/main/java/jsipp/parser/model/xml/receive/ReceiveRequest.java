package jsipp.parser.model.xml.receive;

public class ReceiveRequest extends ReceiveAction {
	private String method;

	public ReceiveRequest(String request, boolean optional) {
		this.method = request;
		this.optional = optional;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ReceiveRequest [ " + method + "]";
	}
}
