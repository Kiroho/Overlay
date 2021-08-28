package dataProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistence.PersistenceManager;

public class Parser {

	
	

	PersistenceManager persManager = new PersistenceManager();
	
	
	
	public ArrayList<String[]> parseData(String path, String name) throws ClassNotFoundException, IOException {
		
		ArrayList<String> data = persManager.loadFile(path);
		ArrayList<String[]> parsedData = new ArrayList<String[]>();
		
		data.forEach(content -> parsedData.add(parseProcessing(content)));
		persManager.saveSong("src/resources/test.txt", parsedData);									//
		return parsedData;
	}
	
	
	private String[] parseProcessing(String line) {
		String parseCode[] = {"none", "none", "none"};
		if(checkForKeyword("sendinput", line)!="") {
			parseCode[0] = "input";
			
			if(checkForNumber(line)!="") {
				parseCode[1] = checkForNumber(line);
			}
			
			if(checkForKeyword("up", line)=="up") {
				parseCode[2] = "up";
			}
			else if(checkForKeyword("down", line)=="down") {
				parseCode[2] = "down";
			}
			
		}
		else if(checkForKeyword("sleep", line)!="") {
			parseCode[0] = "sleep";
			
			if(checkForNumber(line)!="") {
				parseCode[1] = checkForNumber(line);
			}
		}
		else {
			parseCode[0]="none";
		}
		System.out.println(parseCode[0] + " - " + parseCode[1] + " - " + parseCode[2]);
		return parseCode;
	}
	
	
	
	private String checkForKeyword (String keyword, String line) {
		String result="";
		
		if(line.toLowerCase(Locale.US).contains(keyword)) {
			result = keyword;
		}
		return result;
	}
	
	private String checkForNumber (String line) {
		Pattern pattern = Pattern.compile("[0-9]+"); 
		Matcher matcher = pattern.matcher(line);
		String match="";
		// Find all matches
		while (matcher.find()) { 
		  // Get the matching string  
		   match = matcher.group();
		}
		return match;
	}
	
	
	//Noch ungetestet
	public ArrayList<String[]> loadInternScript(String path) throws ClassNotFoundException, IOException{
		ArrayList<String> data = persManager.loadFile(path);
		ArrayList<String[]> output = new ArrayList<String[]>();
		for(int i=0; i<data.size(); i++) {
			String[] split = data.get(i).split("\\s+");
			output.add(split);
		}
		return output;
	}

	
	
}
