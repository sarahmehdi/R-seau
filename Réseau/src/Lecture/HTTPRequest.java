package Lecture;


import java.util.List;

public class HTTPRequest implements IHTTP,ITransportProtocol {

	private String methode;
	private String URL;
	private String version;
	private String entete;
	
	public HTTPRequest(List<Octet> octets,int index) {
	
		for(Octet o: octets) {
			if( o.equals("20")) break;
			methode+=(char) o.toDecimale();
			index++;
			
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).equals("20")) break;
			URL+=(char) octets.get(i).toDecimale();
			index=i;
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).equals("0d") && octets.get(i+1).equals("0a")) break;
			URL+=(char) octets.get(i).toDecimale();
			index=i;
		}
		
		entete=methode+" "+URL+" "+version+"\n";
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
		return "HTTPRequest";
	}

	@Override
	public String getType() {
		return methode;
	}

}
