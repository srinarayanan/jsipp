package jsipp.parser.model.xml.constants;

public enum SippPlaceHolders {

	SERVICE("service"), //
	REMOTE_PORT("remote_port"), //
	REMOTE_IP("remote_ip"), //
	TRANSPORT("transport"), //
	CALL_NUMBER("call_number"), //
	LOCAL_IP("local_ip"), //
	LOCAL_PORT("local_port"), //
	CALL_ID("call_id"), //
	LEN("len"), BRANCH("branch");

	private String value;

	public String getValue() {
		return value;
	}

	private SippPlaceHolders(String value) {
		this.value = value;
	}
}
