package Cryptor;

import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws IOException {
		Cryptor.encrypt("Entry.txt");
		Cryptor.decrypt("Output.txt", "OutputPassword.txt");

	}

}
