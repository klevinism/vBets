package view;

import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.async.TextAreaOutputStream;
import model.globals.Globals;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BettingView_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BettingView_Panel(JFrame currentFrame) {
		setLayout(new MigLayout("", "[581.00px,grow]", "[357px,grow][16px]"));
		setSize(750,550);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 1 2,grow");
		
		JToolBar toolBar = new JToolBar();
		scrollPane.setColumnHeaderView(toolBar);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// now test the mechanism
				System.out.println( "Hello World" );
			}
		});
		btnNewButton.setIcon(new ImageIcon(BettingView_Panel.class.getResource(Globals.paths.LocalImageFolder + "search.png")));
		toolBar.add(btnNewButton);
		
		toolBar.addSeparator(new Dimension(5,10));
		
		JButton btnNewButton_1 = new JButton("Place Bet");
		btnNewButton_1.setIcon(new ImageIcon(BettingView_Panel.class.getResource(Globals.paths.LocalImageFolder + "add.png")));
		toolBar.add(btnNewButton_1);
		
		toolBar.addSeparator(new Dimension(40,20));
		
		JButton btnSearchPlace = new JButton("Search & Place");
		btnSearchPlace.setIcon(new ImageIcon(BettingView_Panel.class.getResource(Globals.paths.LocalImageFolder + "add_auto.png")));
		btnSearchPlace.setHorizontalAlignment(SwingConstants.RIGHT);
		toolBar.add(btnSearchPlace);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setAutoscrolls(true);
		scrollPane_1.setViewportView(textArea);
		
		// Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
		// PrintStream around it to support the println/printf methods.
		PrintStream out = new PrintStream( new TextAreaOutputStream( textArea ) );

		// redirect standard output stream to the TextAreaOutputStream
		System.setOut( out );

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr( out );

	}

}
