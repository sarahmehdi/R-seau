package Lecture;

import java.util.List;

public class ICMP implements ITransportProtocol {

	private final String type = "0x01 ";
	private String code;
	private String checksum;
	private int checksumInt;
	private String identifier;
	private String seqNum;
	
	public ICMP(List<Octet> octets, int index) {
		code = octets.get(index+1).toString();
		
		checksum = "0x"+octets.get(index+2) +octets.get(index+3);
		checksumInt = octets.get(index+2).toDecimale()*16 +octets.get(index+3).toDecimale();
		
		identifier = "0x"+octets.get(index+4) + octets.get(index+5);
		
		seqNum = "Ox"+octets.get(index+6)+octets.get(index+7);
	}
	
	@Override
	public String getName() {
		return "ICMP";
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Protocol : ICMP (0x01) \n Code : ");
		s.append(code);
		s.append("\n Checksum : ");
		s.append(checksum);
		s.append(" ("+checksumInt+")");
		s.append("\n Identifier : ");
		s.append(identifier);
		s.append("\n Sequence Number : ");
		s.append(seqNum);
		return s.toString();
	}

}
