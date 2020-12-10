package Lecture;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lecture {
	
	 private BufferedReader in ;
	 private String file;
	 private String IP="0x";
	 private String HTTP="0x";
	 private String TCP="0x";
	 private String Ethernet="0x";
	 public Lecture (String file)  {
		 this.file=file;
		
	 }
		 
	 public String getIP() throws IOException {
		 try {
		      in= new BufferedReader(new FileReader(file));
		 }
		
		 catch(FileNotFoundException exc)   {
			 System.out.println("Erreur d'ouverture");
		   }
		 
		 return IP;
	 }
	 public String getEthernet() throws IOException {
		 try {
		      in= new BufferedReader(new FileReader(file));
		 }
		
		 catch(FileNotFoundException exc)   {
			 System.out.println("Erreur d'ouverture");
		   }
		 int c;
		 char octet1;
		 char octet2;
		 
		 
		 return Ethernet; 
		 
	 }
 }
	 
	


