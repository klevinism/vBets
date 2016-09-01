package model.async;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

import model.PostObject;
import model.globals.Globals;
import model.web.WebConnection;
import model.web.WebPageManipulation;

public class AsyncTasks implements Runnable {

	private WebConnection conn = null;
	private WebPageManipulation pageManipulation = null;
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		Thread.currentThread().setName("AsyncTasks");
	}
	
	public boolean deleteAd(PostObject post){
		
		PostObject Post = post;
		String PostEditUrl = Post.getUrl();
		String postDescriptionNoAd = "";
		conn = new WebConnection();
		
		try {
			conn.connect(PostEditUrl);
			if(conn.login()){
				conn.connect(PostEditUrl);
				pageManipulation = new WebPageManipulation(conn.getStartPage());
				HtmlTextArea txtAreaDescription = (HtmlTextArea)pageManipulation.getElementById("content");
				HtmlSubmitInput publish = (HtmlSubmitInput)pageManipulation.getElementById("publish");
				
				List<DomElement> listDescription = (List<DomElement>)pageManipulation.getByXPath("//textarea[@class='wp-editor-area']");
				
				String wpDescription = listDescription.get(0).asText();
				
				if(wpDescription.toLowerCase().contains(Globals.AdCode.toLowerCase())){				
					postDescriptionNoAd = wpDescription.replace(Globals.AdCode,"");
					
					txtAreaDescription.setText(postDescriptionNoAd);
					HtmlPage landingPage = publish.click();
					
					
					JOptionPane.showMessageDialog(null, "Ad removed");
					return true;

				}else{
					JOptionPane.showMessageDialog(null, "Sorry no ad code found inside this post");
					return false;
					/*TODO
					 * 1- Notify that post doesn't have an ad
					 */
				}				
			}
			
			System.out.print(postDescriptionNoAd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletePost(PostObject post){
		conn = new WebConnection();
		
		String deleteUrl = post.getUrl();
		
		try {
			conn.connect(deleteUrl);
			if(conn.login()){
				conn.connect(deleteUrl);
				WebPageManipulation pageManipulation = new WebPageManipulation(conn.getStartPage());
				
				List<HtmlAnchor> deleteLink = (List<HtmlAnchor>) pageManipulation.getByXPath("//a[@class='submitdelete deletion']");
				
				HtmlPage redirect = deleteLink.get(0).click();
				
				if(redirect.toString().contains("trashed=1") && redirect.toString().contains("ids="+post.getId())){
					return true;
				}else{
					return false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;		
	}
	
}
