package jsipp.parser.model.xml.constants;

public enum ReceiveAttributes {
	RESPONSE("response"), //
	REQUEST("request"), //
	OPTIONAL("optional");
	private String value;

	public String getValue() {
		return value;
	}

	private ReceiveAttributes(String value) {
		this.value = value;
	}
}
