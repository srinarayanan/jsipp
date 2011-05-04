package jsipp.parser.model.xml;

public abstract class Action {

	
	private boolean isReceive = false;

	public void setReceive(boolean isReceive) {
		this.isReceive = isReceive;
	}

	public boolean isReceive() {
		return this.isReceive;
	}

}
