package model;

import javax.swing.JPanel;

public class MainViewInputObject {
	private String VideoUrl;
	private String Title;
	private String Description;
	
	public MainViewInputObject(){
	}
	
	public void setVideoUrl(String url){
		VideoUrl = url;
	}
	
	public String getVideoUrl(){
		return VideoUrl;
	}
	
	public void setTitle(String title){
		Title = title;
	}
	
	public String getTitle(){
		return Title;
	}
	
	public void setDescription(String description){
		Description = description;
	}
	
	public String getDescription(){
		return Description;
	}
}
