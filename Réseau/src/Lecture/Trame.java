package Lecture;

import java.util.List;

import Type;

public class Trame {
	
	private String addrMacSource;
	private String addrMacDest;
	private String EthType;
	
	private String addrIPsource;
	private String addrIPdest;
	
	public Trame(List<Octet> octets) {
		if(octets == null) throw new IllegalArgumentException();
		
		initEnteteEthernet();
		initPaquetIp();
		initTransport();
		initDonnees();
	}
	
	private void initEnteteEthernet() {
		//initialiser les addresses mac et le protocol ethernet 0800
	}
	
	private void initPaquetIp() {
		//initiatiser les addresses ip et tout ce qu'il y'a quand le paquet ip meme ICMP
	}
	
	private void initTransport() {
		// init TCP ou UDP
	}
	
	private void initDonnees() {
		// init les données si il y'en a 
	}
	
	public void afficher() {
		
		System.out.println("Addresse MAC source : "+addrMacSource+'\n');
		System.out.println("Adresse MAC Destination : "+addrMacDest+'\n');
		
		System.out.println("Type : "+ EthType + " ("+ Type.getEthType(EthType)+")");
	}
}
