package jsipp.parser.model.xml.receive;

import jsipp.parser.model.xml.Action;

public abstract class ReceiveAction extends Action {
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	boolean optional = false;

	public boolean isOptional() {
		// TODO Auto-generated method stub
		return optional;
	}

}
