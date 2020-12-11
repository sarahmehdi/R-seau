 package Lecture;

import java.util.List;

import Type;

public class Trame {
	
	private String addrMacSource;
	private String addrMacDest;
	private String EthType;
	
	private String addrIPsource;
	private String addrIPdest;
	private int options = 0;
	private String TTL;
	private String protocol;
	private String checksum;
	
	public Trame(List<Octet> octets) throws InvalidTrameException {
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
	
	private void initPaquetIp(List<Octet> octets) throws InvalidTrameException {
		//initiatiser les addresses ip et tout ce qu'il y'a quand le paquet ip
		int taille= Character.getNumericValue(octets.get(14).getSecondHexa());
		if(taille < 5) throw new InvalidTrameException();
		if(taille > 5) 
			options = 5*taille - 20;
		
		
		TTL = octets.get(23).toString();
		
		protocol = "0x"+ octets.get(24);
		
		checksum = octets.get(25).toString() + octets.get(26).toString();
		
		StringBuilder tmp = new StringBuilder();
		for(int i=27; i<31; i++)
			tmp.append(octets.get(i));
		addrIPsource = tmp.toString();
		
		StringBuilder tmp2 = new StringBuilder();
		for(int i=31; i<35; i++)
			tmp2.append(octets.get(i));
		addrIPdest = tmp2.toString();
		
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
