import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class readingXML {
	public static void main(String[] args) {
		//readSimpleDom();
		//readDomNodes();
		Document doc = createXMLDoc();
		writeDocToXML(doc, "junk.xml");
	}

	//public static Document createXMLDoc(){
	public static Document createXMLDoc(){
		   Document doc = null;
		   try {
		      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		      // root element
		      Element rootElement = doc.createElement("Root");
		      doc.appendChild(rootElement);
		      //  new element
		      Element element = doc.createElement("Item");
		      rootElement.appendChild(element);
		      // new item
		      Element item = doc.createElement("Title1");
		      item.appendChild(doc.createTextNode("Info 1"));
		      element.appendChild(item);
		      // another new item
		      item = doc.createElement("Title2");   //reusing item which is Element class
		      item.appendChild(doc.createTextNode("Info 2"));
		      element.appendChild(item);
		   } catch (ParserConfigurationException e) {
		      e.printStackTrace();
		   }
		   return doc;
		}
	
	//public static void writeDocToXML(Document doc, String fileName){ 
	public static void writeDocToXML(Document doc, String fileName){ 
		   Transformer transformer;
		   try {
		      transformer = TransformerFactory.newInstance().newTransformer();
		      //format transformer
		      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		      //next 3 lines transforms document into xml file
		      DOMSource source = new DOMSource(doc);
		      StreamResult result = new StreamResult(new File(fileName));
		      transformer.transform(source, result);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
	}
	
	//public static void readDomNodes(){
	public static void readDomNodes(){
		   Document document;
		   try {
		      document = DocumentBuilderFactory.newInstance()
		         .newDocumentBuilder().parse("novel.xml");
		      Node node = document.getDocumentElement();
		      System.out.println("Root node name: " + node.getNodeName());
		      NodeList list= node.getChildNodes();
		      for(int x = 0; x < list.getLength(); x++){
		         printNode(list.item(x));
		      }
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	
	//public static void readSimpleDom(){
	public static void readSimpleDom(){
		   Document document;
		   try {
		      document = DocumentBuilderFactory.newInstance()
		         .newDocumentBuilder().parse("hello.xml");

		      Element root = document.getDocumentElement();
		      Node nextNode = root.getFirstChild();
		      System.out.println(nextNode.getNodeName());
		      System.out.println(nextNode.getNodeType());
		      System.out.println(nextNode.getNodeValue());
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	
	public static void printNode(Node node){
		System.out.println("Start of new node:");
		System.out.println("\tType: " + nodeType(node));
		System.out.println("\tName:" + node.getNodeName());
		if(node.getTextContent().trim().length() > 0){
			System.out.println("\tText:" + node.getTextContent().trim());
		}
		else{
			System.out.println("\tText:" + "tab(s)");
		}System.out.println("\tAttribute: " + node.getAttributes());
		System.out.println("End of node:");
	}

	public static String nodeType(Node node){
		String type = "?????";
		if (node.getNodeType()==Node.ELEMENT_NODE) type = "Element";
		if (node.getNodeType()==Node.TEXT_NODE) type = "Text";
		if (node.getNodeType()==Node.DOCUMENT_TYPE_NODE) type = "Document type";
		if (node.getNodeType()==Node.ENTITY_NODE) type = "Document type";
		if (node.getNodeType()==Node.ENTITY_REFERENCE_NODE) type = "Entity reference";
		if (node.getNodeType()==Node.NOTATION_NODE) type = "Notation";
		if (node.getNodeType()==Node.COMMENT_NODE) type = "Comment";
		if (node.getNodeType()==Node.CDATA_SECTION_NODE) type = "CDATA Section";
		if (node.getNodeType()==Node.ATTRIBUTE_NODE) type = "Attribute";
		if (node.getNodeType()==Node.PROCESSING_INSTRUCTION_NODE) type = "Processing Instruction";
		return type;
	}
}
