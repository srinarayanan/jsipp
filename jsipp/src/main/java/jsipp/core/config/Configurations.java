package jsipp.core.config;

import java.util.Properties;

public class Configurations {
	public static Properties getTxProperties() {
		Properties properties = new Properties();
		// properties1.setProperty("javax.sip.IP_ADDRESS", "127.0.0.1");
		String transport = "tcp";
		String peerHostPort1 = "127.0.0.1:5080";
		properties.setProperty("javax.sip.OUTBOUND_PROXY", peerHostPort1 + "/"
				+ transport);
		properties.setProperty("javax.sip.STACK_NAME", "sender");
		properties.setProperty("sipunit.BINDADDR", "127.0.0.1");
		properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
				"logs/b2buadebug1.txt");
		properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
				"logs/b2bualog1.xml");
		properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
		return properties;
	}

	public static String getUacXML() {
		return "src/test/java/sip/junit/test/uac.xml";
	}
}
