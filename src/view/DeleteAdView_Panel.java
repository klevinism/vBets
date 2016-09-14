package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.PostObject;
import model.async.AsyncTasks;
import model.globals.Globals;
import model.table.TableModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class DeleteAdView_Panel extends JPanel {
	private JTextField textField;
	private JTable table;
	private JSeparator separator;
	private PostObject posts = new PostObject();
	private LinkedList<PostObject> linkedPosts = new LinkedList<PostObject>();
	private String[] columnNames = {"Id","Title","Author","Category","Date","Url","DeleteUrl","WebUrl"};
	private JButton DeleteAd, DeletePost;
	
	private final TableRowSorter<TableModel> sorter;
	private JPopupMenu popupMenu;
	private TableModel tableModel;
	private JMenuItem goToUrl;
	
	public void doInBackground(){
		/*
		 * TODO
		 * 1- Get all posts 
		 */
		if(posts.getAllPosts().size() != 0)
			linkedPosts = posts.getAllPosts();
	}

	/**
	 * Create the panel.
	 */
	public DeleteAdView_Panel(final JFrame currentFrame) {
		doInBackground();

		currentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new MigLayout("", "[pref!,grow][][grow][]", "[50.00][grow,baseline][10.00][]"));
		
		JLabel lblNewLabel = new JLabel("Search Post:");
		add(lblNewLabel, "cell 0 0,alignx right");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				searchPost(textField.getText());
			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPost(textField.getText());
			}
		});
		
		add(textField, "cell 1 0 3 1,growx");
		textField.setColumns(10);
		
		//====================================TABLE=============================================
		tableModel = new TableModel(linkedPosts);
		tableModel.setColumnName(columnNames);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setAutoscrolls(true);
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		sorter = new TableRowSorter<TableModel>(tableModel);
	    table.setRowSorter(sorter);
		table.setComponentPopupMenu(popupMenu);
		
		add(new JScrollPane(table), "cell 0 1 6 1,grow");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		    	ListSelectionModel lsm = (ListSelectionModel)event.getSource();
		    	toggleDeleteButtons(!lsm.isSelectionEmpty());
		    }
		});
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        int r = table.rowAtPoint(e.getPoint());
		        if (r >= 0 && r < table.getRowCount()) {
		            table.setRowSelectionInterval(r, r);
		        } else {
		            table.clearSelection();
		        }

		        int rowindex = table.getSelectedRow();
		        int row = table.convertRowIndexToModel(rowindex);
		        
		        URL postUrl = null;
		        
				try {
					postUrl = new URL(table.getModel().getValueAt(row,7).toString());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        if (row < 0)return;
		        
		        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		            JPopupMenu popup = createPopUp(postUrl);
		            popup.show(e.getComponent(), e.getX(), e.getY());
		        }
		    }
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		//====================================TABLE===========================================================

		separator = new JSeparator();
		add(separator, "cell 0 2 6 1,grow");
		
		//====================================Buttons=========================================================
		
		JButton button = new JButton("<-- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.dispose();
				currentFrame.setContentPane(new LauncherView_Panel(currentFrame));
				currentFrame.setVisible(true);
			}
		});
		add(button, "cell 0 3");
		
		
		DeletePost = new JButton("Remove Post");
		try{
			Image img = ImageIO.read(getClass().getResource(Globals.paths.LocalImageFolder + "delete.png"));
			DeletePost.setIcon(new ImageIcon(img));

		}catch(IOException ex){
		}
		DeletePost.setEnabled(false);
		DeletePost.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				currentFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				// TODO Auto-generated method stub
				AsyncTasks asyncTasks = new AsyncTasks();
				asyncTasks.deletePost(getSelectedPost());
				Thread async = new Thread(asyncTasks);
				async.run();
				
				fireRefreshTableData();
				currentFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}}
		);
		
		

		DeleteAd = new JButton("Remove Ad");
		try{
			Image img = ImageIO.read(getClass().getResource(Globals.paths.LocalImageFolder+"block_ads.png"));
			DeleteAd.setIcon(new ImageIcon(img));
		}catch(IOException ex){
		}
		DeleteAd.setEnabled(false);
		DeleteAd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AsyncTasks asyncTasks = new AsyncTasks();
				asyncTasks.deleteAd(getSelectedPost());
				Thread async = new Thread(asyncTasks);
				async.run();
			}}
		);
		
		//====================================Buttons==========================================================
		
		add(DeleteAd, "cell 2 3,alignx right");
		add(DeletePost, "cell 3 3");
	}
	
	private PostObject getSelectedPost(){
		PostObject selectedPost = null;
		table.convertRowIndexToModel(0);

		int[] row = table.getSelectedRows();
		for(int x=0; x<row.length; x++){
			selectedPost = new PostObject();
			row[x] = table.convertRowIndexToModel(row[x]);//convert index to model so value does not change if table sorted
			
			selectedPost.setId((int)table.getModel().getValueAt(row[x],0));
			selectedPost.setTitle(table.getModel().getValueAt(row[x],1).toString());
			selectedPost.setAuthor(table.getModel().getValueAt(row[x],2).toString());
			selectedPost.setCategory(table.getModel().getValueAt(row[x],3).toString());
			selectedPost.setDate(table.getModel().getValueAt(row[x],4).toString());
			selectedPost.setUrl(table.getModel().getValueAt(row[x],5).toString());
			selectedPost.setDeleteUrl(table.getModel().getValueAt(row[x],6).toString());
			selectedPost.setWebUrl(table.getModel().getValueAt(row[x],7).toString());
		}
		
		return selectedPost;
	}
	
	private void fireRefreshTableData(){
		linkedPosts = new LinkedList<PostObject>();
		linkedPosts = posts.getAllPosts();
		TableModel tableModel = new TableModel(linkedPosts);
		tableModel.setColumnName(columnNames);
		table.setModel(tableModel);
		
		tableModel.fireTableDataChanged();
	}

	private void toggleDeleteButtons(boolean toggleable){
			DeleteAd.setEnabled(toggleable);
			DeletePost.setEnabled(toggleable);
	}
	
	private void searchPost(String word){
		System.out.println("searchPost");
	  if (word.trim().length() == 0) {
	     sorter.setRowFilter(null);
	  } else {
		  System.out.print(word);
		  sorter.setRowFilter(RowFilter.regexFilter("(?i)" + word));
	  }
	}

	public static void openUrlOnBrowser(URI uri){
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private JPopupMenu createPopUp(final URL postUrl){
		popupMenu = new JPopupMenu();
		goToUrl = new JMenuItem("Open in browser");
		Image img;
		try {
			img = ImageIO.read(getClass().getResource(Globals.paths.LocalImageFolder + "browser_icon.png"));
			goToUrl.setIcon(new ImageIcon(img));
		} catch (IOException e2) {
			// TODO Handle this
			e2.printStackTrace();
		}
		popupMenu.add(goToUrl);
		goToUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					openUrlOnBrowser(postUrl.toURI());
				} catch (URISyntaxException e1) {
					// TODO Handle this
					e1.printStackTrace();
				}
            }
        });
		return popupMenu;
	}
	
}
