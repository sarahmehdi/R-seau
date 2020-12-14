 package Lecture;

import java.util.List;


public class Trame {
	
	private List<Octet> octets;
	
	private String addrMacSource;
	private String addrMacDest;
	private String EthType;
	private int version;
	
	private int taille;
	private String bytes;
	private int bytesInt;
	private String addrIPsource;
	private String addrIPdest;
	private String identifier;
	private String DF = "0 (no)";
	private String MF = "0 (no)";
	private int fragmentOffset;
	private String TTL;
	private String protocol;
	private String checksum;
	private int checksumInt;
	private int options=0;
		
	
	public Trame(List<Octet> o) throws InvalidTrameException {
		if(o == null) throw new IllegalArgumentException();
		octets=o;
		initEnteteEthernet();
		initPaquetIp();		
		initDonnees();
	}
	
	private void initEnteteEthernet() {
		
		StringBuilder tmp = new StringBuilder();
		tmp.append(octets.get(0));
		for(int i=1; i<6; i++)
			tmp.append(": "+octets.get(i));
		addrMacDest = tmp.toString();
	
		StringBuilder tmp2 = new StringBuilder();
		tmp2.append(octets.get(6));
		for(int i=7; i<12; i++)
			tmp2.append(": "+octets.get(i));
		addrMacSource = tmp2.toString();
		
		EthType = "0x"+ octets.get(12) + octets.get(13);
		if(Type.getEthType(EthType).equals("IPv4")) version = 4;
		if(Type.getEthType(EthType).equals("IPv6")) version = 6;
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
		
		int indice = octets.get(20).toBinaire()/10000;
		if(indice % 2 ==1)
			MF = "1 (yes)";
		indice = indice/10;
		if(indice % 2==1)
			DF = "1 (yes)";
		
		fragmentOffset = ((octets.get(20).toBinaire()/10000)%2)*2048 + Character.getNumericValue(octets.get(20).getSecondHexa())*65536 + octets.get(21).toDecimale();
		fragmentOffset = fragmentOffset*8;
		
		TTL = octets.get(22).toString();
		
		protocol = "0x"+ octets.get(23);
		
		checksum = "0x" + octets.get(24).toString() + octets.get(25).toString(); 
		checksumInt = octets.get(24).toDecimale()*16 + octets.get(25).toDecimale();
		
		
		StringBuilder tmp = new StringBuilder();
		tmp.append(octets.get(26).toDecimale());
		for(int i=27; i<30; i++)
			tmp.append("."+octets.get(i).toDecimale());
		addrIPsource = tmp.toString();
		
		StringBuilder tmp2 = new StringBuilder();
		tmp2.append(octets.get(30).toDecimale());
		for(int i=31; i<34; i++)
			tmp2.append("."+octets.get(i).toDecimale());
		addrIPdest = tmp2.toString();
		
	}
	
	
	private void initDonnees() {
		// init les données si il y'en a 
	}
	
	
	
	public void afficher() {
		
		System.out.println("Ethernet :");
		System.out.println("      Adresse MAC source : "+addrMacSource);
		System.out.println("      Adresse MAC Destination : "+addrMacDest);
 
		System.out.println("      Type : "+EthType+" ("+Type.getEthType(EthType)+")");
		
		if(Type.getEthType(EthType).equals("Unknown")) {
			System.out.println("Le type de protocol n'est pas reconnu, on ne peut donc pas analyser votre trame :/");
			return ;
		}
		
		System.out.println(Type.getEthType(EthType)+" :");
		System.out.println("      Version : "+version);
		System.out.println("      IHL : "+taille+" bytes");
		System.out.println("      Total length : "+bytes+" ("+bytesInt+")");
		System.out.println("      Identifier : "+identifier);
		System.out.println("            DF : "+DF);
		System.out.println("            MF : "+MF);
		System.out.println("      Fragement Offset : "+fragmentOffset);
		System.out.println("      Adresse IP Source : "+addrIPsource);
		System.out.println("      Adresse IP Destination : "+addrIPdest);
		
		System.out.println("      Time To Leave : "+TTL);
		System.out.println("      Checksum IP : "+checksum+" ("+checksumInt+")");
		System.out.println("      "+Type.getProtocol(protocol, octets, taille+14).toString() );
		
		
		System.out.println('\n');
	
	}
}
