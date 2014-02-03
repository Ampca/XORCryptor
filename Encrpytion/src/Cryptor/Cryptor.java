package Cryptor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public final class Cryptor {
	
	public Cryptor() {
		//
	}
	
	private static String[] algorithm (String entry) {
		String result[] = new String[2];
		result[0] = "";
		result[1] = "";
		for(char c: entry.toCharArray()) {
			
			if(Character.isWhitespace(c)) {
				result[0] += c;
				result[1] += c;
				continue;
			}
			
			char resultChar;
			char key;
			
			do {
			key = generatePassword();
			resultChar = (char)((int)c ^ (int)key);
			} while (Character.isWhitespace(resultChar));
			
			result[0] += resultChar;
			result[1] += key;
		}
		return result;
	}
	
	private static String decryptRithm(String entry, String key) {
		String result = "";
		for(int i=0; i < entry.length() && i < key.length(); i++) {
			if(Character.isWhitespace(entry.charAt(i))) {
				result += entry.charAt(i);
				continue;
			}
			result += (char)(entry.charAt(i) ^ key.charAt(i));
		}
		return result;
	}
	
	public static void encrypt (String file) throws IOException { 
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter("Password.txt", "UTF-8");
		PrintWriter dataWriter = new PrintWriter(generateFileName(file), "UTF-8");
		
		String in = null;
		while((in = reader.readLine()) != null) {
			String[] data = algorithm(in);
			dataWriter.println(data[0]);
			writer.println(data[1]);
		}
		
		
		writer.close();
		dataWriter.close();
		reader.close(); 
	}
	public static void decrypt (String file, String passwordFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedReader passReader = new BufferedReader(new FileReader(passwordFile));
		PrintWriter writer = new PrintWriter(generateFileName(file), "UTF-8");
		
		String in = null;
		String password = null;
		while((in = reader.readLine()) != null && (password = passReader.readLine()) != null) {
			writer.println(decryptRithm(in, password));
		}
		
		
		reader.close();
		passReader.close();
		writer.close();
	}
	private static String generateFileName (String file) {
		String output = "";
		int fileNum;
		
		try{
			fileNum = Integer.parseInt(file.substring(file.length()-5, file.length()-4))+1;
			output = file.substring(0,file.length()-4-(fileNum+"").length())+fileNum+".txt";
		} catch (Exception e) {
			output = file.substring(0, file.length()-4)+1+".txt";
		}
		return output;
	}
	private static char generatePassword() {
		return (char)((int)(Math.random() * 93 + 33));
	}
	
	
}
