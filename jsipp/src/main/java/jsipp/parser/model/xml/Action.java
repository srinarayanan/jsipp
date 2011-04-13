package jsipp.parser.model.xml;

import jsipp.sip.Transceiver;

public abstract class Action {
	public abstract void execute();

	protected Transceiver transceiver;

	public void setTx(Transceiver transceiver) {
		// TODO Auto-generated method stub
		this.transceiver = transceiver;
	}

}
