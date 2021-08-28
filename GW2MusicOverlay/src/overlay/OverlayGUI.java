package overlay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataProcessing.Parser;
import dataProcessing.Interpreter;

public class OverlayGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	JFrame frame = new JFrame("GW2MusicOverlay");JPanel inputBox = new JPanel();
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JButton stop = new JButton("Stop");
	JButton loadFile = new JButton("Add Macro");
	
	JButton loadScript = new JButton("Load Script");
	
	
	JPanel leftPanel = new JPanel();
	

	Parser parser = new Parser();
	ArrayList<String[]> parsedSong = new ArrayList<String[]>();
	Interpreter player;
	
	
	public OverlayGUI() {
	};
	
	private static OverlayGUI gui = new OverlayGUI();
	
	
	public static void main(String[] args) {
		//MainStuff
		gui.initializeGUI();
	}
	
	
	
	private void initializeGUI() {
		@SuppressWarnings("unused")
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		frame.setLayout(new BorderLayout());frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop( true );
		frame.setFocusableWindowState(false);
		frame.setFocusable(false);

		frame.add(play, BorderLayout.SOUTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(loadFile, BorderLayout.NORTH);
		

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(pause);
		leftPanel.add(resume);
		leftPanel.add(stop);
		
		leftPanel.add(loadScript);
		

		loadFile.addActionListener(loadFilePressed);
		play.addActionListener(playPressed);
		pause.addActionListener(pausePressed);
		resume.addActionListener(resumePressed);
		stop.addActionListener(stopPressed);

//		loadScript.addActionListener(loadScriptPressed);
		
		

		
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension (400,500));
		frame.pack();
//		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	Action loadFilePressed = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
			
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                ".ahk .txt", "ahk", "txt");
	        chooser.setFileFilter(filter);
	        chooser. setAcceptAllFileFilterUsed(false);
	        int returnVal = chooser.showOpenDialog(frame);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile().getAbsolutePath());
	        }
	        try {
				parsedSong = parser.parseData(chooser.getSelectedFile().getAbsolutePath(), chooser.getSelectedFile().getName());
				if(parsedSong.isEmpty()) {
					System.out.println("Error. Unable to load Song.");
				}
				else {
					System.out.println("Success. Song loaded.");
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
		  }
	};
	
	
	Action playPressed = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
			player = new Interpreter(parsedSong, "player");
			player.start();
		  }
	};
	
	
	Action pausePressed = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
	        player.pauseSong();
	        
		  }
	};
	
	
	
	Action resumePressed = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
	        player.resumeSong();
	        
		  }
	};
	
	
	Action stopPressed = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
	        player.stopSong();
	        
		  }
	};
	
	Action loadScriptPressed = new AbstractAction() {

		/**
		 */ 
		private static final long serialVersionUID = -5267461181756573253L;

		@Override
		  public void actionPerformed(ActionEvent e){
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                ".ahk .txt", "ahk", "txt");
	        chooser.setFileFilter(filter);
	        chooser. setAcceptAllFileFilterUsed(false);
	        int returnVal = chooser.showOpenDialog(frame);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile().getAbsolutePath());
	        }
	        try {
				parsedSong = parser.loadInternScript(chooser.getSelectedFile().getAbsolutePath());
				if(parsedSong.isEmpty()) {
					System.out.println("Error. Unable to load Song.");
				}
				else {
					System.out.println("Success. Song loaded.");
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
		  }
	};
	
	
}
