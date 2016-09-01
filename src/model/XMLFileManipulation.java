package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import model.globals.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class XMLFileManipulation {
	
	private DocumentBuilder docBuilder;
	private Document doc;
	
	public XMLFileManipulation(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public XMLFileManipulation(String FilePath){
		try{
			File fXmlFile = new File(FilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			docBuilder = dbFactory.newDocumentBuilder();
			doc = docBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * buildElements builds elements on an xml,
	 * -elements	= A map<ElementName, ElementHierarchy>
	 *  
	 */
	public void buildElements(HashMap<String, Integer> elements){
		doc = (Document) docBuilder.newDocument();
		Element rootElement = (Element) doc.getFirstChild();
		Element TempElement = null;

        Map<String, Integer> sortedMapAsc = Util.sortByComparator(elements, true);
        int prevHierarchy = 0;
        
		for(Entry<String, Integer> element : sortedMapAsc.entrySet()){
			String name = element.getKey();
			int hierarchy = element.getValue();
			
			
			if (rootElement == null || hierarchy == 0){//Create first element if doesn't exist.
				rootElement = doc.createElement((String)name);
			    doc.appendChild(rootElement);
			}else if(hierarchy <= prevHierarchy){
				if(rootElement.getParentNode() != null){
					rootElement = (Element) rootElement.getParentNode();
				}
				TempElement = doc.createElement((String)name);
				rootElement.appendChild(TempElement);
				rootElement = TempElement;

			}else{
				TempElement = doc.createElement((String)name);
				rootElement.appendChild(TempElement);
				rootElement = TempElement;
			}
			
			prevHierarchy = hierarchy;
			//TODO Make possible hierarchy.
			//Hierarchy is thought to be OK (0,1,2....)
			//Make if not OK (2,0,1....) 
		}
	}
	
 	
	/*
	 * saveXML saves all elements created in the given path
	 * -path		= The path of the file to be created
	 */
	public void saveXML(String path) throws TransformerException, FileNotFoundException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(path));
		transformer.transform(source, result);
	}
	

	public int getFileNr(String folderPath){
		int fileNr = 0,max = 0;
		File[] files = new File(folderPath).listFiles();
		for(int x=0; x<files.length; x++){
			fileNr = Integer.parseInt(files[x].getName().substring(3, files[x].getName().lastIndexOf("xml")-1));
			if(max < fileNr){
				max = fileNr;
			}
		}
		return max;
	}
	
	public boolean delete(String filePath){
		File file = new File(filePath);
		return file.delete();
	}

	public Map<Integer, HashMap<String, String>> Read(){
		NodeList nList = doc.getElementsByTagName("user");
		Map<Integer, HashMap<String, String>> list= new HashMap<Integer, HashMap<String, String>>();
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			HashMap<String,String> credentials = new HashMap<String, String>();
			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				credentials.put("username", eElement.getElementsByTagName("username").item(0).getTextContent());
				credentials.put("password", eElement.getElementsByTagName("password").item(0).getTextContent());
				credentials.put("amount", eElement.getElementsByTagName("amount").item(0).getTextContent());

				list.put(temp, credentials);

			}
		} 
		return list;
	}

	public <T> T Read(Class<T> cls) throws Exception{
		T construct = null;
		Node nFirstChild = doc.getFirstChild();
		NodeList nList = nFirstChild.getChildNodes();
		Map<String, String> fileData= new HashMap<String, String>();
		Map<String, NamedNodeMap> fileAttributes= new HashMap<String, NamedNodeMap>();

		for (int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);

			if(nNode.hasChildNodes()){
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					NodeList nChildList = nNode.getChildNodes();
					
					for(int x=0; x<nChildList.getLength(); x++){
						Node nChildNode = nChildList.item(x);
						
						if (nChildNode.getNodeType() == Node.ELEMENT_NODE){
							Element elem = (Element) nChildNode;
							String elemContent = elem.getTextContent();
							
							if(elemContent!= null && elemContent!= ""){
								fileData.put(elem.getTagName(), elemContent);
								fileAttributes.put(elem.getTagName(), nChildNode.getAttributes());
							}
						}
					}
				}
			}
		} 
		
		construct = cls.getDeclaredConstructor(Map.class,Map.class).newInstance((Object)fileData,(Object)fileAttributes);

		return (T)construct;
	}

	public boolean Modify(String nodeElement, String OriginalText, String ReplacedText){
		NodeList nList = doc.getElementsByTagName("user");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
										
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				if(eElement.getElementsByTagName(nodeElement).item(0).getTextContent().equals(OriginalText)){
					eElement.getElementsByTagName(nodeElement).item(0).setTextContent(ReplacedText);
					try {
						saveXML("Users");
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
				}
				
			}
		} 
		return false;
	}

	public void addNode(Element element, String text){
		element.appendChild(doc.createTextNode(text));
	}
	
	public NodeList getElement(String elementName){
		return doc.getElementsByTagName(elementName);
	}
	public boolean deleteNode(String node){
		
		//TODO Make deleteNode for all 
		
		XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        XPathExpression expression;
        Node b13Node;
		try {
			expression = xpath.compile("//UserList/user[username/text()='"+node+"']");
			b13Node = (Node) expression.evaluate(doc, XPathConstants.NODE);
			b13Node.getParentNode().removeChild(b13Node);
			saveXML("Users");
			return true; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return false;
	}
}
