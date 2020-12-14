package Lecture;

import java.util.List;

public class TCP implements ITransportProtocol {
	
	private String sourcePort;
	private String destPort;
	
	private String seqNum;
	private String AckNum;
	
	private String THL;
	private String window;
	private String flag;
	
	private String checksum;
	private int checksumInt;

	public TCP(List<Octet> octets, int index) {
		sourcePort = octets.get(index).toString() + octets.get(index+1).toString();
		destPort = octets.get(index+2).toString() + octets.get(index+3).toString();

		StringBuilder s = new StringBuilder();
		for(int i=index+4; i<index+8; i++)
			s.append(octets.get(i));
		seqNum = s.toString();
		
		StringBuilder s2 = new StringBuilder();
		for(int i=index+8; i<index+12; i++)
			s2.append(octets.get(i));
		AckNum = s2.toString();
		
		THL = "0x"+octets.get(index+12).getFirstHexa();
		
		window = "0x"+octets.get(index+14)+octets.get(index+15);
		
		flag = getFlag(octets.get(index+13).toBinaire());
		
		checksum = "0x"+ octets.get(index+16)+octets.get(index+17);
		checksumInt = octets.get(index+16).toDecimale()*16 + octets.get(index+17).toDecimale();
	}
	
	private String getFlag(int indice) {
		if(indice % 2 == 1)
			return "FIN";
		indice=indice/10;
		if(indice % 2 == 1)
			return "SYN";
		indice=indice/10;
		if(indice % 2 ==1)
			return "RST";
		indice=indice/10;
		if(indice % 2 == 1)
			return "PSH";
		indice=indice/10;
		if(indice % 2 == 1)
			return "ACK";
		indice=indice/10;
		if(indice % 2 == 1)
			return "URG";
		return "No flag";
	}
	@Override
	public String getName() {
		return "TCP";
	}

	@Override
	public String getType() {
		return "0x06 ";
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("      Protocol : OxO6 (TCP) \n");
		s.append("TCP : \n");
		s.append("      Source port number : "+sourcePort+'\n');
		s.append("      Destination port number : "+destPort+'\n');
		s.append("      Sequence Number : "+seqNum+'\n');
		s.append("      Acknowledge Number : "+AckNum+'\n');
		s.append("      THL : "+THL+'\n');
		s.append("      Flag : "+flag);
		s.append("      Window : "+window+'\n');
		s.append("      Checksum : "+checksum+" ("+checksumInt+") \n");
		
		return s.toString();
	}

}
