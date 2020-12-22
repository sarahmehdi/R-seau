 package Lecture;

import java.io.PrintWriter;
import java.util.List;


public class Trame {
	
	private static int count = 0;
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
	
	private ITransportProtocol transport;
		
	
	public Trame(List<Octet> o) throws InvalidTrameException {
		if(o == null) throw new IllegalArgumentException();
		if(o.size() < 66) throw new InvalidTrameException("La trame est trop courte, veuillez saisir une trame valide");
		octets = o;
		initEnteteEthernet();
		initPaquetIp();		
		initTransport();
		count++;
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
		if(taille < 20) throw new InvalidTrameException("le IHL de la trame est invalde (IHL ="+taille+")");
		
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
	
	
	private void initTransport() {
		 transport = Type.getProtocol(protocol, octets, taille+14);
	}
	
	
	
	public void afficher(PrintWriter out) {
		
		out.write("\n # TRAME "+count+'\n');
		out.write("Ethernet :\n");
		out.write("      Adresse MAC source : "+addrMacSource+'\n');
		out.write("      Adresse MAC Destination : "+addrMacDest+'\n');
 
		out.write("      Type : "+EthType+" ("+Type.getEthType(EthType)+")\n");
		
		if(Type.getEthType(EthType).equals("Unknown")) {
			out.write("Le type de protocol n'est pas reconnu, on ne peut donc pas analyser votre trame :/");
			return ;
		} 
		
		out.write(Type.getEthType(EthType)+" :\n");
		out.write("      Version : "+version+'\n');
		out.write("      IHL : "+taille+" bytes\n");
		out.write("      Total length : "+bytes+" ("+bytesInt+")\n");
		out.write("      Identifier : "+identifier+'\n');
		out.write("            DF : "+DF+'\n');
		out.write("            MF : "+MF+'\n');
		out.write("      Fragement Offset : "+fragmentOffset+'\n');
		out.write("      Adresse IP Source : "+addrIPsource+'\n');
		out.write("      Adresse IP Destination : "+addrIPdest+'\n');
		
		out.write("      Time To Leave : "+TTL+'\n');
		out.write("      Checksum IP : "+checksum+" ("+checksumInt+")\n");
		if(transport == null) {
			out.write("Le protocol de transport n'est pas reconnu, on ne peut donc pas poursuivre l'analyse de votre trame :/ \n");
			return ;
		}
		out.write(transport.toString()+'\n');
		IHTTP http = Type.getHttp(transport, octets, taille+14);
		if(http==null) {
			out.write("Le type d'application n'est pas reconnu, on ne peut donc pas poursuivre l'analyse de votre trame :/ \n");
			return ;
		}
		out.write(http.toString()+'\n');
	}
}
