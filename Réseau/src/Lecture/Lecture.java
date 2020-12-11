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
		 int cpt =0;
		 
		 while ((c=in.read()) != -1){
			if (cpt ==5) {
				break ;
			}
			Ethernet+=(char) c;
			cpt++;
		 }
		 if (Ethernet == "0x0800")  Ethernet+="  (ipv4)";
		 if (Ethernet == "0x0806")  Ethernet+="  (ARP)";
		 if (Ethernet == "0x86dd")  Ethernet+="  (ipv6)";
		 return Ethernet;
		 
		 
	 }
	 public String getIp() throws IOException {
		 try {
		      in= new BufferedReader(new FileReader(file));
		 }
		
		 catch(FileNotFoundException exc)   {
			 System.out.println("Erreur d'ouverture");
		   }
		 int c;
		 int cpt=0;
		 while ((c=in.read()) != -1){
			 if (cpt >=43 && cpt <= 48)
				 IP+=(char) c ;
		 }
		 int nbOctet = (int)IP.charAt(3) *4;
		 return IP+"  ("+nbOctet+" octets)";
	 }
	 // j'ai une autre idéé que je pense elle va marcher mieux on va stocker tous les octets dans un tableeau 
	 // par ordre on pourra accéder plus facilement au pire on fait une matrice tu vois ..
 }
	 
	


