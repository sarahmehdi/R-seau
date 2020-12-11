package Lecture;


public class Octet {
	private static String hexa; 
	
	public Octet(String hexa) {
		if(hexa.length()!=2) throw new IllegalArgumentException();
		Octet.hexa = hexa;
	}
	
			
	public int toDecimale() {
		int a, b;
		a =Character.getNumericValue(hexa.charAt(0));
		b = Character.getNumericValue(hexa.charAt(1));
		
		return a*16 + b;
	}
	
	public int toBinaire() {
		return Integer.parseInt(Integer.toBinaryString(toDecimale()));
	}
	
	public char getFirstHexa() {
		return hexa.charAt(0);
	}
	public char getSecondHexa() {
		return hexa.charAt(1);
	}
	
	public String toString() {
		return hexa + " ";
	}
}
