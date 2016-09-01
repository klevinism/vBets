package model.globals;

import java.io.File;

import controller.SettingViewController;

public class Paths extends Globals {

	public static SettingViewController svc = new SettingViewController();
	
	/*
	 * Local global paths
	 */
	
	public static String RootFolder = new File("").getAbsolutePath();
	
	public static String UpdaterXmlLocalFile = RootFolder + "/documents/updater.xml";
	public static String SettingsXmlLocalFile = RootFolder + "/documents/settings.xml";
	
	public static String LocalResourcesFolder = "/resources/";
	public static String LocalImageFolder = LocalResourcesFolder+"images/";
	
	
	/*
	 * Remote global paths
	 */
	
	public static String RemoteBaseUrl = "http://delimeta.info";
	public static String RemoteUpdaterUrl = RemoteBaseUrl + "/updater/updater_AdsAttacher";
	public static String RemoteUpdaterFileUrl = RemoteUpdaterUrl + "/updater.xml";
	
	public static String RemoteAdminUrl = svc.getSettings().getAdminUrl();

}
