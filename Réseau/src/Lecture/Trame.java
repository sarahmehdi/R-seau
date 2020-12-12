 package Lecture;

import java.util.List;


public class Trame {
	
	private List<Octet> octets;
	
	private String addrMacSource;
	private String addrMacDest;
	private String EthType;
	
	private int taille;
	private String bytes;
	private int bytesInt;
	private String addrIPsource;
	private String addrIPdest;
	private String identifier;
	private String TTL;
	private String protocol;
	private String checksum;
	private int checksumInt;
	private int options=0;
		
	private String IpTransportSource;
	private String IpTransportDest;
	
	public Trame(List<Octet> o) throws InvalidTrameException {
		if(o == null) throw new IllegalArgumentException();
		octets=o;
		if(octets.size()>=13)
			initEnteteEthernet();
		if(octets.size()>=34)
			initPaquetIp();
		
			initTransport();
			initDonnees();
	}
	
	private void initEnteteEthernet() {
		
		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<6; i++)
			tmp.append(octets.get(i));
		addrMacDest = tmp.toString();
	
		StringBuilder tmp2 = new StringBuilder();
		for(int i=6; i<12; i++)
			tmp2.append(octets.get(i));
		addrMacSource = tmp2.toString();
		
		EthType = "0x"+ octets.get(12) + octets.get(13);
		
	}
	
	private void initPaquetIp() throws InvalidTrameException {
		//initiatiser les addresses ip et tout ce qu'il y'a quand le paquet ip
		taille= 4*Character.getNumericValue(octets.get(14).getSecondHexa());
		if(taille < 20) throw new InvalidTrameException();
		if(taille > 20) 
			options = taille - 20;
		
		bytes = "0x"+octets.get(16)+octets.get(17);
		bytesInt = octets.get(16).toDecimale()*16 + octets.get(17).toDecimale();

		identifier = "0x"+octets.get(18)+octets.get(19);
		
		
		TTL = octets.get(22).toString();
		
		protocol = "0x"+ octets.get(23);
		
		checksum = "0x" + octets.get(24).toString() + octets.get(25).toString();
		checksumInt = octets.get(24).toDecimale()*16 + octets.get(25).toDecimale();
		
		
		StringBuilder tmp = new StringBuilder();
		for(int i=26; i<30; i++)
			tmp.append(octets.get(i));
		addrIPsource = tmp.toString();
		
		StringBuilder tmp2 = new StringBuilder();
		for(int i=30; i<34; i++)
			tmp2.append(octets.get(i));
		addrIPdest = tmp2.toString();
		
	}
	
	private void initTransport() {
		// init ICMP ou TCP ou UDP
		int index = 34+options;
		
		StringBuilder tmp = new StringBuilder();
		for(int i=index; i<index+4; i++) 
			tmp.append(octets.get(i));
		IpTransportSource = tmp.toString();
		
		StringBuilder tmp2 = new StringBuilder();
		for(int i=index+4; i<index+8; i++) 
			tmp.append(octets.get(i));
		IpTransportSource = tmp.toString();
		
	}
	
	private void initDonnees() {
		// init les données si il y'en a 
	}
	
	
	
	public void afficher() {
		
		System.out.println("Addresse MAC source : "+addrMacSource);
		System.out.println("Adresse MAC Destination : "+addrMacDest);
 
		System.out.println("Type : "+EthType+" ("+Type.getEthType(EthType)+")");
		
		System.out.println("Head length : "+taille);
		System.out.println("Total length : "+bytes+" ("+bytesInt+")");
		System.out.println("Identifier : "+identifier);
		System.out.println("Adresse IP Source : "+addrIPsource);
		System.out.println("Adresse IP Destination : "+addrIPdest);
		
		System.out.println("Time To Leave : "+TTL);
		System.out.println("Checksum IP : "+checksum+" ("+checksumInt+")");
		System.out.println(Type.getProtocol(protocol, octets, taille+14).toString() );
		
		
		System.out.println('\n');
	
	}
}
