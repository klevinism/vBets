package model.updater;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;

public class UpdaterObject {
	
	private String Version;
	private String Description;
	private String Url;
	private Map<String,String> XmlData = new HashMap<String,String>();
	private Map<String, NamedNodeMap> XmlAttributes = new HashMap<String,NamedNodeMap>();

	
	public UpdaterObject(){
		
	}
	
	public UpdaterObject(Map<String,String> xmlData, Map<String,NamedNodeMap> xmlAttributes){
		XmlData = xmlData;
		XmlAttributes = xmlAttributes;
	}
	
	public void setData(Map<String,String> xmlData){
		XmlData = xmlData;
	}
	
	public Map<String, String> getData(){
		return XmlData;
	}
	
	public void setAttributes(Map<String, NamedNodeMap> xmlAttributes){
		XmlAttributes = xmlAttributes;
	}
	
	public Map<String, NamedNodeMap> getAttributes(){
		return XmlAttributes;
	}
	
	public String getCurrentVersion(){
		return XmlData.get("CurrentVersion");
	}
	
	public String getCurrentVersion(String elementName){
		return XmlData.get(elementName);
	}
	
}
