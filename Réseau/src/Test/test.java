package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import Trame;
import Lecture.Octet;

public class test {
	public static void main(String[]args) {
		BufferedReader br = new BufferedReader(new FileReader(/**fichier**/));
		String line;
		Trame tr;
		List<Octet> octets;
		while((line = br.readLine())!=null) {
			String[] s = line.split(" ");
			if(s.length>0) {
				if(s[0]=="00") {
					if(tr!=null) { 
						tr.afficher();
						tr = new Trame(octets);
					}
					octets = new ArrayList<>();
				}
				for(int i=1; i<s.length; i++) {
					octets.add(new Octet(s[i]));
				}
			}
		}
	}
}
