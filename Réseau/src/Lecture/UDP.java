package Lecture;

public class UDP implements ITransportProtocol {

	
	@Override
	public String getName() {
		return "UDP";
	}

	@Override
	public String getType() {
		return "0x17";
	}

}
