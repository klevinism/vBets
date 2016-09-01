package JUnit_Tests;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import model.XMLFileManipulation;
import model.updater.UpdaterObject;

public class TestXMLFileManipulation {
	@Test
	public void test(){
		//Test for xml file manipulation
		XMLFileManipulation xmlFM = new XMLFileManipulation(new File("").getAbsolutePath()+"/updater.xml");
		UpdaterObject updateObject = new UpdaterObject();
		try {
			updateObject = (UpdaterObject)xmlFM.Read(new UpdaterObject().getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(updateObject.getCurrentVersion()+"I did this shit");
	}
}
