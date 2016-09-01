package model;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;

public class SettingViewObject {
	public String[] ParameterCategory = {"General","Advanced"};
	public String[] Parameters = {"AdminUrl","AdminUsername","AdminPassword","AdCode"};
	private String AdminUrl;
	private String AdminUsername;
	private String AdminPassword;
	private String AdCode;
	private Map<String, String> XmlData;
	private Map<String, NamedNodeMap> XmlAttributes;
	
	public SettingViewObject(){
	}
	
	public SettingViewObject(String adminUrl, String adminUsername, String adminPassword, String adCode){
		AdminUrl = adminUrl;
		AdminUsername = adminUsername;
		AdminPassword = adminPassword;
		AdCode = adCode;
	}
	
	public SettingViewObject(Map<String,String> xmlData, Map<String,NamedNodeMap> xmlAttributes){
		XmlData = xmlData;
		XmlAttributes = xmlAttributes;
	}
	
	public void setAdminUrl(String adminUrl){
		AdminUrl = adminUrl;
	}
	
	public String getAdminUrl(){
		if(AdminUrl == "" || AdminUrl == null)
			return XmlData.get("AdminUrl");
		else
			return AdminUrl;
	}

	public void setAdminUsername(String adminUsername){
		AdminUsername = adminUsername;
	}
	
	public String getAdminUsername(){
		if(AdminUsername == "" || AdminUsername == null)
			return XmlData.get("AdminUsername");
		else
			return AdminUsername;
	}
	
	public void setAdminPassword(String adminPassword){
		AdminPassword = adminPassword;
	}
	
	public String getAdminPassword(){
		if(AdminPassword == "" || AdminPassword == null)
			return XmlData.get("AdminPassword");
		else
			return AdminPassword;
	}
	
	public void setAdCode(String adCode){
		AdCode = adCode;
	}
	
	public String getAdCode(){
		if(AdCode == "" || AdCode == null)
			return XmlData.get("AdCode");
		else
			return AdCode;
	}
	
}
