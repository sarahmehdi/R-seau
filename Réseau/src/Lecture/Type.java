package Lecture;


public class Type {

		
		public static String getEthType(String value) {
			if(value.equals("0x0800")) return "IPv4";
			if(value.equals("0x0806")) return "ARP";
			if(value.equals("0x86dd")) return "IPv6";
			
			return "Unknown";
		}
		
		public static String getProtocol(String value) {
			if(value.equals("0x01")) return "ICMP";
			if(value.equals("0x06")) return "TCP";
			if(value.equals("0x17")) return "UDP";
			
			return "Unknown";
		}
}
