package Lecture;

import java.util.List;

public class HTTPResponse implements IHTTP,ITransportProtocol{

	private String version = "";
	private String codeStatus = "";
	private String message = "";
	private String entete = "";
	
	
	public HTTPResponse(List<Octet> octets,int index) {
		for(int i=index; i<octets.size(); i++) {
			if( octets.get(i).toString().equals("20 ")) break;
			version+=(char) octets.get(i).toDecimale();
			index++;
			
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).toString().equals("20 ")) break;
			codeStatus+= (char) octets.get(i).toDecimale();
			index=i;
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).toString().equals("0d ") && octets.get(i+1).toString().equals("0a ")) break;
			message+=(char) octets.get(i).toDecimale();
			index=i;
		}
		
		for(int i=index+1;i<octets.size();i++) {
			if(octets.get(i-1).toString().equals("0d ") && octets.get(i).toString().equals("0a ")){
				if(octets.get(i+1).toString().equals("0d ") && octets.get(i+2).toString().equals("0a ") ) break;
				entete+= "\n      ";
			} else 
				entete+= (char) octets.get(i).toDecimale();
		}
		
		
		
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("HTTP : HTTPResponse \n");
		s.append("\n      Version : "+version);
		s.append("      Code Status : "+codeStatus);
		s.append("\n      Message : "+message);
		s.append(entete);
		return s.toString();
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
