package model.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;

import model.SettingViewObject;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.host.Document;

import controller.SettingViewController;

public class WebConnection{
	private WebClient webClient;
	private HtmlPage startPage;
	private WebPageManipulation webPageManipulation;
	private SettingViewController svc;
	
	public WebConnection(){
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.setJavaScriptTimeout(1000);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setAppletEnabled(false);
		webClient.getOptions().setDoNotTrackEnabled(false);
		webClient.getOptions().setPopupBlockerEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	    webClient.getCookieManager().setCookiesEnabled(true);
	    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	    webClient.getOptions().setThrowExceptionOnScriptError(true);
	    
	}
	
	public void connect(String url) throws MalformedURLException, IOException{
		startPage = webClient.getPage(url);
	}
	
	public HtmlPage getStartPage(){
		
		return startPage;
	}
	
	public boolean login(){
		webPageManipulation = new WebPageManipulation(getStartPage());
		svc = new SettingViewController();
				
		HtmlForm form = webPageManipulation.getFormByNr(0);
		Iterator<DomElement> itr = webPageManipulation.getAllElements(form).iterator();
		
		SettingViewObject svo = new SettingViewObject();
		svo = svc.getSettings();
		String[] str = {svo.getAdminUsername(),svo.getAdminPassword()};
		
		int cnt=0;
		
		while(itr.hasNext()){
			DomElement elem = itr.next();
			if(elem.hasChildNodes()){
				DomElement childElem = elem.getFirstElementChild();
			
				if(childElem.getNodeName().equals("input")&&cnt==1){
					webPageManipulation.setElement(childElem,str[cnt]);
				}
				cnt++;
			}
		}
		
		DomElement domUsername = (DomElement) webPageManipulation.getByXPath("//input[@type='text']").get(0);
		DomElement domPassword = (DomElement) webPageManipulation.getByXPath("//input[@type='password']").get(0);
		
		webPageManipulation.setElement(domUsername, str[0]);
		webPageManipulation.setElement(domPassword, str[1]);
		
		DomElement btnSubmit;
		HtmlButton btnSubmit2;
		
		if(webPageManipulation.getSubmitButton("//input[@type='submit']").size() > 0 ){
			try{
				btnSubmit = (DomElement) webPageManipulation.getSubmitButton("//input[@type='submit']").get(0);
				((HtmlElement) btnSubmit).click();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			//HtmlSubmitInput e = (HtmlSubmitInput) startPage.createElement("input");
			//e.setAttribute("type", "submit");
			
			HtmlElement button = (HtmlElement) startPage.createElement("button");
			button.setAttribute("type", "submit");
			
			btnSubmit2 = (HtmlButton) form.appendChild(button);
			
			try {
				btnSubmit2.click();
				return true;
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				return false;

			}
		}
		
	}
	
}
