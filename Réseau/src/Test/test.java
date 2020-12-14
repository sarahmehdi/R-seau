package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Lecture.*;

public class test {
	public static void main(String[]args) {
	try {
		BufferedReader br = new BufferedReader(new FileReader("data/trame.txt"));
		
		String line;
		List<Octet> octets = new ArrayList<>();
		Trame tr;
		
		while((line = br.readLine())!=null) {
			
			String[] s = line.split(" ");
			if(s.length>1) {
				if(s[0].equals("00")) {
					if(octets.size()>0) {
						tr = new Trame(octets);
						tr.afficher();
					}
					octets = new ArrayList<>();
				}
				
				for(int i=1; i<s.length; i++) {
					if(s[i].length() == 2)
						octets.add(new Octet(s[i]));
				}
			}
		}
		tr = new Trame(octets);
		tr.afficher();
	} catch(IOException e) {
		e.printStackTrace();
	} catch(InvalidTrameException e) {
		e.printStackTrace();
	}
	
	}
}
