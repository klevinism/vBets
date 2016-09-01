package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import model.SettingViewObject;
import model.XMLFileManipulation;
import model.globals.Globals;

public class SettingViewController extends Controller{
	private SettingViewObject settingViewObject;
	private XMLFileManipulation fileManipulation;
	
	public SettingViewController(){
		
	}
	
	public SettingViewController(SettingViewObject settingviewobject){
		settingViewObject = settingviewobject;
	}

	@Override
	public void setActionPerformed(String actionPerformer) {
		// TODO Auto-generated method stub
		if(actionPerformer.equals(Globals.actions.SettingView_Save)){
			/*
			 * TODO
			 * 1- Open xml if exists
			 * 2- Check if settings same as in settingobject
			 * 3- If setting different enter new setting parameter
			 * 4- Save xml file
			 */
			
			fileManipulation = new XMLFileManipulation();
			HashMap<String, Integer> elements = new HashMap<String, Integer>();
			elements.put(settingViewObject.Parameters[0], 2);
			elements.put(settingViewObject.Parameters[1], 2);
			elements.put(settingViewObject.Parameters[2], 2);
			elements.put(settingViewObject.Parameters[3], 2);
			elements.put(settingViewObject.ParameterCategory[1], 1);
			elements.put(settingViewObject.ParameterCategory[0], 1);
			elements.put("Settings", 0);			
			
			fileManipulation.buildElements(elements);
			
			fileManipulation.addNode((Element) fileManipulation.getElement(settingViewObject.Parameters[0]).item(0), settingViewObject.getAdminUrl());
			fileManipulation.addNode((Element)fileManipulation.getElement(settingViewObject.Parameters[1]).item(0), settingViewObject.getAdminUsername());
			fileManipulation.addNode((Element)fileManipulation.getElement(settingViewObject.Parameters[2]).item(0), settingViewObject.getAdminPassword());
			
			String adCode =  settingViewObject.getAdCode().replaceAll("<", "/<");
			adCode = adCode.replaceAll(">", "/>");
			adCode = adCode.trim().replaceAll(" +", "");
			fileManipulation.addNode((Element)fileManipulation.getElement(settingViewObject.Parameters[3]).item(0), adCode);

			try {
				fileManipulation.saveXML(Globals.paths.SettingsXmlLocalFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public SettingViewObject getSettings(){
		String adCode = null;
		File tmp = new File(Globals.paths.SettingsXmlLocalFile);
		settingViewObject = new SettingViewObject();

		if(tmp.exists()){
			fileManipulation = new XMLFileManipulation(Globals.paths.SettingsXmlLocalFile);
			
			try {
				settingViewObject = (SettingViewObject)fileManipulation.Read(new SettingViewObject().getClass());
				
				if(adCode != null){
					adCode = settingViewObject.getAdCode().replaceAll("/<", "<");
					adCode = adCode.replaceAll("/>", ">");
					adCode = adCode.trim().replaceAll(" +", "");
				}
				
				settingViewObject.setAdCode(adCode);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			fileManipulation = new XMLFileManipulation();
			HashMap<String, Integer> elements = new HashMap<String, Integer>();
			elements.put(settingViewObject.Parameters[0], 2);
			elements.put(settingViewObject.Parameters[1], 2);
			elements.put(settingViewObject.Parameters[2], 2);
			elements.put(settingViewObject.Parameters[3], 2);
			elements.put(settingViewObject.ParameterCategory[1], 1);
			elements.put(settingViewObject.ParameterCategory[0], 1);
			elements.put("Settings", 0);
			
			try {
				fileManipulation.saveXML(Globals.paths.SettingsXmlLocalFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return settingViewObject;
	}
}
