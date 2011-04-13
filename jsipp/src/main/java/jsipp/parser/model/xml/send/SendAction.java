package jsipp.parser.model.xml.send;

import jsipp.parser.model.xml.Action;

public abstract class SendAction extends Action {
	public SendAction(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	protected String content;
}
