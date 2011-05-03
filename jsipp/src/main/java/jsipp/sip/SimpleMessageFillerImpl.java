package jsipp.sip;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jsipp.parser.model.xml.constants.SippPlaceHolders;

public class SimpleMessageFillerImpl implements MessageFiller {

	private static final Logger logger = Logger
			.getLogger(SimpleMessageFillerImpl.class.getName());
	// INVITE sip:[service]@[remote_ip]:[remote_port] SIP/2.0
	// Via: SIP/2.0/[transport] [local_ip]:[local_port];branch=[branch]
	// From: sipp <sip:sipp@[local_ip]:[local_port]>;tag=[call_number]
	// To: sut <sip:[service]@[remote_ip]:[remote_port]>
	// Call-ID: [call_id]
	// CSeq: 1 INVITE
	// Contact: sip:sipp@[local_ip]:[local_port]
	// Max-Forwards: 70
	// Subject: Performance Test
	// Content-Type: application/sdp
	// Content-Length: [len]

	// SERVICE("service"), //
	// REMOTE_PORT("remote_port"), //
	// REMOTE_IP("remote_ip"), //
	// TRANSPORT("transport"), //
	// CALL_NUMBER("call_number"), //
	// LOCAL_IP("local_port"), //
	// LOCAL_PORT("local_port"), //
	// CALL_ID("call_id"), //
	// LEN("len");

	private static Map<SippPlaceHolders, String> fillers = new HashMap<SippPlaceHolders, String>();
	static {
		fillers.put(SippPlaceHolders.SERVICE, "bob");
		fillers.put(SippPlaceHolders.REMOTE_IP, "127.0.0.1");
		fillers.put(SippPlaceHolders.TRANSPORT, "TCP");
		fillers.put(SippPlaceHolders.REMOTE_PORT, "7071");
		fillers.put(SippPlaceHolders.LOCAL_IP, "127.0.0.1");
		fillers.put(SippPlaceHolders.LOCAL_PORT, "7070");
		fillers.put(SippPlaceHolders.BRANCH, "newbracnk");
		fillers.put(SippPlaceHolders.CALL_ID, "newcallid000001");
		fillers.put(SippPlaceHolders.LEN, "0");
		fillers.put(SippPlaceHolders.CALL_NUMBER, "2222");
		fillers.put(SippPlaceHolders.CSEQ, "1");

	}

	public String fill(String reqString) {
		for (SippPlaceHolders sippPlaceHolder : SippPlaceHolders.values()) {

			logger.info("" + sippPlaceHolder + "|"
					+ fillers.get(sippPlaceHolder));
			reqString = reqString.replace("[" + sippPlaceHolder.getValue()
					+ "]", fillers.get(sippPlaceHolder));
		}
		logger.info("Filled Req String :\n " + reqString);
		return reqString;

	}

}
