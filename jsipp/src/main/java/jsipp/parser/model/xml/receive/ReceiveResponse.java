package jsipp.parser.model.xml.receive;

public class ReceiveResponse extends ReceiveAction {

	int code;

	public ReceiveResponse(String a) {
		this.code = Integer.parseInt(a);
	}

	public ReceiveResponse(String response, boolean optional) {
		this.code = Integer.parseInt(response);
		this.optional = optional;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ReceiveResponse [ " + code + ", optional " + optional + "]";
	}

}
