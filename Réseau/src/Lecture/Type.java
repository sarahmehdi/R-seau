package Lecture;

import java.util.List;

public class Type {

		
		public static String getEthType(String value) {
			if(value.equals("0x08 00 ")) return "IPv4";
			if(value.equals("0x08 06 ")) return "ARP";
			if(value.equals("0x86 dd ")) return "IPv6";
			
			return "Unknown";
		}
		
		public static ITransportProtocol getProtocol(String value, List<Octet> octets, int i) {
			if(value.equals("0x01 ")) return new ICMP(octets, i);
			if(value.equals("0x06 ")) return new TCP(octets, i);
			//if(value.equals("0x17 ")) return new UDP(octets, i);
			
			return null;
		}
		
}
