package view;

import controller.*;
import model.MainViewInputObject;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttachAdView_Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField youtube_field;
	public JTextField wp_title_field;
	public JTextArea textArea;
	private MainViewController mainVC;
	private MainViewInputObject mainInputObject;
	/**
	 * Create the panel.
	 */
	
	public AttachAdView_Panel(final JFrame currentFrame) {
		setSize(450,350);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 100, 0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblYoutubeUrl = new JLabel("Youtube Url :");
		GridBagConstraints gbc_lblYoutubeUrl = new GridBagConstraints();
		gbc_lblYoutubeUrl.gridwidth = 2;
		gbc_lblYoutubeUrl.insets = new Insets(5, 0, 5, 5);
		gbc_lblYoutubeUrl.anchor = GridBagConstraints.EAST;
		gbc_lblYoutubeUrl.gridx = 0;
		gbc_lblYoutubeUrl.gridy = 1;
		add(lblYoutubeUrl, gbc_lblYoutubeUrl);
		
		youtube_field = new JTextField();
		GridBagConstraints gbc_youtube_field = new GridBagConstraints();
		gbc_youtube_field.insets = new Insets(10, 0, 5, 30);
		gbc_youtube_field.gridwidth = 2;
		gbc_youtube_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_youtube_field.gridx = 3;
		gbc_youtube_field.gridy = 1;
		add(youtube_field, gbc_youtube_field);
		youtube_field.setColumns(10);
		
		JLabel lblWpTitle = new JLabel("WP Title :");
		lblWpTitle.setToolTipText("Wordpress Title");
		GridBagConstraints gbc_lblWpTitle = new GridBagConstraints();
		gbc_lblWpTitle.anchor = GridBagConstraints.EAST;
		gbc_lblWpTitle.gridwidth = 2;
		gbc_lblWpTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblWpTitle.gridx = 0;
		gbc_lblWpTitle.gridy = 2;
		add(lblWpTitle, gbc_lblWpTitle);
		
		wp_title_field = new JTextField();
		GridBagConstraints gbc_wp_title_field = new GridBagConstraints();
		gbc_wp_title_field.insets = new Insets(0, 0, 5, 30);
		gbc_wp_title_field.gridwidth = 2;
		gbc_wp_title_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_wp_title_field.gridx = 3;
		gbc_wp_title_field.gridy = 2;
		add(wp_title_field, gbc_wp_title_field);
		wp_title_field.setColumns(10);
		
		JLabel lblWpDescription = new JLabel("WP Description :");
		lblWpDescription.setToolTipText("Wordpress Description");
		GridBagConstraints gbc_lblWpDescription = new GridBagConstraints();
		gbc_lblWpDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblWpDescription.gridwidth = 2;
		gbc_lblWpDescription.anchor = GridBagConstraints.EAST;
		gbc_lblWpDescription.gridx = 0;
		gbc_lblWpDescription.gridy = 3;
		add(lblWpDescription, gbc_lblWpDescription);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setAutoscrolls(true);
		textArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		JScrollPane txtAreaScrollPane = new JScrollPane(textArea);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(5, 0, 5, 30);
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 3;
		add(txtAreaScrollPane, gbc_textArea);
		
		final JButton btnAttachAd = new JButton("Attach Ad");
		btnAttachAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				currentFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				mainVC = new MainViewController(getInput());
				mainVC.setActionPerformed(btnAttachAd.getText());
				currentFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}
		});
		
		JButton button = new JButton("<-- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.dispose();
				currentFrame.setContentPane(new LauncherView_Panel(currentFrame));
				currentFrame.setVisible(true);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		GridBagConstraints gbc_btnAttachAd = new GridBagConstraints();
		gbc_btnAttachAd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAttachAd.insets = new Insets(0, 0, 5, 30);
		gbc_btnAttachAd.gridx = 4;
		gbc_btnAttachAd.gridy = 4;
		add(btnAttachAd, gbc_btnAttachAd);
		

	}

	public MainViewInputObject getInput(){
		mainInputObject = new MainViewInputObject();
		mainInputObject.setVideoUrl(this.youtube_field.getText());
		mainInputObject.setTitle(this.wp_title_field.getText());
		mainInputObject.setDescription(this.textArea.getText());
		return mainInputObject;
	}
}
