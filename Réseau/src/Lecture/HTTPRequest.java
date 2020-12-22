package Lecture;


import java.util.List;

public class HTTPRequest implements IHTTP{

	private String methode = "";
	private String URL = "";
	private String version = "";
	private String entete = "";
	
	public HTTPRequest(List<Octet> octets,int index) {
		for(int i=index; i<octets.size(); i++) {
			Octet o = octets.get(i);
			if( o.toString().equals("20 ")) break;
			methode+=(char) o.toDecimale();
			index++;
			
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).toString().equals("20 ")) break;
			URL+=(char) octets.get(i).toDecimale();
			index=i;
		}
		for(int i=index+1;i<octets.size();i++) {
			if (octets.get(i).toString().equals("0d ") && octets.get(i+1).toString().equals("0a ")) break;
			version+=(char) octets.get(i).toDecimale();
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
		s.append("HTTP : HTTPRequest \n");
		s.append("      Methode : "+methode);
		s.append("\n      URL : "+URL);
		s.append("\n      Version : "+version);
		s.append(entete);
		return s.toString();
	}
 
	@Override
	public String getName() {
		return "HTTPRequest";
	}

}
