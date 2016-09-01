package controller;

import model.XMLFileManipulation;
import model.globals.Globals;
import model.updater.Updater;
import model.updater.UpdaterObject;

@SuppressWarnings("static-access")
public class VersionController{
	private String CurrentVersion;
	private String LatestVersion;
	private XMLFileManipulation fileManipulation;
	private UpdaterObject updateObject;
	private Updater updater;
	
	public VersionController(){
		
	}
	
	public VersionController(String currentVersion, String latestVersion){
		CurrentVersion = currentVersion;
		LatestVersion = latestVersion;
	}
	
	public void setCurrentVersion(String currentVersion){
		CurrentVersion = currentVersion;
	}
	
	public void setCurrentVersion(int currentVersion){
		CurrentVersion = "" + currentVersion;
	}
	
	public int getCurrentVersion(){
		if(CurrentVersion == null || CurrentVersion == ""){
			try {
				return getLocalVersion(Globals.paths.UpdaterXmlLocalFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			return Integer.parseInt(CurrentVersion.replace(".", ""));
		}
		return 0;
	}
	
	public String getCurrentVersionAsString(){
		if(CurrentVersion == null)
			getCurrentVersion();
		return CurrentVersion;
	}
	
	private int getLocalVersion(String filePath) throws Exception{
		fileManipulation = new XMLFileManipulation(filePath);
		updateObject = (UpdaterObject)fileManipulation.Read(new UpdaterObject().getClass());
		this.setCurrentVersion(updateObject.getCurrentVersion());
		
		return this.getCurrentVersion();
	}
	
	public void setLatestVersion(String latestVersion){
		LatestVersion = latestVersion;
	}

	public void setLatestVersion(int latestVersion){
		LatestVersion = ""+latestVersion;
	}
	
	public int getLatestVersion(){
		if(LatestVersion == null || LatestVersion == ""){
			try {
				return this.getRemoteVersion(Globals.paths.RemoteUpdaterFileUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			return Integer.parseInt(LatestVersion.replace(".", ""));	
		}
		return 0;
	}
	
	public String getLatestVersionAsString(){
		if(LatestVersion == null)
			getLatestVersion();
		return LatestVersion;
	}
	
	private int getRemoteVersion(String remoteFilePath) throws Exception{
		updater = new Updater();
		this.setLatestVersion(updater.getLatestVersion(remoteFilePath));
		
		return this.getLatestVersion();
	}
	
	public boolean isUpToDate(){
		if(this.getCurrentVersion() < this.getLatestVersion()){
			return false;
		}else{
			return true;
		}
	}
}
