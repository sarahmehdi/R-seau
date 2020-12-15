package Lecture;

import java.util.List;

public class HTTPResponse implements IHTTP,ITransportProtocol{

	private String version;
	private int codeStatus;
	private String message;
	private String entete;
	
	
	public HTTPResponse(List<Octet> octets,int index) {
		for(Octet o: octets) {
			if( o.equals("20")) break;
			version+=(char) o.toDecimale();
			index++;
			
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).equals("20")) break;
			codeStatus= octets.get(i).toDecimale();
			index=i;
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).equals("0d") && octets.get(i+1).equals("0a")) break;
			message+=(char) octets.get(i).toDecimale();
			index=i;
		}
		
		entete=version+" "+codeStatus+" "+message+"\n";
		for(int i=index+1;i<octets.size();i++) {
			if(octets.get(i).equals("0d") && octets.get(i+1).equals("0a")
			 && octets.get(i+2).equals("0d") && octets.get(i+3).equals("0a") ) break;
			if (octets.get(i).equals("20")) entete+="\n";
			entete+=(char) octets.get(i).toDecimale();
		}
		
		
		
	}
	public String toString() {
		return entete;
	}
	@Override
	public String getName() {
		return "HTTPResponse";
	}

	@Override
	public String getType() {
		return message;
	}

}
