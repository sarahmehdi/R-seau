package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		PrintWriter out = new PrintWriter(new FileOutputStream("data/result"));
		
		int offsetControl = 0;
		int lastlength = 16;
		int count = 0;
		while((line = br.readLine())!=null) {
			
			String[] s = line.split(" ");
			if(Type.hexaToInt(s[0])==0) {
				if(octets.size()>0) {
					tr = new Trame(octets);
					tr.afficher(out);
				}
				octets = new ArrayList<>();
				offsetControl = 0;
			} else {
				if(Type.hexaToInt(s[0])!=offsetControl)
					throw new InvalidTrameException("l'offset est invalid à la ligne "+count);
				if(lastlength < 17)
					throw new InvalidTrameException("le nombre d'octet est invalide à la ligne "+(count-1));
			}
			int i;
			for(i=1; i<s.length; i++) {
				if(s[i].length() == 2) {
					octets.add(new Octet(s[i]));
					offsetControl++;
				}
			}
			lastlength=i;
			count++;
		}
		tr = new Trame(octets);
		tr.afficher(out);
		
		out.close();
		br.close();
	} catch(IOException e) {
		e.printStackTrace();
	} catch(InvalidTrameException e) {
		e.printStackTrace();
	}
	
	}
}
