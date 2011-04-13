package jsipp.parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jsipp.parser.exception.JsippParseException;
import jsipp.parser.model.xml.Action;
import jsipp.parser.model.xml.Sceneraio;
import jsipp.parser.model.xml.constants.ReceiveAttributes;
import jsipp.parser.model.xml.constants.SendAttributes;
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

	public static void main(String ar[]) throws SAXException, IOException,
			ParserConfigurationException {
		Sceneraio sc = new SimpleParser().parser(new File(
				"src/test/java/sip/junit/test/uac.xml"));
		for (Action action : sc.getActions()) {
			logger.info("Processing Action : " + action);
		}

	}

	public Sceneraio parser(File file) throws SAXException, IOException,
			ParserConfigurationException {
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
