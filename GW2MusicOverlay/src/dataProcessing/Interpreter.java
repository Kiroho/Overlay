package dataProcessing;

import java.util.ArrayList;

import winkeyboard.Keyboard;
import winkeyboard.ScanCode;

public class Interpreter extends Thread{

	
	
	private ArrayList<String[]> song;
	Keyboard keyboard = new Keyboard();
	private boolean isPaused = false;
	private boolean stopPlay = false;
	

	public Interpreter(ArrayList<String[]> song, String name) {
		super(name);
		this.song = song;
	}
	
	
	
	@Override
	public void run() {
		playSong(song);
	}
	
	
	
	
	public void playSong(ArrayList<String[]> song) {
		    	System.out.println("Play");
		    		for(int i=0; i<song.size();i++) {
		    			if(stopPlay) {
		    				break;
		    			}
						while(isPaused) {
																	//Nach Möglichkeit ein (einmaliges) "releaseAllkeys" einfügen
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						playLine(song.get(i));
		    		}
		    		stopPlay=true;
				
		
	}
	

	public void pauseSong() {
		//Pause Thread
		isPaused = true;
		System.out.println("Pause Status: " + isPaused);
	}

	public void resumeSong() {
		//Resume Thread
		isPaused = false;
		System.out.println("Pause Status: " + isPaused);
	}
	
	public void stopSong() {
		//Stop Thread
		stopPlay = true;
	}
	
	
	private void playLine(String[] line) {
		
		switch(line[0]) {
		case "input":
			//handle input
			handleInput(line);
			break;
		
		case "sleep":
			//handle sleep
			handleSleep(line);
			break;
		
		}
		
	}
	
	private void handleInput(String[] line) {
		if(line[1]=="none") {
			return;
		}
		switch(line[2]) {
		case "up":
			//handle input-up
			keyboard.winKeyRelease(translateKeyStrokes(line[1]));
			break;
		case "down":
			//handle input-down
			keyboard.winKeyPress(translateKeyStrokes(line[1]));
			break;
		case "none":
			//handle input-none
			keyboard.winKeyPress(translateKeyStrokes(line[1]));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			keyboard.winKeyRelease(translateKeyStrokes(line[1]));try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void handleSleep(String[] line) {
		try {
			Thread.sleep(Long.parseLong(line[1]));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int translateKeyStrokes(String input) {

		
		switch(input) {
		case "1":
			return ScanCode.DIK_1;
		case "2":
			return ScanCode.DIK_2;
		case "3":
			return ScanCode.DIK_3;
		case "4":
			return ScanCode.DIK_4;
		case "5":
			return ScanCode.DIK_5;
		case "6":
			return ScanCode.DIK_6;
		case "7":
			return ScanCode.DIK_7;
		case "8":
			return ScanCode.DIK_8;
		case "9":
			return ScanCode.DIK_9;
		case "0":
			return ScanCode.DIK_0;
		}
		
		//Errorcode einfügen und beim Ausgeben handeln
		return 0000;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
