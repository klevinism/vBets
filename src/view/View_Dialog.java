package view;

import controller.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import model.SettingViewObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static int result; 
	private JButton okButton;
	/**
	 * Create the dialog.
	 */
	public View_Dialog(String Title,final JPanel panel) {
		setTitle(Title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(panel.getClass().getSimpleName().equals(SettingView_Panel.class.getSimpleName())){
							SettingViewObject settingObject = new SettingViewObject();
							settingObject = ((SettingView_Panel) panel).getSettings();
							
							
							SettingViewController svc = new SettingViewController(settingObject);
							svc.setActionPerformed(okButton.getText());
							
							
							
						}
						
						/*
						 * TODO
						 * 1.Check if data same
						 * 2.Modify modified data
						 * 3.Save XML
						 */
						
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
}
