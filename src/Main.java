import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import model.globals.Globals;
import model.updater.Updater;
import view.LauncherView_Panel;
import view.View_Frame;
import controller.VersionController;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VersionController vController = new VersionController();
		
		if(vController.isUpToDate()){
			
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						View_Frame frame = new View_Frame("Value Bets");
						frame.setLocationRelativeTo(null);
						frame.setMinimumSize(new Dimension(370,200));

						LauncherView_Panel launcherView = new LauncherView_Panel(frame);

						//System.out.println(Globals.paths.LocalImageFolder);
						Image image = Toolkit.getDefaultToolkit().getImage(launcherView.getClass().getResource(Globals.paths.LocalImageFolder + "app-icon.png"));
						ImageIcon icon = new ImageIcon(image);
						frame.setIconImage(icon.getImage());
						
						frame.setContentPane(launcherView);
						frame.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}else{
			Updater updater = new Updater();
			updater.initComponents();
			updater.setVisible(true);
			updater.download();
		}
		
	}

}
