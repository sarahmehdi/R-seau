package Lecture;

public class InvalidTrameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTrameException() {
		super("La trame contenue dans le fichier est invalide");
	}

}
