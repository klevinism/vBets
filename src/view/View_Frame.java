package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import controller.SettingViewController;

import model.SettingViewObject;
import model.globals.Globals;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public View_Frame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setSize(310, 241);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingView_Panel settViewPanel = new SettingView_Panel();
				JScrollPane jsp = new JScrollPane(settViewPanel);
			    jsp.setPreferredSize(new Dimension(400, 200));
			    jsp.setBorder(null);
			    
				int result = JOptionPane.showConfirmDialog(View_Frame.this, jsp, "My custom dialog", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					if(settViewPanel.getClass().getSimpleName().equals(SettingView_Panel.class.getSimpleName())){
						SettingViewObject settingObject = new SettingViewObject();
						settingObject = ((SettingView_Panel) settViewPanel).getSettings();
						
						
						SettingViewController svc = new SettingViewController(settingObject);
						svc.setActionPerformed("Save");
					}
				} else {
				    System.out.println("User canceled / closed the dialog, result = " + result);
				}
			}
		});
		
		mntmSettings.setIcon(new ImageIcon(View_Frame.class.getResource(Globals.paths.LocalImageFolder+"icon-gear.png")));
		
		mnSettings.add(mntmSettings);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
	}

}
