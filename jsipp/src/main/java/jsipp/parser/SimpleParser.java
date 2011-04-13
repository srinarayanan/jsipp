package jsipp.parser;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import jsipp.parser.exception.JsippParseException;
import jsipp.parser.model.xml.Sceneraio;
import jsipp.parser.model.xml.constants.ReceiveAttributes;
import jsipp.parser.model.xml.constants.SippActions;
import jsipp.parser.model.xml.receive.ReceiveAction;
import jsipp.parser.model.xml.receive.ReceiveRequest;
import jsipp.parser.model.xml.receive.ReceiveResponse;
import jsipp.parser.model.xml.send.SendAction;
import jsipp.parser.model.xml.send.SendRequest;
import jsipp.parser.model.xml.send.SendResponse;

public class SimpleParser {
	private static Logger logger = Logger.getLogger(SimpleParser.class
			.getName());

	public static void main(String ar[]) throws Exception {
		Sceneraio sc = new SimpleParser().parser(new File(
				"src/test/java/sip/junit/test/uac.xml"));
		sc.play();
		

	}

	public Sceneraio parser(File file) throws Exception {
		// TODO Auto-generated method stub
		Sceneraio sceneraio = new Sceneraio();
		logger.info(file.isFile() + "");

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setCoalescing(true);
		Document doc = builderFactory.newDocumentBuilder().parse(file);

		NodeList list = doc.getElementsByTagName("*");

		for (int i = 0; i < list.getLength(); i++) {

			// Get element
			Element element = (Element) list.item(i);
			switch (SippActions.get(element.getNodeName())) {
			case SEND:
				SendAction sendAction = parseSend(element);
				sceneraio.addAction(sendAction);
				break;
			case RECV:
				ReceiveAction recvAction = parseReceive(element);
				if (!recvAction.isOptional()) {
					sceneraio.addAction(recvAction);
				}
				break;
			case PAUSE:
				break;
			}

		}
		return sceneraio;
	}

	private SendAction parseSend(Element element) {

		String text = element.getTextContent().trim();
		if (text.startsWith("SIP/2.0")) {
			return new SendResponse(text);
		}
		return new SendRequest(text);

	}

	private ReceiveAction parseReceive(Element element) {
		ReceiveAction r = null;
		String value = element.getAttribute(ReceiveAttributes.OPTIONAL
				.getValue());
		boolean optional = value != null ? Boolean.parseBoolean(value) : false;

		String request = element.getAttribute(ReceiveAttributes.REQUEST
				.getValue());
		String response = element.getAttribute(ReceiveAttributes.RESPONSE
				.getValue());
		if (!request.isEmpty()) {
			r = new ReceiveRequest(request, optional);
			// TODO take care of action - the contnet as well
		} else if (!response.isEmpty()) {
			r = new ReceiveResponse(response, optional);
		} else {
			throw new JsippParseException("requred attribute missing");
		}

		return r;
	}
}
