 package Lecture;

import java.util.List;

import Type;

public class Trame {
	
	private String addrMacSource;
	private String addrMacDest;
	private String EthType;
	
	private String addrIPsource;
	private String addrIPdest;
	private String options = "no options";
	
	public Trame(List<Octet> octets) {
		if(octets == null) throw new IllegalArgumentException();
		if(octets.size()>=13)
			initEnteteEthernet(octets);
		if(octets.size()>=34)
			initPaquetIp(octets);
		
			initTransport(octets);
			initDonnees(octets);
	}
	
	private void initEnteteEthernet(List<Octet> octets) {
		
		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<6; i++)
			tmp.append(octets.get(i));
		addrMacDest = tmp.toString();
	
		StringBuilder tmp2 = new StringBuilder();
		for(int i=7; i<12; i++)
			tmp2.append(octets.get(i));
		addrMacSource = tmp2.toString();
		
		EthType = "0x"+ octets.get(12) + octets.get(13);
		
	}
	
	private void initPaquetIp(List<Octet> octets) {
		//initiatiser les addresses ip et tout ce qu'il y'a quand le paquet ip meme ICMP
	}
	
	private void initTransport(List<Octet> octets) {
		// init TCP ou UDP
	}
	
	private void initDonnees(List<Octet> octets) {
		// init les données si il y'en a 
	}
	
	public void afficher() {
		
		System.out.println("Addresse MAC source : "+addrMacSource+'\n');
		System.out.println("Adresse MAC Destination : "+addrMacDest+'\n');
		
		System.out.println("Type : "+ EthType + " ("+ Type.getEthType(EthType)+")");
	}
}
