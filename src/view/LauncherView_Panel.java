package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import controller.VersionController;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class LauncherView_Panel extends JPanel {
	public JLabel lblVersion;
	
	public void doInBackground(){
		VersionController vController = new VersionController();
		
		lblVersion.setText(lblVersion.getText()+vController.getCurrentVersionAsString());
	}
	/**
	 * Create the panel.
	 */
	public LauncherView_Panel(final JFrame currentFrame) {
		setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("fill", "[grow]", "[pref!,grow][][][]"));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("fill", "[5px]", "[pref!]"));
		
		JLabel label = new JLabel("Value Bets");
		label.setFont(new Font("Book Antiqua", Font.PLAIN, 25));
		panel_1.add(label, "cell 0 0,alignx center,aligny top");
		
		JButton btnNewButton = new JButton("Bet Now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BettingView_Panel bettingPanel = new BettingView_Panel(currentFrame);
				currentFrame.dispose();
				currentFrame.setContentPane(bettingPanel);
				currentFrame.setSize(bettingPanel.getSize());
				currentFrame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btnNewButton, "cell 0 1,alignx center,aligny center");
		
		lblVersion = new JLabel("Version: ");
		panel.add(lblVersion, "cell 0 3,alignx right,aligny bottom");
		
		this.doInBackground();
	}
	
}
