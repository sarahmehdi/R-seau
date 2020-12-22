package Lecture;

import java.util.List;

public class UDP implements ITransportProtocol {

	private String sourcePort;
	private String destPort;
	
	private String length;
	private String checksum;
	private int checksumInt;
	
	public UDP(List<Octet> octets, int index) {
		sourcePort = octets.get(index).toString() + octets.get(index+1).toString();
		destPort = octets.get(index+2).toString() + octets.get(index+3).toString();
		
		length = octets.get(index+4).toString() + octets.get(index+4).toString();
		checksum = octets.get(index+6).toString() + octets.get(index+7).toString();
		
		checksumInt = octets.get(index+6).toDecimale()*16 + octets.get(index+7).toDecimale();
	}
	@Override
	public String getName() {
		return "UDP";
	}

	@Override
	public String getType() {
		return "0x17";
	}
	
	
	@Override 
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Protocol : UDP (0x17)\n");
		s.append("UDP :\n");
		s.append("      Numero de port source : "+sourcePort+"\n");
		s.append("      Numero de port destination : "+destPort+"\n");
		s.append("      Longueur : "+length+'\n');
		s.append("      Checkcum : "+checksum+" ("+checksumInt+")");
		return s.toString();
	}

}
