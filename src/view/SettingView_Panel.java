package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.Border;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import controller.SettingViewController;

import model.SettingViewObject;

public class SettingView_Panel extends JPanel {
	private JTextField websiteUrlField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private TextField tf_adminUrl;
	private JTextArea adCodeTextArea;
	private SettingViewObject settingViewObject;
	private SettingViewController svc;

	public void doInBackground(){
		/*
		 * TODO 
		 * 1- get all data from settings xml file
		 * 2- put them in setting object
		 * 3- show them in settings
		 */
		
		svc = new SettingViewController();
		settingViewObject = svc.getSettings();
	}
	
	/**
	 * Create the panel.
	 */
	public SettingView_Panel() {
		this.doInBackground();

		setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		scrollPane.setSize(900, 10);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("General", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblWordpressAdminUrl = new JLabel("Website Url :");
		GridBagConstraints gbc_lblWordpressAdminUrl = new GridBagConstraints();
		gbc_lblWordpressAdminUrl.gridwidth = 2;
		gbc_lblWordpressAdminUrl.anchor = GridBagConstraints.EAST;
		gbc_lblWordpressAdminUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblWordpressAdminUrl.gridx = 0;
		gbc_lblWordpressAdminUrl.gridy = 1;
		panel_1.add(lblWordpressAdminUrl, gbc_lblWordpressAdminUrl);
		
		websiteUrlField = new JTextField(settingViewObject.getAdminUrl());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 25);
		gbc_textField.gridwidth = 6;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel_1.add(websiteUrlField, gbc_textField);
		websiteUrlField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 2;
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		panel_1.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JTextField(settingViewObject.getAdminUsername());
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.insets = new Insets(0, 0, 5, 25);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 2;
		panel_1.add(usernameField, gbc_textField_1);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 2;
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		panel_1.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField(settingViewObject.getAdminPassword());
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 6;
		gbc_passwordField.insets = new Insets(0, 0, 5, 25);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 3;
		panel_1.add(passwordField, gbc_passwordField);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 9;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		panel_1.add(separator, gbc_separator);
		
		JLabel lblAdCode = new JLabel("Ad Code :");
		GridBagConstraints gbc_lblAdCode = new GridBagConstraints();
		gbc_lblAdCode.gridwidth = 2;
		gbc_lblAdCode.anchor = GridBagConstraints.EAST;
		gbc_lblAdCode.insets = new Insets(15, 0, 5, 5);
		gbc_lblAdCode.gridx = 0;
		gbc_lblAdCode.gridy = 6;
		panel_1.add(lblAdCode, gbc_lblAdCode);
		
		adCodeTextArea = new JTextArea(settingViewObject.getAdCode());
		adCodeTextArea.setLineWrap(true);
		adCodeTextArea.setAutoscrolls(true);
		adCodeTextArea.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		JScrollPane txtAreaScrollPane = new JScrollPane(adCodeTextArea);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 3;
		gbc_textArea.gridwidth = 4;
		gbc_textArea.insets = new Insets(15, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 6;
		panel_1.add(txtAreaScrollPane, gbc_textArea);
		
		JPanel panel_2 = new JPanel();
		
		//JavaFx used
		//using a two-parameter constructor
		final JFXPanel panelJFXP = new JFXPanel();
		panelJFXP.setBackground(Color.WHITE);
		
		Platform.runLater(new Runnable(){
            
            public void run() {
            	Pane stack = new Pane();
            	GridPane gridBig = new GridPane();
        		Scene scene = new Scene(stack);
            	
            	TitledPane tp = new TitledPane();
        		tp.setText("Links");
        		GridPane grid = new GridPane();
        		grid.setVgap(4);     
        		tp.setContent(grid);
        		
        		Label lb_adminUrl = new Label("Admin Url: ");
        		lb_adminUrl.setTooltip(new Tooltip("Admin page url ex: 'wp-admin'"));
        		grid.add(lb_adminUrl, 0, 0);

        		grid.setHgap(20);
        		
        		tf_adminUrl = new TextField();
        		tf_adminUrl.setPromptText("Admin page url");
        		tf_adminUrl.setTooltip(new Tooltip("Admin page url ex: 'wp-admin'"));
        		tf_adminUrl.setMinWidth(200);
        		grid.add(tf_adminUrl, 1, 0);

        		Label lb_postUrl = new Label("Post Url: ");
        		lb_postUrl.setTooltip(new Tooltip("New Post page url ex: 'wp-admin/post-new.php'"));
        		grid.add(lb_postUrl, 0, 1);

        		grid.setHgap(20);
        		
        		TextField tf_postUrl = new TextField();
        		tf_postUrl.setPromptText("New post page url");
        		tf_postUrl.setTooltip(new Tooltip("New Post page url ex: 'wp-admin/post-new.php'"));
        		tf_postUrl.setMinWidth(200);
        		grid.add(tf_postUrl, 1, 1);
            	gridBig.add(tp, 0, 0);
            	gridBig.setVgap(6);
            	
            	
        		TitledPane tp2 = new TitledPane();
        		tp2.setText("Forms");
        		tp2.setMaxHeight(900);
        		
        		GridPane grid2 = new GridPane();
        		grid2.setVgap(7);   
        		
        		HBox labeledSeparator = new HBox();
        		Label label = new Label("Login Form");
        		Separator rightSeparator = new Separator();
        		rightSeparator.setPrefWidth(100);
        		Separator leftSeparator = new Separator();
        		leftSeparator.setPrefWidth(100);
        		labeledSeparator.getChildren().add(leftSeparator);
        		labeledSeparator.getChildren().add(label);
        		labeledSeparator.getChildren().add(rightSeparator);
        		labeledSeparator.setAlignment(Pos.CENTER);
        		
        		grid2.add(labeledSeparator, 0,0,4, 1);
        		tp2.setContent(grid2);
        		
        		ToggleGroup btnGroup = new ToggleGroup();
        		
        		Label lb_usernameFieldId = new Label("Username Field: ");
        		lb_usernameFieldId.setTooltip(new Tooltip("Get username field attribute by: (default 'id')"));
        		grid2.add(lb_usernameFieldId, 0, 1);

        		grid2.setHgap(20);
        		
        		RadioButton rb_usernameFieldId = new RadioButton("Id");
        		rb_usernameFieldId.setToggleGroup(btnGroup);
        		rb_usernameFieldId.setSelected(true);
        		grid2.add(rb_usernameFieldId, 1, 1);
        		
        		RadioButton rb_usernameFieldName = new RadioButton("Name");
        		rb_usernameFieldName.setToggleGroup(btnGroup);
        		grid2.add(rb_usernameFieldName, 2, 1);

        		RadioButton rb_usernameFieldAttribute = new RadioButton("Attribute");
        		rb_usernameFieldAttribute.setToggleGroup(btnGroup);
        		
        		grid2.add(rb_usernameFieldAttribute, 3, 1);
        		
        		TextField tf_usernameFieldAttribute = new TextField();
        		grid2.add(tf_usernameFieldAttribute, 0, 2, 2, 1);

        		final TextField tf_usernameFieldValue = new TextField();
        		tf_usernameFieldValue.setVisible(false);
        		grid2.add(tf_usernameFieldValue, 2, 2, 2, 1);        		
        		
        		
        		ToggleGroup btnGroup2 = new ToggleGroup();
        		
        		Label lb_passwordFieldId = new Label("Password Field: ");
        		lb_passwordFieldId.setTooltip(new Tooltip("Get password field attribute by: (default 'id')"));
        		grid2.add(lb_passwordFieldId, 0, 3);

        		grid2.setHgap(20);
        		
        		RadioButton rb_passwordFieldId = new RadioButton("Id");
        		rb_passwordFieldId.setSelected(true);
        		rb_passwordFieldId.setToggleGroup(btnGroup2);
        		grid2.add(rb_passwordFieldId, 1, 3);
        		
        		RadioButton rb_passwordFieldName = new RadioButton("Name");
        		rb_passwordFieldName.setToggleGroup(btnGroup2);
        		grid2.add(rb_passwordFieldName, 2, 3);

        		RadioButton rb_passwordFieldAttribute = new RadioButton("Attribute");
        		rb_passwordFieldAttribute.setToggleGroup(btnGroup2);
        		grid2.add(rb_passwordFieldAttribute, 3, 3);
        		
        		TextField tf_passwordFieldAttribute = new TextField();
        		grid2.add(tf_passwordFieldAttribute, 0, 4, 2, 1);

        		final TextField tf_passwordFieldValue = new TextField();
        		tf_passwordFieldValue.setVisible(false);
        		grid2.add(tf_passwordFieldValue, 2, 4, 2, 1);        		

        		
        		ToggleGroup btnGroup3 = new ToggleGroup();
        		
        		Label lb_loginButtonId = new Label("Login Button: ");
        		lb_loginButtonId.setTooltip(new Tooltip("Get login button attribute by: (default 'id')"));
        		grid2.add(lb_loginButtonId, 0, 5);

        		grid2.setHgap(20);
        		
        		RadioButton rb_loginButtonId = new RadioButton("Id");
        		rb_loginButtonId.setSelected(true);
        		rb_loginButtonId.setToggleGroup(btnGroup3);
        		grid2.add(rb_loginButtonId, 1, 5);
        		
        		RadioButton rb_loginButtonName = new RadioButton("Name");
        		rb_loginButtonName.setToggleGroup(btnGroup3);
        		grid2.add(rb_loginButtonName, 2, 5);

        		RadioButton rb_loginButtonAttribute = new RadioButton("Attribute");
        		rb_loginButtonAttribute.setToggleGroup(btnGroup3);
        		grid2.add(rb_loginButtonAttribute, 3, 5);
        		
        		TextField tf_loginButtonAttribute = new TextField();
        		grid2.add(tf_loginButtonAttribute, 0, 6, 2, 1);

        		final TextField tf_loginButtonValue = new TextField();
        		tf_loginButtonValue.setVisible(false);
        		grid2.add(tf_loginButtonValue, 2, 6, 2, 1);   
        		
/*
 * --------------------------------Actions-----------------------------------
 */
        		rb_usernameFieldAttribute.selectedProperty().addListener(new ChangeListener<Boolean>() {
        		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
        		        if (isNowSelected) { 
        		            tf_usernameFieldValue.setVisible(true);
        		        } else {
        		            tf_usernameFieldValue.setVisible(false);
        		        }
        		    }
        		});
        		rb_passwordFieldAttribute.selectedProperty().addListener(new ChangeListener<Boolean>() {
        		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
        		        if (isNowSelected) { 
        		            tf_passwordFieldValue.setVisible(true);
        		        } else {
        		        	tf_passwordFieldValue.setVisible(false);
        		        }
        		    }
        		});
        		rb_loginButtonAttribute.selectedProperty().addListener(new ChangeListener<Boolean>() {
        		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
        		        if (isNowSelected) { 
        		            tf_loginButtonValue.setVisible(true);
        		        } else {
        		        	tf_loginButtonValue.setVisible(false);
        		        }
        		    }
        		});
/*
* --------------------------------!Actions-----------------------------------
*/
        		tp2.setContent(grid2);
            	gridBig.add(tp2, 0, 1);

            	panelJFXP.setScene(scene);
        		stack.getChildren().add(gridBig);
            }
		});
		
		//panel_2.add();
		tabbedPane.addTab("Advanced", null, panelJFXP, null);

		JLabel lblTitleField = new JLabel("Title Field:");
		lblTitleField.setToolTipText("Get title field by attribute");
		GridBagConstraints gbc_lblTitleField = new GridBagConstraints();
		gbc_lblTitleField.anchor = GridBagConstraints.EAST;
		gbc_lblTitleField.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitleField.gridx = 1;
		gbc_lblTitleField.gridy = 1;
		panel_2.add(lblTitleField, gbc_lblTitleField);
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		GridBagConstraints gbc_rdbtnName = new GridBagConstraints();
		gbc_rdbtnName.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnName.gridx = 2;
		gbc_rdbtnName.gridy = 1;
		panel_2.add(rdbtnName, gbc_rdbtnName);
		
		JRadioButton rdbtnId = new JRadioButton("Id");
		rdbtnId.setSelected(true);
		GridBagConstraints gbc_rdbtnId = new GridBagConstraints();
		gbc_rdbtnId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId.gridx = 3;
		gbc_rdbtnId.gridy = 1;
		panel_2.add(rdbtnId, gbc_rdbtnId);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnId);
		group.add(rdbtnName);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panel_2.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblContentFieldId = new JLabel("Content Field:");
		lblContentFieldId.setToolTipText("Get content field by attribute");
		GridBagConstraints gbc_lblContentFieldId = new GridBagConstraints();
		gbc_lblContentFieldId.anchor = GridBagConstraints.EAST;
		gbc_lblContentFieldId.insets = new Insets(0, 0, 5, 5);
		gbc_lblContentFieldId.gridx = 1;
		gbc_lblContentFieldId.gridy = 3;
		panel_2.add(lblContentFieldId, gbc_lblContentFieldId);
		
		JRadioButton rdbtnName_1 = new JRadioButton("Name");
		GridBagConstraints gbc_rdbtnName_1 = new GridBagConstraints();
		gbc_rdbtnName_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnName_1.gridx = 2;
		gbc_rdbtnName_1.gridy = 3;
		panel_2.add(rdbtnName_1, gbc_rdbtnName_1);
		
		JRadioButton rdbtnId_1 = new JRadioButton("Id");
		rdbtnId_1.setSelected(true);
		GridBagConstraints gbc_rdbtnId_1 = new GridBagConstraints();
		gbc_rdbtnId_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId_1.gridx = 3;
		gbc_rdbtnId_1.gridy = 3;
		panel_2.add(rdbtnId_1, gbc_rdbtnId_1);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnName_1);
		group2.add(rdbtnId_1);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 4;
		panel_2.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPublishButtonId = new JLabel("Publish Button:");
		lblPublishButtonId.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPublishButtonId.setToolTipText("Get publish button by attribute");
		GridBagConstraints gbc_lblPublishButtonId = new GridBagConstraints();
		gbc_lblPublishButtonId.anchor = GridBagConstraints.EAST;
		gbc_lblPublishButtonId.insets = new Insets(0, 0, 5, 5);
		gbc_lblPublishButtonId.gridx = 1;
		gbc_lblPublishButtonId.gridy = 5;
		panel_2.add(lblPublishButtonId, gbc_lblPublishButtonId);
		
		JRadioButton rdbtnName_2 = new JRadioButton("Name");
		GridBagConstraints gbc_rdbtnName_2 = new GridBagConstraints();
		gbc_rdbtnName_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnName_2.gridx = 2;
		gbc_rdbtnName_2.gridy = 5;
		panel_2.add(rdbtnName_2, gbc_rdbtnName_2);
		
		JRadioButton rdbtnId_2 = new JRadioButton("Id");
		rdbtnId_2.setSelected(true);
		GridBagConstraints gbc_rdbtnId_2 = new GridBagConstraints();
		gbc_rdbtnId_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId_2.gridx = 3;
		gbc_rdbtnId_2.gridy = 5;
		panel_2.add(rdbtnId_2, gbc_rdbtnId_2);
		
		ButtonGroup group3 = new ButtonGroup();
		group3.add(rdbtnName_2);
		group3.add(rdbtnId_2);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 2;
		gbc_textField_4.insets = new Insets(0, 0, 0, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 6;
		panel_2.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
	}

	public SettingViewObject getSettings(){
		SettingViewObject settingObject = new SettingViewObject();
		
		String websiteUrl = websiteUrlField.getText();
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		String adcode = adCodeTextArea.getText();
		
		settingObject.setAdminUrl(websiteUrl);
		settingObject.setAdminUsername(username);
		settingObject.setAdminPassword(password);
		settingObject.setAdCode(adcode);
		
		return settingObject;
	}
}
