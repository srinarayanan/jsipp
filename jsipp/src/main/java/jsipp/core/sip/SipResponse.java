package jsipp.core.sip;

import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.message.Response;

public class SipResponse {
	private int code;
	private String cSeq;
	private String callID;

	public SipResponse(Response response) {
		this.response = response;
		this.code = response.getStatusCode();
		this.cSeq = response.getHeader(CSeqHeader.NAME).toString();
		this.callID = response.getHeader(CallIdHeader.NAME).toString();
	}

	private Response response;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SipResponse) {
			SipResponse other = (SipResponse) obj;
			return this.code == other.code && this.cSeq.equals(other.cSeq)
					&& this.callID.equals(other.callID);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {

		return this.callID.hashCode();
	}

	@Override
	public String toString() {

		return "SipResponse = code = " + this.code + ", call id =" + this.callID + " , cseq = " + this.cSeq;
	}
}
